package com.components;
import com.helper.ZDate;

public class TaskJob extends Task
{
    private ZDate deadline;

    public TaskJob(String _titlu)
    {
        super(_titlu);
        deadline = null;
    }

    public void setDeadline(ZDate _deadline)
    {
        deadline = _deadline;
    }

    public ZDate getDeadline()
    {
        return deadline;
    }

    public void getInfo()
    {
        System.out.print("-job-related");
        if(deadline != null)
            System.out.print(": deadline: " + deadline.zi + "." + deadline.luna + "." + deadline.an);
        System.out.print("; ");
    }
}