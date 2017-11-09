package com.instateams.service;

import com.instateams.dao.CollaboratorDao;
import com.instateams.exception.ObjectNotFoundException;
import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorServiceImpl implements CollaboratorService
{
    @Autowired
    private CollaboratorDao collaboratorDao;

    @Autowired
    private ProjectService projectService;

    @Override
    public List<Collaborator> findAll()
    {
        return collaboratorDao.findAll();
    }

    @Override
    public List<Collaborator> findByRole(Role role)
    {
        return collaboratorDao.findByRole(role);
    }


    @Override
    public Collaborator findById(Long id)
    {
        Collaborator collaborator = collaboratorDao.findById(id);
        if (collaborator == null)
        {
            throw new ObjectNotFoundException("Collaborator was not found");
        }
        return collaborator;
    }

    @Override
    public void save(Collaborator collaborator)
    {
        collaboratorDao.save(collaborator);
    }

    @Override
    public void unassignRole(Collaborator collaborator)
    {
        collaborator.setRole(null);
        collaboratorDao.save(collaborator);
    }

    @Override
    public void delete(Collaborator collaborator)
    {
        List<Project> projectsWithCollaborator = projectService.findByCollaborator(collaborator);
        if (!(projectsWithCollaborator == null || projectsWithCollaborator.isEmpty()))
        {
            projectsWithCollaborator.forEach(project -> projectService.unassignCollaborator(project.getId(), collaborator));
        }

        collaboratorDao.delete(collaborator);
    }
}
