package com.files;

import com.components.*;
import com.helper.ZDate;
import com.main.Agenda;
import com.main.Service;

import java.io.*;

public class FileMeetMgr
{
    private static FileMeetMgr meet_file_mgr = null;

    private final Agenda agenda;
    private final Service serv;
    private final String meetsFile = "files//meetings.csv";

    private FileMeetMgr()
    {
        agenda = Agenda.getAgenda();
        serv = Service.getService();
    }

    public static FileMeetMgr getMeet_file_mgr()
    {
        if (meet_file_mgr == null)
            meet_file_mgr = new FileMeetMgr();
        return meet_file_mgr;
    }


    public void saveMeetings()
    {
        try
        {
            FileWriter meetFileWriter = new FileWriter(meetsFile);
            PrintWriter meetPrintWriter = new PrintWriter(meetFileWriter);

            for (Meeting meet : agenda.getAllMeets())
            {
                meetPrintWriter.printf("%d,%d,%d,%s,%d,", meet.getDate().zi, meet.getDate().luna, meet.getDate().an, meet.getDesc(), meet.getImportant() ? 1 : 0);
                if (meet instanceof MeetingJob)
                    meetPrintWriter.printf("%d,%d\n", 1, ((MeetingJob) meet).getWithBoss() ? 1 : 0);
                else
                    meetPrintWriter.printf("%d,%d\n", 0, ((MeetingHome) meet).getFamily() ? 1 : 0);
            }
            meetPrintWriter.flush();
            meetPrintWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occured.");
        }
    }

    public void loadMeetings()
    {
        BufferedReader meetReader;
        try
        {
            meetReader = new BufferedReader(new FileReader(meetsFile));

            String line;
            while ((line = meetReader.readLine()) != null)
            {
                String[] meetings = line.split(",");
                ZDate date = new ZDate(Integer.parseInt(meetings[0]), Integer.parseInt(meetings[1]), Integer.parseInt(meetings[2]));
                String desc = meetings[3].equals("null") ? null : meetings[3];
                boolean imp = Integer.parseInt(meetings[4]) != 0;
                boolean job = Integer.parseInt(meetings[5]) != 0;
                boolean boss = false;
                boolean fam = false;
                if (job)
                    boss = Integer.parseInt(meetings[6]) != 0;
                else
                    fam = Integer.parseInt(meetings[6]) != 0;
                serv.sAddMeeting(date.zi, date.luna, date.an, job, desc, boss, fam, imp);
            }
            meetReader.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occured.");
        }
    }
}
