package com.codecool.library.controller.api;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"api/authors"})
public class AuthorApiController extends ApiControllerBase {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTerm = req.getParameter("search");

        EntityManager em = getEntityManager();

        List authors = em.createQuery("SELECT author FROM Author author WHERE LOWER(firstName) LIKE :name OR LOWER(lastName) LIKE :name")
                .setParameter("name", "%"+searchTerm.toLowerCase()+"%")
                .setMaxResults(10)
                .getResultList();

        closeEntitymanager();

        outputJson(resp,authors);
    }
}
