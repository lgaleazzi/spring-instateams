package com.instateams.dao;

import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProjectDaoImpl extends GenericDao<Project> implements ProjectDao
{
    @Override
    protected Class<Project> getPersistentClass()
    {
        return Project.class;
    }

    @Override
    public List<Project> findByCollaborator(Collaborator collaborator)
    {
        Session session = getSessionFactory().openSession();

        TypedQuery<Project> query = session
                .createQuery("select distinct project from Project project join project.collaborators collaborator " +
                        "where collaborator = :collaborator", Project.class)
                .setParameter("collaborator", collaborator);

        List<Project> projects = query.getResultList();

        session.close();

        return projects;
    }

    public List<Project> findByRole(Role role)
    {
        Session session = getSessionFactory().openSession();

        TypedQuery<Project> query = session
                .createQuery("select distinct project from Project project join project.roles role where role = :role",
                        Project.class)
                .setParameter("role", role);

        List<Project> projects = query.getResultList();

        session.close();

        return projects;
    }

    @Override
    public Project findById(Long id)
    {
        Session session = getSessionFactory().openSession();
        Project project = session.get(Project.class, id);

        if (project != null)
        {
            Hibernate.initialize(project.getRoles());
            Hibernate.initialize(project.getCollaborators());
            project.getRoles().forEach(role -> Hibernate.initialize(role.getCollaborators()));
        }

        session.close();

        return project;
    }
}
