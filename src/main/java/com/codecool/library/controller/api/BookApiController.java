package com.codecool.library.controller.api;

import com.codecool.library.dao.BookDAO;
import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/book")
public class BookApiController extends ApiControllerBase<BookDAO> {

    @Autowired
    public BookApiController(BookDAO dao) {
        super(dao);
    }


    @PostMapping(value="/add")
    public Book add(@RequestParam String title,@RequestParam String location, @RequestParam int publicationYear, @RequestParam Language language) {

        Book book = new Book(title);
        book.setPublicationYear(publicationYear);
        book.setLanguage(language);
        book.setLocation(location);

        getDAO().persist(book);

        return book;
    }

}
