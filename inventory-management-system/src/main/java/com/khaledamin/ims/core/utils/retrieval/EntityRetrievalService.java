package com.khaledamin.ims.core.utils.retrieval;

import java.util.List;
import java.util.Optional;

public interface EntityRetrievalService {

    <T, ID> Optional<T> getOptionalById(Class<T> entityClass, ID id);

    <T, ID> boolean exists(Class<T> entityClass, ID id);

    <T> List<T> findAll(Class<T> entityClass);
}