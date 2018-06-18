package com.codecool.library.service;

import com.codecool.library.model.Author;
import com.codecool.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends BaseService<Author, AuthorRepository> {


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        super(authorRepository);
    }

}
