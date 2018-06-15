package com.codecool.library.controller.api;

import com.codecool.library.model.Author;
import com.codecool.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/author/")
public class AuthorApiController extends ApiControllerBase<Author, AuthorService> {

    @Autowired
    public AuthorApiController(AuthorService service){
        super(service);
    }

}
