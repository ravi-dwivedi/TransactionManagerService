package com.transaction.manager.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.manager.service.dao.ChildDao;
import com.transaction.manager.service.dao.ParentDao;
import com.transaction.manager.service.dao.entity.ChildEntity;
import com.transaction.manager.service.dao.entity.ParentEntity;
import com.transaction.manager.service.dto.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionService {

    private final ParentDao parentDao;

    private final ChildDao childDao;

    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionService(ParentDao parentDao, ChildDao childDao) {
        this.parentDao = parentDao;
        this.childDao = childDao;
        objectMapper = new ObjectMapper();
    }

    public List<Transaction> getTransactions(final int page, final int size) {
        final List<ParentEntity> parentEntities = parentDao.findAll(page, size);
        final List<ChildEntity> childEntities =
                childDao.findByParentIds(
                        parentEntities.stream()
                                .map(parentEntity -> parentEntity.getId())
                                .collect(Collectors.toList()));

        final Map<Long, BigDecimal> parentIdByTotalPaidAmountMap =
                childEntities.stream().collect(Collectors.toMap(ChildEntity::getParentId,
                        ChildEntity::getPaidAmount,
                        (c1, c2) -> c1.add(c2)));

        return parentEntities.stream().map(parentEntity -> {
            final Transaction transaction = objectMapper.convertValue(parentEntity, Transaction.class);
            transaction.setTotalPaidAmount(parentIdByTotalPaidAmountMap
                    .getOrDefault(transaction.getId(), new BigDecimal(0)));
            return transaction;
        }).collect(Collectors.toList());
    }
}
