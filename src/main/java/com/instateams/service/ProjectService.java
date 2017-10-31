package com.instateams.service;

import com.instateams.model.Project;
import com.instateams.model.Status;

import java.util.List;

public interface ProjectService
{
    List<Project> findAll();

    List<Status> allStatus();

    Project findById(Long id);

    void save(Project project);

    void delete(Project project);
}
