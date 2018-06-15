package com.codecool.library.controller.api;

import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import com.codecool.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/book")
public class BookApiController extends ApiControllerBase<Book, BookService> {

    @Autowired
    public BookApiController(BookService service) {
        super(service);
    }


    @PostMapping(value="/add")
    public Book add(@RequestParam String title,@RequestParam String location, @RequestParam int publicationYear, @RequestParam Language language) {

        Book book = new Book(title);
        book.setPublicationYear(publicationYear);
        book.setLanguage(language);
        book.setLocation(location);

        getService().persist(book);

        return book;
    }

}
