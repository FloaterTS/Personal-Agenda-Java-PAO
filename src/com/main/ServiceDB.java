package com.main;

import com.components.*;
import com.helper.*;

import java.util.*;

import com.repository.*;

public class ServiceDB
{
    private static ServiceDB service = null;

    private final TaskHomeRepository taskHomeRepository = TaskHomeRepository.getInstance();
    private final TaskJobRepository taskJobRepository = TaskJobRepository.getInstance();
    private final MeetHomeRepository meetHomeRepository = MeetHomeRepository.getInstance();
    private final MeetJobRepository meetJobRepository = MeetJobRepository.getInstance();

    private ServiceDB()
    {
    }

    public static ServiceDB getService()
    {
        if (service == null)
            service = new ServiceDB();
        return service;
    }


    public void saveTaskHome(int id, String title, String desc, boolean must)
    {
        TaskHome task = new TaskHome(title);
        task.setId(id);
        task.setDescriere(desc);
        task.setMust(must);
        taskHomeRepository.save(task);
    }

    public TaskHome findTaskHome(int id)
    {
        return taskHomeRepository.find(id);
    }

    public void updateTaskHome(int id, String title)
    {
        taskHomeRepository.update(id, title);
    }

    public void deleteTaskHome(int id)
    {
        taskHomeRepository.delete(id);
    }

    public void saveTaskJob(int id, String title, String desc, int zi, int luna, int an)
    {
        TaskJob task = new TaskJob(title);
        task.setId(id);
        task.setDescriere(desc);
        task.setDeadline(new ZDate(zi, luna, an));
        taskJobRepository.save(task);
    }

    public TaskJob findTaskJob(int id)
    {
        return taskJobRepository.find(id);
    }

    public void updateTaskJob(int id, String title)
    {
        taskJobRepository.update(id, title);
    }

    public void deleteTaskJob(int id)
    {
        taskJobRepository.delete(id);
    }

    public void saveMeetHome(int id, int zi, int luna, int an, String desc, boolean family)
    {
        MeetingHome meet = new MeetingHome(zi, luna, an);
        meet.setId(id);
        meet.setDescriere(desc);
        meet.setFamily(family);
        meetHomeRepository.save(meet);
    }

    public MeetingHome findMeetHome(int id)
    {
        return meetHomeRepository.find(id);
    }

    public void updateMeetHome(int id, String desc)
    {
        meetHomeRepository.update(id, desc);
    }

    public void deleteMeetHome(int id)
    {
        meetHomeRepository.delete(id);
    }

    public void saveMeetJob(int id, int zi, int luna, int an, String desc, boolean withBoss)
    {
        MeetingJob meet = new MeetingJob(zi, luna, an);
        meet.setId(id);
        meet.setDescriere(desc);
        meet.setWithBoss(withBoss);
        meetJobRepository.save(meet);
    }

    public MeetingJob findMeetJob(int id)
    {
        return meetJobRepository.find(id);
    }

    public void updateMeetJob(int id, String desc)
    {
        meetJobRepository.update(id, desc);
    }

    public void deleteMeetJob(int id)
    {
        meetJobRepository.delete(id);
    }
}