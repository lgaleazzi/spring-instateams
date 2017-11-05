package com.instateams.service;

import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;
import com.instateams.model.Status;

import java.util.List;

public interface ProjectService
{
    List<Project> findAll();

    List<Project> findByCollaborator(Collaborator collaborator);

    List<Project> findByRole(Role role);

    List<Status> allStatus();

    Project findById(Long id);

    void unassignRole(Long projectId, Role role);

    void unassignCollaborator(Long projectId, Collaborator collaborator);

    void save(Project project);

    void delete(Project project);
}
