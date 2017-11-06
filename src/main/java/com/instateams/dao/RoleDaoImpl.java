package com.instateams.dao;


import com.instateams.model.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDao<Role> implements RoleDao
{
    @Override
    protected Class<Role> getPersistentClass()
    {
        return Role.class;
    }

    @Override
    public Role findById(Long id)
    {
        Session session = getSessionFactory().openSession();
        Role role = session.get(Role.class, id);

        if (role != null)
        {
            Hibernate.initialize(role.getCollaborators());
        }

        session.close();

        return role;
    }
}
