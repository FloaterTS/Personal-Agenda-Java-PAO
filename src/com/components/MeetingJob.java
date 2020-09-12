package com.components;

public class MeetingJob extends Meeting
{

    private boolean withBoss;

    public MeetingJob(int zi, int luna, int an)
    {
        super(zi, luna, an);
        withBoss = false;
    }

    public void setWithBoss(boolean withBoss)
    {
        this.withBoss = withBoss;
    }

    public boolean getWithBoss()
    {
        return withBoss;
    }

    public void getInfo()
    {
        if (withBoss)
            System.out.print("-job-related: cu conducerea; ");
        else
            System.out.print("-job-related: cazuala; ");
    }
}
