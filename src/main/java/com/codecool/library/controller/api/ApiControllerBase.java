package com.codecool.library.controller.api;

import com.codecool.library.dao.DAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ApiControllerBase<T extends DAO> {

    private T dao;

    public ApiControllerBase(T dao) {
        this.dao = dao;
    }

    public T getDAO() {
        return dao;
    }

    @GetMapping(value = "/search/{searchTerm}")
    public List search(@PathVariable String searchTerm) {
        return dao.fullTextSearch(searchTerm);
    }

    @GetMapping(value="/by-id/{id}")
    public Object getById(@PathVariable int id) {
        return dao.getById(id);
    }
}
