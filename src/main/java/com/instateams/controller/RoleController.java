package com.instateams.controller;

import com.instateams.model.Role;
import com.instateams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RoleController
{
    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    public String allRoles(Model model)
    {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "role/index";
    }
}
