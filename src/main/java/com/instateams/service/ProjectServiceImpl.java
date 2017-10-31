package com.instateams.service;

import com.instateams.dao.ProjectDao;
import com.instateams.exceptions.ObjectNotFoundException;
import com.instateams.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> findAll()
    {
        return projectDao.findAll();
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
