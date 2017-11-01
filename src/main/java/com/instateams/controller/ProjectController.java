package com.instateams.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @RequestMapping("/data")
    public String fillWithData()
    {
        fillData();
        return "redirect:/";
    }

    @RequestMapping("/projects/add")
    public String addForm(Model model)
    {
        if (!model.containsAttribute("project"))
        {
            model.addAttribute("project", new Project());
        }

        return "project/form";
    }

    @RequestMapping(value = "/projects/save", method = RequestMethod.POST)
    public String addProject(@Valid Project project)
    {
        System.out.println(project);
        projectService.save(project);

        return "redirect:/";
    }

    @RequestMapping("/projects/{id}")
    public String projectDetails(@PathVariable Long id, Model model)
    {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        System.out.println(project);

        return "project/details";
    }

    @RequestMapping("/projects/{id}/edit")
    public String editForm(@PathVariable Long id, Model model)
    {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);

        return "project/form";
    }

    @RequestMapping("projects/{id}/collaborators")
    public String editCollForm(@PathVariable Long id, Model model)
    {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);

        return "project/collaborators";
    }

    private void fillData()
    {
        Role role1 = new Role();
        role1.setName("Project Manager");
        Role role2 = new Role();
        role2.setName("Developer");
        Role role3 = new Role();
        role3.setName("QA");
        roleService.save(role1);
        roleService.save(role2);
        roleService.save(role3);

        Collaborator coll1 = new Collaborator();
        coll1.setName("Kim");
        coll1.setRole(role1);
        Collaborator coll2 = new Collaborator();
        coll2.setName("Janice");
        coll2.setRole(role1);
        Collaborator coll3 = new Collaborator();
        coll3.setName("Amanda");
        coll3.setRole(role2);
        Collaborator coll4 = new Collaborator();
        coll4.setName("Sam");
        coll4.setRole(role3);
        Collaborator coll5 = new Collaborator();
        coll5.setName("Hadrian");
        coll5.setRole(role2);

        collaboratorService.save(coll1);
        collaboratorService.save(coll2);
        collaboratorService.save(coll3);
        collaboratorService.save(coll4);
        collaboratorService.save(coll5);

        Project project1 = new Project();
        project1.setName("Implement Instateams");
        project1.setDescription("A cool project");
        project1.setStatus(Status.RUNNING);
        project1.setStartDate(LocalDate.of(2018, 02, 03));
        project1.getRoles().add(role1);
        project1.getRoles().add(role2);

        projectService.save(project1);

        Project project2 = new Project();
        project2.setName("Implement giflib");
        project2.setDescription("Another cool project");
        project2.setStatus(Status.ARCHIVED);
        project2.setStartDate(LocalDate.of(2017, 05, 11));
        project2.getRoles().add(role2);
        project2.getRoles().add(role3);

        projectService.save(project2);
    }
}
