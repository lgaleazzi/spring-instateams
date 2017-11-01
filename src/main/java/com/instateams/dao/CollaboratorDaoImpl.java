package com.instateams.dao;

import com.instateams.model.Collaborator;
import com.instateams.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CollaboratorDaoImpl implements CollaboratorDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Collaborator> findAll()
    {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
        criteria.from(Collaborator.class);
        List<Collaborator> collaborators = session.createQuery(criteria).getResultList();
        session.close();

        return collaborators;
    }

    @Override
    public List<Collaborator> findByRole(Role role)
    {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Collaborator> query = builder.createQuery(Collaborator.class);
        Root<Collaborator> root = query.from(Collaborator.class);
        query.select(root).where(builder.equal(root.get("role"), role));
        List<Collaborator> collaborators = session.createQuery(query).getResultList();
        session.close();

        return collaborators;
    }

    @Override
    public Collaborator findById(Long id)
    {
        Session session = sessionFactory.openSession();
        Collaborator collaborator = session.get(Collaborator.class, id);
        session.close();

        return collaborator;
    }

    @Override
    public void save(Collaborator collaborator)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(collaborator);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Collaborator collaborator)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(collaborator);
        session.getTransaction().commit();
        session.close();
    }
}
