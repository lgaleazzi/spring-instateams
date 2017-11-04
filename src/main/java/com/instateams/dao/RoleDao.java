package com.instateams.dao;

import com.instateams.model.Role;

import java.util.List;

public interface RoleDao
{
    List<Role> findAll();
    Role findById(Long id);
    void save(Role role);
    void delete(Role role);

    boolean hasCollaborators(Long roleId);
}
