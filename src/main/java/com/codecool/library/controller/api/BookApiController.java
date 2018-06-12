package com.codecool.library.controller.api;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/search-book-ajax"})
public class BookApiController extends ApiControllerBase {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTerm = req.getParameter("search-term");

        EntityManager em = getEntityManager();

        List books = em.createQuery("SELECT book FROM Book book WHERE LOWER(title) LIKE :title")
        .setParameter("title", "%"+searchTerm.toLowerCase()+"%")
        .setMaxResults(10)
        .getResultList();

        closeEntitymanager();

        outputJson(resp,books);
    }
}
