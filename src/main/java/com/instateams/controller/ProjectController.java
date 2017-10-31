package com.instateams.controller;

import com.instateams.model.Project;
import com.instateams.service.CollaboratorService;
import com.instateams.service.ProjectService;
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
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CollaboratorService collaboratorService;

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
        if (!model.containsAttribute("projectToSave"))
        {
            model.addAttribute("projectToSave", new Project());
        }
        model.addAttribute("allStatus", projectService.allStatus());
        model.addAttribute("allRoles", roleService.findAll());

        return "project/form";
    }

    @RequestMapping(value = "/projects/save", method = RequestMethod.POST)
    public String addProject(@Valid Project project)
    {
        projectService.save(project);

        return "redirect:/";
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
        model.addAttribute("projectToSave", project);
        model.addAttribute("allStatus", projectService.allStatus());
        model.addAttribute("allRoles", roleService.findAll());

        return "project/form";
    }
}
