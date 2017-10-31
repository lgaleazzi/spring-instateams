package com.instateams.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @Column
    private List<Role> rolesNeeded;

    @ManyToMany
    @Column
    private List<Collaborator> collaborators;

    public Project() {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public List<Role> getRolesNeeded()
    {
        return rolesNeeded;
    }

    public void setRolesNeeded(List<Role> rolesNeeded)
    {
        this.rolesNeeded = rolesNeeded;
    }

    public List<Collaborator> getCollaborators()
    {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators)
    {
        this.collaborators = collaborators;
    }
}
