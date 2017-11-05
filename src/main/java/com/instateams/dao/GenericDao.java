package com.instateams.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDao<T>
{
    @Autowired
    private SessionFactory sessionFactory;

    protected abstract Class<T> getPersistentClass();

    protected SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public List<T> findAll()
    {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
        criteria.from(getPersistentClass());
        List<T> entities = session.createQuery(criteria).getResultList();

        session.close();

        return entities;
    }

    public T findById(Long id)
    {
        Session session = sessionFactory.openSession();

        T entity = session.get(getPersistentClass(), id);

        session.close();

        return entity;
    }

    public void save(T entity)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T entity)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
