package com.khaledamin.ims.core.utils.retrieval;


import com.khaledamin.ims.core.persistence.BaseRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityRetrievalServiceImpl implements EntityRetrievalService {

    private final Repositories repositories;

    public EntityRetrievalServiceImpl(ApplicationContext context) {
        this.repositories = new Repositories(context);
    }

    @SuppressWarnings("unchecked")
    private <T, ID> BaseRepository<T, ID> getRepository(Class<T> entityClass) {

        return (BaseRepository<T, ID>) repositories
                .getRepositoryFor(entityClass)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No persistence found for " + entityClass.getSimpleName()));
    }

    @Override
    public <T, ID> Optional<T> getOptionalById(Class<T> entityClass, ID id) {

        return getRepository(entityClass).findById(id);
    }

    @Override
    public <T, ID> boolean exists(Class<T> entityClass, ID id) {
        return getRepository(entityClass).existsById(id);
    }


    @Override
    public <T> List<T> findAll(Class<T> entityClass) {

        return getRepository(entityClass).findAll();
    }

}