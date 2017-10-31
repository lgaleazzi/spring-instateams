package com.instateams.controller;

import com.instateams.model.Collaborator;
import com.instateams.model.Role;
import com.instateams.service.CollaboratorService;
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
public class CollaboratorController
{
    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/collaborators")
    public String allCollaborators(Model model)
    {
        List<Collaborator> collaborators = collaboratorService.findAll();
        List<Role> roles = roleService.findAll();
        model.addAttribute("collaborators", collaborators);
        model.addAttribute("roles", roles);
        if (!model.containsAttribute("collaboratorToSave"))
        {
            model.addAttribute("collaboratorToSave", new Collaborator());
        }

        return "collaborator/index";
    }

    @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator)
    {
        System.out.println(collaborator);
        collaboratorService.save(collaborator);
        return "redirect:/collaborators";
    }

    @RequestMapping("/collaborators/{id}")
    public String displayEditForm(@PathVariable Long id, Model model)
    {
        Collaborator collaborator = collaboratorService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("collaboratorToSave", collaborator);
        model.addAttribute("roles", roles);

        return "collaborator/index";
    }

    @RequestMapping(value = "/collaborators/{id}", method = RequestMethod.POST)
    public String editCollaborator(@Valid Collaborator collaborator)
    {
        collaboratorService.save(collaborator);

        return "redirect:/collaborators";
    }

    @RequestMapping("collaborators/{id}/delete")
    public String deleteCollaborator(@PathVariable Long id)
    {
        Collaborator collaborator = collaboratorService.findById(id);
        collaboratorService.delete(collaborator);

        return "redirect:/collaborators";
    }
}
