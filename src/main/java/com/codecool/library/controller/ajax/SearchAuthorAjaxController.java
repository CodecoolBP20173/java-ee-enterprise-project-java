package com.codecool.library.controller.ajax;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"search-author-ajax"})
public class SearchAuthorAjaxController extends AjaxControllerBase {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTerm = req.getParameter("search-term");

        EntityManager em = getEntityManager();

        List authors = em.createQuery("SELECT author FROM Author author WHERE firstName LIKE :name OR lastName LIKE :name")
                .setParameter("name", "%"+searchTerm+"%")
                .setMaxResults(10)
                .getResultList();

        closeEntitymanager();

        outputJson(resp,authors);
    }
}
