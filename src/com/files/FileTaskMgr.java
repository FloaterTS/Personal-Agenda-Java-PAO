package com.files;

import com.components.*;
import com.helper.ZDate;
import com.main.Agenda;
import com.main.Service;

import java.io.*;

public class FileTaskMgr
{
    private static FileTaskMgr task_file_mgr = null;

    private final Agenda agenda;
    private final Service serv;
    private final String tasksFile = "files//tasks.csv";

    private FileTaskMgr()
    {
        agenda = Agenda.getAgenda();
        serv = Service.getService();
    }

    public static FileTaskMgr getTask_file_mgr()
    {
        if (task_file_mgr == null)
            task_file_mgr = new FileTaskMgr();
        return task_file_mgr;
    }

    public void saveTasks()
    {
        try
        {
            FileWriter taskFileWriter = new FileWriter(tasksFile);
            PrintWriter taskPrintWriter = new PrintWriter(taskFileWriter);

            for (Task task : agenda.getAllTasks())
            {
                taskPrintWriter.printf("%s,%s,%d,", task.getTitle(), task.getDesc(), task.getImportant() ? 1 : 0);
                if (task instanceof TaskJob)
                {
                    int zi = 0, luna = 0, an = 0;
                    if (((TaskJob) task).getDeadline() != null)
                    {
                        zi = ((TaskJob) task).getDeadline().zi;
                        luna = ((TaskJob) task).getDeadline().luna;
                        an = ((TaskJob) task).getDeadline().an;
                    }
                    taskPrintWriter.printf("%d,%d,%d,%d\n", 1, zi, luna, an);
                } else
                    taskPrintWriter.printf("%d,%d\n", 0, ((TaskHome) task).getMust() ? 1 : 0);
            }
            taskPrintWriter.flush();
            taskPrintWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occured.");
        }
    }

    public void loadTasks()
    {
        BufferedReader taskReader;
        try
        {
            taskReader = new BufferedReader(new FileReader(tasksFile));

            String line;
            while ((line = taskReader.readLine()) != null)
            {
                String[] tasks = line.split(",");
                String name = tasks[0];
                String desc = tasks[1].equals("null") ? null : tasks[1];
                boolean imp = Integer.parseInt(tasks[2]) != 0;
                boolean job = Integer.parseInt(tasks[3]) != 0;
                boolean must = false;
                ZDate dl = null;
                if (job)
                {
                    int zi, luna, an;
                    zi = Integer.parseInt(tasks[4]);
                    luna = Integer.parseInt(tasks[5]);
                    an = Integer.parseInt(tasks[6]);
                    if (zi != 0 || luna != 0 || an != 0)
                        dl = new ZDate(zi, luna, an);
                } else
                    must = Integer.parseInt(tasks[4]) != 0;
                serv.sAddTask(name, job, desc, dl, must, imp);
            }
            taskReader.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occured.");
        }
    }
}
