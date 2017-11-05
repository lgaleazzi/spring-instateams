package com.instateams.dao;

import com.instateams.model.Collaborator;
import com.instateams.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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

        TypedQuery<Collaborator> query = session
                .createQuery("select collaborator from Collaborator collaborator where collaborator.role = :role",
                        Collaborator.class)
                .setParameter("role", role);

        List<Collaborator> collaborators = query.getResultList();

        session.close();

        return collaborators;
    }
}
