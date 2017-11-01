package com.instateams.service;

import com.instateams.model.Collaborator;
import com.instateams.model.Role;

import java.util.List;

public interface CollaboratorService
{
    List<Collaborator> findAll();

    List<Collaborator> findByRole(Role role);

    Collaborator findById(Long id);

    void save(Collaborator collaborator);

    void delete(Collaborator collaborator);
}
