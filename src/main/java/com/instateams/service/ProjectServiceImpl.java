package com.instateams.service;

import com.instateams.dao.ProjectDao;
import com.instateams.exception.ObjectNotFoundException;
import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;
import com.instateams.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> findAll()
    {
        List<Project> projects = projectDao.findAll();
        Collections.sort(projects);
        return projects;
    }

    @Override
    public List<Project> findByCollaborator(Collaborator collaborator)
    {
        return projectDao.findByCollaborator(collaborator);
    }

    @Override
    public List<Project> findByRole(Role role)
    {
        return projectDao.findByRole(role);
    }

    @Override
    public List<Status> allStatus()
    {
        return Arrays.asList(Status.values());
    }

    @Override
    public Project findById(Long id)
    {
        Project project = projectDao.findById(id);
        if (project == null)
        {
            throw new ObjectNotFoundException("Project was not found");
        }
        return project;
    }

    @Override
    public void unassignRole(Long projectId, Role role)
    {
        Project project = findById(projectId);
        //remove collaborator with that role assigned to the project
        Collaborator collaboratorWithRole = project.getCollaborators()
                .stream()
                .filter(collaborator -> collaborator.getRole().equals(role))
                .findFirst()
                .orElse(null);

        if (collaboratorWithRole != null)
        {
            unassignCollaborator(projectId, collaboratorWithRole);
        }

        //remove the role
        project.getRolesNeeded().remove(role);

        projectDao.save(project);
    }

    public void unassignCollaborator(Long projectId, Collaborator collaborator)
    {
        Project project = findById(projectId);
        project.getCollaborators().remove(collaborator);
        projectDao.save(project);
    }

    @Override
    public void save(Project project)
    {
        projectDao.save(project);
    }

    @Override
    public void delete(Project project)
    {
        projectDao.delete(project);
    }
}
