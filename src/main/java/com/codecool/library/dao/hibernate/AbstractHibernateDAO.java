package com.codecool.library.dao.hibernate;

import com.codecool.library.dao.DAO;
import com.codecool.library.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractHibernateDAO<T extends BaseModel> implements DAO<T> {

    private final EntityManager entityManager;
    private final Class<? extends T> entityClass;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public AbstractHibernateDAO(EntityManager entityManager, Class<? extends T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;

        if(this.entityClass == null)
            throw new IllegalArgumentException("Entity class cannot be null.");

        if(this.entityManager == null)
            throw new IllegalArgumentException("Entity manager may not be null.");
    }


    @Override
    public Optional<T> getById(int id) {

       CriteriaBuilder cb = entityManager.getCriteriaBuilder();


       CriteriaQuery<? extends T> q = cb.createQuery(entityClass);

        Root<? extends T> entity =q.from(entityClass);

        q.where(cb.equal(entity.get("id"), id));

        T result = entityManager.createQuery(q).getSingleResult();

        return Optional.of(result);
    }

    @Override
    public abstract List<T> fullTextSearch(String searchTerm);

    @Override
    public void persist(BaseModel instance) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(instance);
        transaction.commit();
    }
}
