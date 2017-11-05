package com.instateams.dao;

import com.instateams.model.Collaborator;
import com.instateams.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CollaboratorDaoImpl extends GenericDao<Collaborator> implements CollaboratorDao
{
    @Override
    protected Class<Collaborator> getPersistentClass()
    {
        return Collaborator.class;
    }

    @Override
    public List<Collaborator> findByRole(Role role)
    {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Collaborator> query = builder.createQuery(Collaborator.class);
        Root<Collaborator> root = query.from(Collaborator.class);
        query.select(root).where(builder.equal(root.get("role"), role));
        List<Collaborator> collaborators = session.createQuery(query).getResultList();
        session.close();

        return collaborators;
    }
}
