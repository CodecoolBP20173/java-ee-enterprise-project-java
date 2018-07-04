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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializerBean {

    @Autowired
    public InitializerBean(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin("admin", bCryptPasswordEncoder.encode("admin"));
            adminRepository.save(admin);
        }

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("László", "Bock"));
        authors.add(new Author("Sue", "Hadfield"));
        authors.add(new Author("Gill", "Hasson"));
        authors.add(new Author("Josh", "Seiden"));
        authors.add(new Author("Jeff", "Gothelf"));
        authors.add(new Author("Chris", "McChesney"));
        authors.add(new Author("Sean", "Covey"));
        authors.add(new Author("Jim", "Huling"));
        authors.add(new Author("Ádám", "Nagy"));
        authors.add(new Author("Ágnes", "Antal"));
        authors.add(new Author("Mónika", "Holzer"));
        authors.add(new Author("Daniel H", "Pink"));
        authors.add(new Author("Herbert", "Schildt"));
        authors.add(new Author("Patric", "Lencioni"));
        authors.add(new Author("Carrie Anne", "Phillhin"));
        authors.add(new Author("Robert", "C. Martin"));
        authors.add(new Author("Max", "Kanat-Alexander"));
        authors.add(new Author("Domonkos", "Kotsis"));
        authors.add(new Author("Gábor", "Légrádi"));
        authors.add(new Author("Gergely", "Nagy"));
        authors.add(new Author("Sándor", "Szénási"));
        authors.add(new Author("Andrew", "Hunt"));
        authors.add(new Author("David", "Thomas"));
        authors.add(new Author("István", "Reiter"));
        authors.add(new Author("Carol S.", "Dweck"));
        authors.add(new Author("Josh", "Carter"));
        authors.add(new Author("József", "Kovács"));
        authors.add(new Author("Gábor", "Takács"));
        authors.add(new Author("Miklós", "Takács"));
        authors.add(new Author("Frederic", "Laloux"));
        authorRepository.saveAll(authors);

        Publisher publisher1 = new Publisher("Bookline");
        publisherRepository.save(publisher1);

//        Author author, String title, Publisher publisher, String location, Integer publicationYear
        bookRepository.save(
                new Book(authors.get(0),"A google-titok", publisher1, "Szekszárd", 2015)
        );

    }
}
