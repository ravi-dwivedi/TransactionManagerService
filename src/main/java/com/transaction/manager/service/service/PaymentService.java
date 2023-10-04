package com.transaction.manager.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.manager.service.dao.ChildDao;
import com.transaction.manager.service.dao.ParentDao;
import com.transaction.manager.service.dao.entity.ChildEntity;
import com.transaction.manager.service.dao.entity.ParentEntity;
import com.transaction.manager.service.dto.Payment;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PaymentService {

    private final ParentDao parentDao;

    private final ChildDao childDao;

    private final ObjectMapper objectMapper;

    @Autowired
    public PaymentService(@NonNull final ParentDao parentDao, @NonNull final ChildDao childDao) {
        this.parentDao = parentDao;
        this.childDao = childDao;
        objectMapper = new ObjectMapper();
    }

    public List<Payment> getPaymentsByParentId(@NonNull final Long parentId) {
        final Optional<ParentEntity> parentEntity = parentDao.findById(parentId);
        if (parentEntity.isPresent()) {
            final List<ChildEntity> childEntities = childDao.findByParentId(parentId);
            return childEntities.stream()
                    .map(childEntity -> {
                        final Payment payment = objectMapper.convertValue(childEntity, Payment.class);
                        payment.setParentId(parentId);
                        payment.setSender(parentEntity.get().getSender());
                        payment.setReceiver(parentEntity.get().getReceiver());
                        payment.setTotalAmount(parentEntity.get().getTotalAmount());
                        return payment;
                    })
                    .sorted(Comparator.comparing(Payment::getId))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
