package com.codecool.library.controller.api;

import com.codecool.library.dao.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/author/")
public class AuthorApiController extends ApiControllerBase<AuthorDAO> {

    @Autowired
    public AuthorApiController(AuthorDAO dao){
        super(dao);
    }

}
