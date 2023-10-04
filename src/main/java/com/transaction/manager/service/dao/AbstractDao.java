package com.transaction.manager.service.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.manager.service.dao.entity.FileDataEntity;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Log4j2
public abstract class AbstractDao<T> {

    protected final FileDataEntity<T> data;

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected AbstractDao(final String filePath, final TypeReference<FileDataEntity<T>> typeReference) {
        this.data = loadDataFromJson(filePath, typeReference);
    }

    protected List<T> findAll(final int page, final int size) {
        final int startItem = page * size;

        if (data.getData().size() < startItem) {
            return Collections.emptyList();
        } else {
            final int toIndex = Math.min(startItem + size, data.getData().size());
            return data.getData().subList(startItem, toIndex);
        }
    }


    private FileDataEntity<T> loadDataFromJson(final String filePath, final TypeReference<FileDataEntity<T>> typeReference) {
        final InputStream inputStream = TypeReference.class.getResourceAsStream(filePath);
        try {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            log.info("Exception while parsing data from file - {}", filePath);
            return new FileDataEntity<>();
        }
    }
}
