package com.instateams.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project implements Comparable<Project>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd") //This is the pattern of the date sent by the HTML form
    private LocalDate startDate;

    @ManyToMany
    private List<Role> rolesNeeded;

    @ManyToMany
    private List<Collaborator> collaborators;

    public Project()
    {
        this(null, null, null, null);
    }

    public Project(String name, String description, Status status, LocalDate startDate)
    {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.rolesNeeded = new ArrayList<>();
        this.collaborators = new ArrayList<>();
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

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

    public List<Role> getEmptyRoles()
    {
        List<Role> emptyRoles = new ArrayList<>(rolesNeeded);
        collaborators.forEach(collaborator -> emptyRoles.remove(collaborator.getRole()));

        return emptyRoles;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Project project = (Project)o;

        if (id != null ? !id.equals(project.id) : project.id != null)
            return false;
        return name != null ? name.equals(project.name) : project.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", rolesNeeded=" + rolesNeeded +
                '}';
    }

    @Override
    public int compareTo(Project o)
    {
        return this.startDate.compareTo(o.startDate);
    }
}
