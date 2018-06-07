package com.codecool.library.controller;

import com.codecool.library.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBConnect {
    public static void persist(BaseModel ... baseModels) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cclibrary");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        for (BaseModel model : baseModels) {
            em.persist(model);
        }

        transaction.commit();
        em.close();
        emf.close();
    }
}
