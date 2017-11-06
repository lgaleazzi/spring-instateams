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

/*    @Override
    public List<Role> findAll()
    {
        Session session = getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
        criteria.from(Role.class);
        List<Role> roles = session.createQuery(criteria).getResultList();

        roles.forEach(role -> Hibernate.initialize(role.getCollaborators()));

        session.close();

        return roles;
    }*/

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

    @Override
    public boolean hasCollaborators(Long roleId)
    {
        return findById(roleId).hasCollaborators();
    }
}
