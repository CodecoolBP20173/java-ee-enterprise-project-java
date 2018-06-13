package com.codecool.library.controller.api;

import com.codecool.library.dao.BookDAO;
import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookApiController extends ApiControllerBase {


    private BookDAO dao;

    @Autowired
    public BookApiController(BookDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/api/book/search/{searchTerm}", method = {RequestMethod.GET})
    public List search(@PathVariable  String searchTerm) {
        return dao.fullTextSearch(searchTerm);
    }

    @RequestMapping(value="/api/book/add", method = RequestMethod.POST)
    public Book add(@RequestParam String title,@RequestParam String location, @RequestParam int publicationYear, @RequestParam Language language) {

        Book book = new Book(title);
        book.setPublicationYear(publicationYear);
        book.setLanguage(language);
        book.setLocation(location);

        dao.persist(book);

        return book;
    }

    @RequestMapping(value="/api/book/by-id/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable int id) {
        return dao.getById(id);
    }
}
