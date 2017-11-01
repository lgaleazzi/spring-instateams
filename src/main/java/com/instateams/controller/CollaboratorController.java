package com.instateams.controller;

import com.instateams.controller.flashmessage.FlashMessage;
import com.instateams.model.Collaborator;
import com.instateams.model.Role;
import com.instateams.service.CollaboratorService;
import com.instateams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CollaboratorController
{
    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("allRoles")
    public List<Role> populateRoles()
    {
        return roleService.findAll();
    }

    @RequestMapping("/collaborators")
    public String allCollaborators(Model model)
    {
        List<Collaborator> collaborators = collaboratorService.findAll();
        model.addAttribute("collaborators", collaborators);

        if (!model.containsAttribute("collaborator"))
        {
            model.addAttribute("collaborator", new Collaborator());
        }

        return "collaborator/index";
    }

    @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
            redirectAttributes.addFlashAttribute("collaborator", collaborator);
            return "redirect:/collaborators";
        }

        collaboratorService.save(collaborator);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully added",
                FlashMessage.Status.SUCCESS));

        return "redirect:/collaborators";
    }

    @RequestMapping("/collaborators/{id}")
    public String displayEditForm(@PathVariable Long id, Model model)
    {
        Collaborator collaborator = collaboratorService.findById(id);

        if (!model.containsAttribute("collaborator"))
        {
            model.addAttribute("collaborator", collaborator);
        }

        return "collaborator/index";
    }

    @RequestMapping(value = "/collaborators/{id}", method = RequestMethod.POST)
    public String editCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
            redirectAttributes.addFlashAttribute("collaborator", collaborator);
            return String.format("redirect:/collaborators/%s", collaborator.getId());
        }

        collaboratorService.save(collaborator);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully updated",
                FlashMessage.Status.SUCCESS));


        return "redirect:/collaborators";
    }

    @RequestMapping("collaborators/{id}/delete")
    public String deleteCollaborator(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        Collaborator collaborator = collaboratorService.findById(id);
        collaboratorService.delete(collaborator);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully deleted.",
                FlashMessage.Status.SUCCESS));


        return "redirect:/collaborators";
    }
}
