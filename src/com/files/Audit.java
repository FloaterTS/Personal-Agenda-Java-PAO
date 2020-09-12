package com.files;

import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.Date;

public class Audit
{
    private static Audit audit = null;

    private FileWriter auditWriter;
    private Date date;
    private long time;
    private Timestamp ts;

    private Audit()
    {
        try{
        String auditFile = "files//audit.csv";
        auditWriter = new FileWriter(auditFile);

        date = new Date();
        time = date.getTime();
        ts = new Timestamp(time);

        auditWriter.write( "Query pornit, " + ts + "\n");
        auditWriter.flush();
        }
        catch (Exception e)
        {
            System.out.println("An error occured.");
        }
    }

    public static Audit getAudit()
    {
        if (audit == null)
            audit = new Audit();
        return audit;
    }

    public void saveQuery(String cmd)
    {
        date = new Date();
        time = date.getTime();
        ts = new Timestamp(time);

        try
        {
            auditWriter.append(cmd).append(", ").append(String.valueOf(ts)).append("\n");
            auditWriter.flush();
        }
        catch (Exception e)
        {
            System.out.println("An error occured.");
        }
    }

    public void closeQuery()
    {
        date = new Date();
        time = date.getTime();
        ts = new Timestamp(time);

        try
        {
            auditWriter.append("Query terminat, ").append(String.valueOf(ts));
            auditWriter.flush();

            auditWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("An error occured.");
        }
    }
}








