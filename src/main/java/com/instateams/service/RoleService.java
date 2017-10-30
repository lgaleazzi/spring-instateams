package com.instateams.service;

import com.instateams.model.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void delete(Role role);
}
