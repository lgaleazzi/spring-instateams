package com.instateams.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements Comparable<Role>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @OneToMany(mappedBy = "role")
    private List<Collaborator> collaborators;

    public Role()
    {
        this(null);
    }

    public Role(String name)
    {
        this.name = name;
        collaborators = new ArrayList<>();
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

    public List<Collaborator> getCollaborators()
    {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators)
    {
        this.collaborators = collaborators;
    }

    public boolean hasCollaborators()
    {
        return !(collaborators == null || collaborators.isEmpty());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Role role = (Role)o;

        if (id != null ? !id.equals(role.id) : role.id != null)
            return false;
        return name != null ? name.equals(role.name) : role.name == null;

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
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Role o)
    {
        return this.getName().compareTo(o.getName());
    }
}
