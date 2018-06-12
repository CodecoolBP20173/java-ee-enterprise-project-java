package com.codecool.library.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
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
}
