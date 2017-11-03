package com.instateams.dataloader;

import com.instateams.model.Collaborator;
import com.instateams.model.Project;
import com.instateams.model.Role;
import com.instateams.model.Status;
import com.instateams.service.CollaboratorService;
import com.instateams.service.ProjectService;
import com.instateams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner
{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CollaboratorService collaboratorService;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        List<Role> roles = Arrays.asList(
                new Role("Project Manager"),
                new Role("Developer"),
                new Role("QA")
        );
        roles.forEach(roleService::save);

        Arrays.asList(
                new Collaborator("Kim", roles.get(0)),
                new Collaborator("Janice", roles.get(0)),
                new Collaborator("Amanda", roles.get(1)),
                new Collaborator("Sam", roles.get(2)),
                new Collaborator("Adrian", roles.get(2))
        ).forEach(collaboratorService::save);


        Arrays.asList(
                new Project("Implement Instateams", "A tool to manage project teams", Status.RUNNING,
                        LocalDate.of(2018, 02, 03)),
                new Project("Implement giflib", "A tool to manage a gif library", Status.ARCHIVED,
                        LocalDate.of(2017, 05, 11))
        ).forEach(projectService::save);
    }
}
