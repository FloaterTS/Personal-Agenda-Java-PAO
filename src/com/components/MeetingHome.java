package com.components;

public class MeetingHome extends Meeting
{

    private boolean family;

    public MeetingHome(int zi, int luna, int an)
    {
        super(zi, luna, an);
        family = true;
    }

    public void setFamily(Boolean family)
    {
        this.family = family;
    }

    public boolean getFamily()
    {
        return family;
    }

    public void getInfo()
    {
        if (family)
            System.out.print("-cu familia; ");
        else
            System.out.print("-personala; ");
    }
}

