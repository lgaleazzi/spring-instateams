package com.instateams.dao;

import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;

import java.util.List;

public interface ProjectDao
{
    List<Project> findAll();

    List<Project> findByCollaborator(Collaborator collaborator);
    List<Project> findByRole(Role role);
    Project findById(Long id);
    void save(Project project);
    void delete(Project project);
}
