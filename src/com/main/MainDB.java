package com.main;

import com.components.*;
import com.files.Audit;
import com.helper.ZDate;
import com.connection.DatabaseConnection;

import java.util.*;

public class MainDB
{
    public static void main(String[] args)
    {
        ServiceDB serv = ServiceDB.getService();

        try
        {
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
        }
        catch (Exception e)
        {
            System.out.println("There was a problem obtaining the database connection");
        }


        Scanner scn = new Scanner(System.in);
        int c, cc;
        do
        {
            System.out.println("Alegeti o actiune:");
            System.out.println("1. Insert");
            System.out.println("2. Select");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Exit");
            c = scn.nextInt();
            scn.nextLine();
            switch (c)
            {
                case 1:
                {
                    System.out.println("Alegeti o actiune:");
                    System.out.println("1. Insert taskHome");
                    System.out.println("2. Insert taskJob");
                    System.out.println("3. Insert meetingHome");
                    System.out.println("4. Insert meetingJob");
                    System.out.println("0. Back");
                    cc = scn.nextInt();
                    scn.nextLine();
                    switch (cc)
                    {
                        case 1:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Titlu = ");
                            String titlu = scn.nextLine();
                            System.out.print("Descriere = ");
                            String desc = scn.nextLine();
                            System.out.print("Obligatorie (y/n)? ");
                            boolean obl = scn.nextLine().equals("y");
                            serv.saveTaskHome(id, titlu, desc, obl);
                        }
                        break;
                        case 2:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Titlu = ");
                            String titlu = scn.nextLine();
                            System.out.print("Descriere = ");
                            String desc = scn.nextLine();
                            System.out.print("Deadline-zi = ");
                            int zi = scn.nextInt();
                            System.out.print("Deadline-luna = ");
                            int luna = scn.nextInt();
                            System.out.print("Deadline-an = ");
                            int an = scn.nextInt();
                            serv.saveTaskJob(id, titlu, desc, zi, luna, an);
                        }
                        break;
                        case 3:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            System.out.print("Data-zi = ");
                            int zi = scn.nextInt();
                            System.out.print("Data-luna = ");
                            int luna = scn.nextInt();
                            System.out.print("Data-an = ");
                            int an = scn.nextInt();
                            System.out.print("Descriere = ");
                            String desc = scn.nextLine();
                            System.out.print("Cu familia (y/n)? ");
                            boolean fam = scn.nextLine().equals("y");
                            serv.saveMeetHome(id, zi, luna, an, desc, fam);
                        }
                        break;
                        case 4:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            System.out.print("Data-zi = ");
                            int zi = scn.nextInt();
                            System.out.print("Data-luna = ");
                            int luna = scn.nextInt();
                            System.out.print("Data-an = ");
                            int an = scn.nextInt();
                            System.out.print("Descriere = ");
                            String desc = scn.nextLine();
                            System.out.print("Cu conducerea (y/n)? ");
                            boolean boss = scn.nextLine().equals("y");
                            serv.saveMeetHome(id, zi, luna, an, desc, boss);
                        }
                        break;
                        case 0:
                            break;
                        default:
                        {
                            System.out.println("Optiune invalida.");
                        }
                    }
                }
                break;
                case 2:
                {
                    System.out.println("Alegeti o actiune:");
                    System.out.println("1. Select taskHome");
                    System.out.println("2. Select taskJob");
                    System.out.println("3. Select meetingHome");
                    System.out.println("4. Select meetingJob");
                    System.out.println("0. Back");
                    cc = scn.nextInt();
                    scn.nextLine();
                    switch (cc)
                    {
                        case 1:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            TaskHome th = serv.findTaskHome(id);
                            System.out.println("Titlu = " + th.getTitle());
                            System.out.println("Descriere = " + th.getDesc());
                            System.out.println(th.getMust() ? "Obligatorie" : "Optionala");
                        }
                        break;
                        case 2:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            TaskJob tj = serv.findTaskJob(id);
                            System.out.println("Titlu = " + tj.getTitle());
                            System.out.println("Descriere = " + tj.getDesc());
                            ZDate dd = tj.getDeadline();
                            System.out.println("Deadline = " + dd.zi + "-" + dd.luna + "-" + dd.an);
                        }
                        break;
                        case 3:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            MeetingHome mh = serv.findMeetHome(id);
                            ZDate d = mh.getDate();
                            System.out.println("Data = " + d.zi + "-" + d.luna + "-" + d.an);
                            System.out.println("Descriere = " + mh.getDesc());
                            System.out.println(mh.getFamily() ? "De familie" : "Personala");
                        }
                        break;
                        case 4:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            MeetingJob mj = serv.findMeetJob(id);
                            ZDate d = mj.getDate();
                            System.out.println("Data = " + d.zi + "-" + d.luna + "-" + d.an);
                            System.out.println("Descriere = " + mj.getDesc());
                            System.out.println(mj.getWithBoss() ? "Cu conducerea" : "cazuala");
                        }
                        break;
                        case 0:
                            break;
                        default:
                        {
                            System.out.println("Optiune invalida.");
                        }
                    }
                }
                break;
                case 3:
                {
                    System.out.println("Alegeti o actiune:");
                    System.out.println("1. Update taskHome");
                    System.out.println("2. Update taskJob");
                    System.out.println("3. Update meetingHome");
                    System.out.println("4. Update meetingJob");
                    System.out.println("0. Back");
                    cc = scn.nextInt();
                    scn.nextLine();
                    switch (cc)
                    {
                        case 1:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Titlu nou = ");
                            String newt = scn.nextLine();
                            serv.updateTaskHome(id, newt);
                        }
                        break;
                        case 2:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Titlu nou = ");
                            String newt = scn.nextLine();
                            serv.updateTaskJob(id, newt);
                        }
                        break;
                        case 3:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Descriere noua = ");
                            String newd = scn.nextLine();
                            serv.updateMeetHome(id, newd);
                        }
                        break;
                        case 4:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Descriere noua = ");
                            String newd = scn.nextLine();
                            serv.updateMeetJob(id, newd);
                        }
                        break;
                        case 0:
                            break;
                        default:
                        {
                            System.out.print("Optiune invalida.");
                        }
                    }
                }
                break;
                case 4:
                {
                    System.out.println("Alegeti o actiune:");
                    System.out.println("1. Delete taskHome");
                    System.out.println("2. Delete taskJob");
                    System.out.println("3. Delete meetingHome");
                    System.out.println("4. Delete meetingJob");
                    System.out.println("0. Back");
                    cc = scn.nextInt();
                    scn.nextLine();
                    switch (cc)
                    {
                        case 1:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            serv.deleteTaskHome(id);
                        }
                        break;
                        case 2:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            serv.deleteTaskJob(id);
                        }
                        break;
                        case 3:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            serv.deleteMeetHome(id);
                        }
                        break;
                        case 4:
                        {
                            System.out.print("Id = ");
                            int id = scn.nextInt();
                            serv.deleteMeetJob(id);
                        }
                        break;
                        case 0:
                            break;
                        default:
                        {
                            System.out.println("Optiune invalida.");
                        }
                    }
                }
                break;
                case 0:
                    break;
                default:
                {
                    System.out.println("Optiune invalida.");
                }
            }
        } while (c != 0);


    }

}
