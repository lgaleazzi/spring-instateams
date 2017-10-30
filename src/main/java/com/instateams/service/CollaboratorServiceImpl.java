package com.instateams.service;

import com.instateams.dao.CollaboratorDao;
import com.instateams.exceptions.ObjectNotFoundException;
import com.instateams.model.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorServiceImpl implements CollaboratorService
{
    @Autowired
    private CollaboratorDao collaboratorDao;

    @Override
    public List<Collaborator> findAll()
    {
        return collaboratorDao.findAll();
    }

    @Override
    public Collaborator findById(Long id)
    {
        Collaborator collaborator = collaboratorDao.findById(id);
        if (collaborator == null)
        {
            throw new ObjectNotFoundException();
        }
        return collaborator;
    }

    @Override
    public void save(Collaborator collaborator)
    {
        collaboratorDao.save(collaborator);
    }

    @Override
    public void delete(Collaborator collaborator)
    {
        collaboratorDao.delete(collaborator);
    }
}
