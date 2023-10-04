package com.transaction.manager.service.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.transaction.manager.service.dao.entity.FileDataEntity;
import com.transaction.manager.service.dao.entity.ParentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ParentDao extends AbstractDao<ParentEntity> {

    public ParentDao() {
        super("/Parent.json", new TypeReference<FileDataEntity<ParentEntity>>() {
        });
    }

    public List<ParentEntity> findAll(final int page, final int size) {
        return super.findAll(page, size);
    }

    public Optional<ParentEntity> findById(final Long id) {
        return this.data.getData().stream()
                .filter(parentEntity -> parentEntity.getId().equals(id))
                .findFirst();
    }

}
