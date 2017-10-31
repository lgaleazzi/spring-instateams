package com.instateams.controller;

import com.instateams.model.Project;
import com.instateams.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController
{
    @Autowired
    ProjectService projectService;

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

        return "project/form";
    }

    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addProject(@Valid Project project)
    {
        projectService.save(project);

        return "redirect:/";
    }
}
