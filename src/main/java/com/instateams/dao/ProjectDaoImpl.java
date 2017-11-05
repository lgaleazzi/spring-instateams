package com.instateams.dao;

import com.instateams.model.Project;
import com.instateams.model.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> findAll()
    {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        criteria.from(Project.class);
        List<Project> projects = session.createQuery(criteria).getResultList();
        projects.forEach(project -> Hibernate.initialize(project.getRoles()));
        session.close();

        return projects;
    }

    public List<Project> findByRole(Role role)
    {
        //TODO: finish this
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        Root<Project> root = criteria.from(Project.class);

        Path<List<Role>> roles = root.get("roles");
        Expression<Role> roleParameter = builder.parameter(Role.class, "role");

        criteria.select(root).where(roleParameter.in(roles));

        TypedQuery<Project> query = session.createQuery(criteria)
                .setParameter("role", role);
        List<Project> projects = query.getResultList();

        return projects;
    }

    @Override
    public Project findById(Long id)
    {
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, id);
        Hibernate.initialize(project.getRoles());
        Hibernate.initialize(project.getCollaborators());
        project.getCollaborators().forEach(collaborator -> Hibernate.initialize(collaborator.getRole()));
        project.getRoles().forEach(role -> Hibernate.initialize(role.getCollaborators()));
        session.close();

        return project;
    }

    @Override
    public void save(Project project)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(project);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Project project)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(project);
        session.getTransaction().commit();
        session.close();
    }
}
