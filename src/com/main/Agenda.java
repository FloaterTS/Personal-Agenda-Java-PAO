package com.main;

import com.components.*;
import java.util.*;

public class Agenda
{
    private List<Task> tasks;
    private List<Meeting> meets;
    private Set<Alarm> alarms;

    private static Agenda agendaMain = null;

    private Agenda()
    {
        tasks = new ArrayList<>();
        meets = new ArrayList<>();
        alarms = new HashSet<>();
    }

    public static Agenda getAgenda()
    {
        if(agendaMain == null)
            agendaMain = new Agenda();
        return agendaMain;
    }

    public List<Task> getAllTasks()
    {
        return tasks;
    }

    public List<Meeting> getAllMeets()
    {
        return meets;
    }

    public Set<Alarm> getAllAlarms()
    {
        return alarms;
    }

    public void addTask(Task t)
    {
        tasks.add(t);
    }

    public void addMeeting(Meeting m)
    {
        meets.add(m);
    }

    public void addAlarm(Alarm a)
    {
        alarms.add(a);
    }

    public void removeTaskById(int id)
    {
        tasks.remove(id);
    }

    public void removeMeetingById(int id)
    {
        meets.remove(id);
    }

    public void removeAlarmById(int id)
    {
        alarms.remove(getAlarmById(id));
    }

    public void deleteAll()
    {
        tasks.clear();
        meets.clear();
        alarms.clear();
    }

    public Alarm getAlarmById(int id)
    {
        int i = 0;
        for(Alarm alarm : alarms)
        {
            if(i == id)
                return alarm;
            i++;
        }
        return null;
    }
}
