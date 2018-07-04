package com.codecool.library.service;

import com.codecool.library.model.Admin;
import com.codecool.library.model.Author;
import com.codecool.library.model.Book;
import com.codecool.library.model.Publisher;
import com.codecool.library.repository.AdminRepository;
import com.codecool.library.repository.AuthorRepository;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    @Autowired
    public InitializerBean(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin("admin", bCryptPasswordEncoder.encode("admin"));
            adminRepository.save(admin);
        }

        Author author1 = new Author("László", "Bock");
        authorRepository.save(author1);

        Publisher publisher1 = new Publisher("Bookline");
        publisherRepository.save(publisher1);
        
//        Author author, String title, Publisher publisher, String location, Integer publicationYear
        bookRepository.save(
                new Book(author1,"A google-titok", publisher1, "Szekszárd", 2015)
        );

    }
}
