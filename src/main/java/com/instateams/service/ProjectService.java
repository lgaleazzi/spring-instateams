package com.instateams.service;

import com.instateams.model.Project;
import com.instateams.model.Role;
import com.instateams.model.Status;

import java.util.List;

public interface ProjectService
{
    List<Project> findAll();

    List<Project> findByRole(Role role);
    List<Status> allStatus();

    Project findById(Long id);

    void save(Project project);

    void delete(Project project);
}
