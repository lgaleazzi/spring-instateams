package com.instateams.controller;

import com.instateams.model.Role;
import com.instateams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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
        if (!model.containsAttribute("newRole"))
        {
            model.addAttribute("newRole", new Role());
        }

        return "role/index";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String addRole(@Valid Role role)
    {
        //TODO: deal with invalid data
        roleService.save(role);

        return "redirect:/roles";
    }

    @RequestMapping("/roles/{id}/edit")
    public String displayEditForm(@PathVariable Long id, Model model)
    {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);

        return "role/edit";
    }

    @RequestMapping(value = "/roles/{id}/edit", method = RequestMethod.POST)
    public String editRole(@Valid Role role)
    {
        roleService.save(role);

        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/{id}/delete", method = RequestMethod.POST)
    public String deleteRole(@PathVariable Long id)
    {
        Role role = roleService.findById(id);
        roleService.delete(role);
        return "redirect:/roles";
    }
}
