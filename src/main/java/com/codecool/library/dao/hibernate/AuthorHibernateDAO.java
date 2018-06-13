package com.codecool.library.dao.hibernate;

import com.codecool.library.dao.AuthorDAO;
import com.codecool.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AuthorHibernateDAO extends AbstractHibernateDAO<Author> implements AuthorDAO {

    @Autowired
    public AuthorHibernateDAO(EntityManager entityManager) {
        super(entityManager, Author.class);
    }

    @Override
    public List<Author> fullTextSearch(String searchTerm) {
        return getEntityManager().createQuery("SELECT author FROM Author author WHERE LOWER(firstName) LIKE :name OR LOWER(lastName) LIKE :name")
                .setParameter("name", "%"+searchTerm.toLowerCase()+"%")
                .setMaxResults(10)
                .getResultList();

        //to be replaced with Hibernate Full Text search
    }
}
