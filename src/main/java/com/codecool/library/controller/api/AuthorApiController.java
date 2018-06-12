package com.codecool.library.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class AuthorApiController extends ApiControllerBase {


    @RequestMapping(value = "/api/author/{searchTerm}", method = {RequestMethod.GET})
    public List search(@PathVariable String searchTerm) {

        EntityManager em = getEntityManager();

        List authors = em.createQuery("SELECT author FROM Author author WHERE LOWER(firstName) LIKE :name OR LOWER(lastName) LIKE :name")
                .setParameter("name", "%"+searchTerm.toLowerCase()+"%")
                .setMaxResults(10)
                .getResultList();

        closeEntitymanager();

        return authors;
    }
}
