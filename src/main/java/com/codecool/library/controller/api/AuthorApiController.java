package com.codecool.library.controller.api;

import com.codecool.library.dao.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorApiController extends ApiControllerBase {

    private AuthorDAO dao;

    @Autowired
    public AuthorApiController(AuthorDAO dao){
        this.dao = dao;
    }

    @RequestMapping(value = "/api/author/search/{searchTerm}", method = {RequestMethod.GET})
    public List search(@PathVariable String searchTerm) {
        return dao.fullTextSearch(searchTerm);
    }

    @RequestMapping(value="/api/author/by-id/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable int id) {
        return dao.getById(id);
    }
}
