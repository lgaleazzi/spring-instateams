package com.instateams.dao;

import com.instateams.model.Collaborator;

import java.util.List;

public interface CollaboratorDao
{
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}
