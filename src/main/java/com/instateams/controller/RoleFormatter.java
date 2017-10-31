package com.instateams.controller;

import com.instateams.model.Role;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class RoleFormatter implements Formatter<Role>
{
    @Override
    public Role parse(String id, Locale locale) throws ParseException
    {
        Role role = new Role();
        role.setId(Long.parseLong(id));
        return role;
    }

    @Override
    public String print(Role role, Locale locale)
    {
        String id = role.getId() + "";
        return id;
    }
}
