package com.codecool.library.controller.api;

import com.codecool.library.model.BaseModel;
import com.codecool.library.repository.BaseModelRepository;
import com.codecool.library.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ApiControllerBase<M extends BaseModel, T extends BaseService<M, ? extends BaseModelRepository<M>>> {

    private T service;

    public ApiControllerBase(T service) {
        this.service = service;
    }

    public T getService() {
        return service;
    }

    @GetMapping(value = "/search/{searchTerm}")
    public List<M> search(@PathVariable String searchTerm) {
        return service.fullTextSearch(searchTerm);
    }

    @GetMapping(value="/by-id/{id}")
    public M getById(@PathVariable long id) {
        return service.getById(id);
    }
}
