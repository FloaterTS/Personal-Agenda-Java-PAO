package com.components;

public class TaskHome extends Task
{
    private boolean must;

    public TaskHome(String _titlu)
    {
        super(_titlu);
        must = false;
    }

    public void setMust(Boolean m)
    {
        must = m;
    }

    public boolean getMust()
    {
        return must;
    }

    public void getInfo()
    {
        if (must)
            System.out.print("-obligatorie; ");
        else
            System.out.print("-optional; ");
    }
}
