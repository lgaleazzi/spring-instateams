package com.instateams.model;


public enum Status
{
    RUNNING("Running", "active"),
    ARCHIVED("Archived", "inactive"),
    NOT_STARTED("Not Started", "inactive");

    private String name;
    private String state;

    Status(String name, String state)
    {
        this.name = name;
        this.state = state;
    }

    public String getName()
    {
        return name;
    }

    public String getState()
    {
        return state;
    }
}
