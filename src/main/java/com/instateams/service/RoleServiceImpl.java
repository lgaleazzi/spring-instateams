package com.instateams.service;

import com.instateams.dao.RoleDao;
import com.instateams.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll()
    {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id)
    {
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
        roleDao.delete(role);
    }
}
