package com.codecool.library.controller.api;

import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RestController
public class BookApiController extends ApiControllerBase {

    @RequestMapping(value = "/api/book/{searchTerm}", method = {RequestMethod.GET})
    public List search(@PathVariable  String searchTerm) {
        EntityManager em = getEntityManager();

        List books = em.createQuery("SELECT book FROM Book book WHERE LOWER(title) LIKE :title")
        .setParameter("title", "%"+searchTerm.toLowerCase()+"%")
        .setMaxResults(10)
        .getResultList();

        closeEntitymanager();

        return books;
    }

    @RequestMapping(value="/api/book", method = RequestMethod.POST)
    public Book add(@RequestParam String title,@RequestParam String location, @RequestParam int publicationYear, @RequestParam Language language) {
        EntityManager em = getEntityManager();

        Book book = new Book(title);
        book.setPublicationYear(publicationYear);
        book.setLanguage(language);
        book.setLocation(location);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(book);
        transaction.commit();

        closeEntitymanager();

        return book;
    }
}
