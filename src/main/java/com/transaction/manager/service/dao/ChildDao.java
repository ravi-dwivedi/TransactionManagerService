package com.transaction.manager.service.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.transaction.manager.service.dao.entity.ChildEntity;
import com.transaction.manager.service.dao.entity.FileDataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChildDao extends AbstractDao<ChildEntity> {

    public ChildDao() {
        super("/Child.json", new TypeReference<FileDataEntity<ChildEntity>>() {
        });
    }

    public List<ChildEntity> findByParentId(final Long parentId) {
        return this.data.getData().stream()
                .filter(childEntity -> childEntity.getParentId().equals(parentId))
                .collect(Collectors.toList());
    }

    public List<ChildEntity> findByParentIds(final List<Long> parentIds) {
        return this.data.getData().stream()
                .filter(childEntity -> parentIds.contains(childEntity.getParentId()))
                .collect(Collectors.toList());
    }
}
