package com.codecool.library.dao.hibernate;

import com.codecool.library.dao.BookDAO;
import com.codecool.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BookHibernateDAO extends AbstractHibernateDAO<Book> implements BookDAO {

    @Autowired
    public BookHibernateDAO(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public List<Book> fullTextSearch(String searchTerm) {
        return getEntityManager().createQuery("SELECT book FROM Book book WHERE LOWER(title) LIKE :title")
                .setParameter("title", "%"+searchTerm.toLowerCase()+"%")
                .setMaxResults(10)
                .getResultList();
        //to be replaced with Hibernate Full Text Search

    }
}
