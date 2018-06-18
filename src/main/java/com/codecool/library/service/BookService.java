package com.codecool.library.service;

import com.codecool.library.model.Book;
import com.codecool.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book, BookRepository> {


    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }

}
