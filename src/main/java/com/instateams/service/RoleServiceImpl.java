package com.instateams.service;

import com.instateams.dao.RoleDao;
import com.instateams.exceptions.ObjectNotFoundException;
import com.instateams.model.Collaborator;
import com.instateams.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private CollaboratorService collaboratorService;

    @Override
    public List<Role> findAll()
    {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id)
    {
        Role role = roleDao.findById(id);
        if (role == null)
        {
            throw new ObjectNotFoundException();
        }
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role)
    {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role)
    {
        if (roleDao.hasCollaborators(role.getId()))
        {
            List<Collaborator> collaborators = collaboratorService.findByRole(role);
            collaborators.forEach(collaboratorService::unassignRole);
        }

        roleDao.delete(role);
    }
}
