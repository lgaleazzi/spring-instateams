package com.instateams.model;


public enum Status
{
    ACTIVE("Active"),
    ARCHIVED("Archived"),
    NOT_STARTED("Not Started");

    private String name;

    Status(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
