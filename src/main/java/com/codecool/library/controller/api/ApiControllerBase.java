package com.codecool.library.controller.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApiControllerBase {

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

}
