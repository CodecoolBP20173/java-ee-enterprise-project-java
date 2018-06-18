package com.codecool.library.service;

import com.codecool.library.model.BaseModel;
import com.codecool.library.repository.BaseModelRepository;

import java.util.List;

public abstract class BaseService<M extends BaseModel, R extends BaseModelRepository<M>> {

    private R repository;

    BaseService(R repository) {
        this.repository = repository;
    }

    public M getById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<M> fullTextSearch(String searchTerm) {
        return getRepository().fullTextSearch("%"+searchTerm.toLowerCase()+"%");
    }

    public void persist(M model) {
        repository.save(model);
    }

    protected R getRepository() {
        return repository;
    }
}
