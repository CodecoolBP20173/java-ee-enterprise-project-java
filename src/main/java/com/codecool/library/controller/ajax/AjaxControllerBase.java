package com.codecool.library.controller.ajax;

import com.codecool.library.gson.CollectionAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AjaxControllerBase extends HttpServlet {

    EntityManagerFactory emf;
    EntityManager em;

    protected EntityManager getEntityManager() {
        if(em != null && em.isOpen())
            return em;

        emf = Persistence.createEntityManagerFactory("cclibrary");
        em = emf.createEntityManager();

        return em;
    }

    protected void closeEntitymanager() {
        em.close();
        emf.close();
    }

    protected void outputJson(HttpServletResponse resp, Object obj) throws IOException {

        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Collection.class, new CollectionAdapter()).create();

        String output = gson.toJson(obj);

        resp.setHeader("Content-Type","application/json;charset=utf-8");
        resp.getWriter().write(output);
        resp.getWriter().close();
    }
}
