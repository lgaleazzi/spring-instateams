package com.instateams.service;

import com.instateams.dao.ProjectDao;
import com.instateams.exceptions.ObjectNotFoundException;
import com.instateams.model.Project;
import com.instateams.model.Role;
import com.instateams.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Project> findByRole(Role role)
    {
        //TODO: implement with findByRole once it works
        return projectDao.findAll()
                .stream()
                .filter(project -> project.getRoles().contains(role))
                .collect(Collectors.toList());
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
            throw new ObjectNotFoundException();
        }
        return project;
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
