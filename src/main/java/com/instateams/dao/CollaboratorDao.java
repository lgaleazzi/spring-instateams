package com.instateams.dao;

import com.instateams.model.Collaborator;
import com.instateams.model.Role;

import java.util.List;

public interface CollaboratorDao
{
    List<Collaborator> findAll();

    List<Collaborator> findByRole(Role role);
    Collaborator findById(Long id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}
