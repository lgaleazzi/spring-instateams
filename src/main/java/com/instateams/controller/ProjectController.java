package com.instateams.controller;

import com.instateams.controller.flashmessage.FlashMessage;
import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;
import com.instateams.model.Status;
import com.instateams.service.CollaboratorService;
import com.instateams.service.ProjectService;
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
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CollaboratorService collaboratorService;

    @ModelAttribute("allRoles")
    public List<Role> populateRoles()
    {
        return roleService.findAll();
    }

    @ModelAttribute("allStatus")
    public List<Status> populateStatus()
    {
        return projectService.allStatus();
    }

    @ModelAttribute("allCollaborators")
    public List<Collaborator> populateCollaborators()
    {
        return collaboratorService.findAll();
    }

    @RequestMapping("/")
    public String allProjects(Model model)
    {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/index";
    }

    @RequestMapping("/projects/add")
    public String addForm(Model model)
    {
        if (!model.containsAttribute("project"))
        {
            model.addAttribute("project", new Project());
        }
        model.addAttribute("action", "/projects/add");

        return "project/form";
    }

    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addProject(@Valid Project project, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            redirectAttributes.addFlashAttribute("project", project);
            return "redirect:/projects/add";
        }

        projectService.save(project);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Project successfully added",
                FlashMessage.Status.SUCCESS));

        return String.format("redirect:/projects/%s", project.getId());
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.POST)
    public String editProject(@Valid Project project, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            redirectAttributes.addFlashAttribute("project", project);
            return String.format("redirect:/projects/%s/edit", project.getId());
        }

        projectService.save(project);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Project successfully updated",
                FlashMessage.Status.SUCCESS));

        return String.format("redirect:/projects/%s", project.getId());
    }


    @RequestMapping("/projects/{id}")
    public String projectDetails(@PathVariable Long id, Model model)
    {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);

        return "project/details";
    }

    @RequestMapping("/projects/{id}/edit")
    public String editForm(@PathVariable Long id, Model model)
    {
        Project project = projectService.findById(id);
        if (!model.containsAttribute("project"))
        {
            model.addAttribute("project", project);
        }
        model.addAttribute("action", "/projects/" + project.getId());

        return "project/form";
    }

    @RequestMapping("projects/{id}/collaborators")
    public String editCollForm(@PathVariable Long id, Model model)
    {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);

        return "project/collaborators";
    }

    @RequestMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        Project project = projectService.findById(id);
        projectService.delete(project);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Project successfully deleted",
                FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }
}
