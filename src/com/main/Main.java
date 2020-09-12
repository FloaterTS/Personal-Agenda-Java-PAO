package com.main;

import com.components.*;
import com.files.Audit;
import com.files.FileManager;
import com.helper.*;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Agenda agendaMain = Agenda.getAgenda();
        Service serv = Service.getService();
        FileManager fileManager = FileManager.getFileManager();
        Audit audit = Audit.getAudit();

        fileManager.loadAgenda();

        Scanner scn = new Scanner(System.in);
        int c, cc;
        do
        {
            System.out.println("Alegeti o actiune:");
            System.out.println("1. Adaugati in agenda");
            System.out.println("2. Afisati continutul agendei");
            System.out.println("3. Modificati continutul agendei");
            System.out.println("4. Stergeti continutul agendei");
            System.out.println("0. Save & Exit");
            c = scn.nextInt();
            scn.nextLine();
            switch (c)
            {
                case 1:
                {
                    System.out.println("Alegeti:");
                    System.out.println("1. Adaugati o sarcina");
                    System.out.println("2. Adaugati o intalnire");
                    System.out.println("3. Adaugati o alarma");
                    System.out.println("0. Back");
                    cc = scn.nextInt();
                    scn.nextLine();
                    switch (cc)
                    {
                        case 1:
                        {
                            boolean job, ob = false, imp;
                            String desc = null;
                            ZDate dt = null;
                            System.out.print("Titlu: ");
                            String num = scn.nextLine();
                            System.out.print("Adaugati descriere? (y/n): ");
                            String d = scn.nextLine();
                            if (d.equals("y"))
                            {
                                System.out.print("Descriere: ");
                                desc = scn.nextLine();
                            }
                            System.out.print("Job-related? (y/n): ");
                            String j = scn.nextLine();
                            job = j.equals("y");
                            if(job)
                            {
                                System.out.print("Setati deadline? (y/n): ");
                                String dl = scn.nextLine();
                                if (dl.equals("y"))
                                {
                                    System.out.print("Deadline-zi: ");
                                    int dzi = scn.nextInt();
                                    System.out.print("Deadline-luna: ");
                                    int dlu = scn.nextInt();
                                    System.out.print("Deadline-an: ");
                                    int dan = scn.nextInt();
                                    scn.nextLine();
                                    dt = new ZDate(dzi,dlu,dan);
                                }
                            }
                            else
                            {
                                System.out.print("Obligatorie? (y/n): ");
                                String o = scn.nextLine();
                                ob = o.equals("y");
                            }
                            System.out.print("Important? (y/n): ");
                            String im = scn.nextLine();
                            imp = im.equals("y");
                            serv.sAddTask(num, job, desc, dt, ob, imp);
                            System.out.println("Sarcina adaugata.\n");
                            audit.saveQuery("Adaugare sarcina");
                        }
                        break;
                        case 2:
                        {
                            boolean job, fm = true, wb = false, imp;
                            String desc = null;
                            int zi, luna, an;
                            System.out.print("Data-zi: ");
                            zi = scn.nextInt();
                            System.out.print("Data-luna: ");
                            luna = scn.nextInt();
                            System.out.print("Data-an: ");
                            an = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Adaugati descriere? (y/n): ");
                            String d = scn.nextLine();
                            if (d.equals("y"))
                            {
                                System.out.print("Descriere: ");
                                desc = scn.nextLine();
                            }
                            System.out.print("Job-related? (y/n): ");
                            String j = scn.nextLine();
                            job = j.equals("y");
                            if(job)
                            {
                                System.out.print("Cu conducerea? (y/n): ");
                                String mi = scn.nextLine();
                                wb = mi.equals("y");
                            }
                            else
                            {
                                System.out.print("De familie? (y/n): ");
                                String o = scn.nextLine();
                                fm = o.equals("y");
                            }
                            System.out.print("Important? (y/n): ");
                            String im = scn.nextLine();
                            imp = im.equals("y");
                            serv.sAddMeeting(zi, luna, an, job, desc, wb, fm, imp);
                            System.out.println("Intalnire adaugata.\n");
                            audit.saveQuery("Adaugare intalnire");
                        }
                        break;
                        case 3:
                        {
                            int ora, minut;
                            boolean imp;
                            String reminder = null;
                            System.out.print("Ora: ");
                            ora = scn.nextInt();
                            System.out.print("Minute: ");
                            minut = scn.nextInt();
                            scn.nextLine();
                            System.out.print("Reminder? (y/n): ");
                            String r = scn.nextLine();
                            if(r.equals("y"))
                            {
                                System.out.print("Remind: ");
                                reminder = scn.nextLine();
                            }
                            System.out.print("Important? (y/n): ");
                            String im = scn.nextLine();
                            imp = im.equals("y");
                            serv.sAddAlarm(ora, minut, reminder, imp);
                            System.out.println("Alarma adaugata.\n");
                            audit.saveQuery("Adaugare alarma");
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
                    System.out.println("Alegeti:");
                    System.out.println("1. Afisati sarcini existente");
                    System.out.println("2. Afisati intalniri existente");
                    System.out.println("3. Afisati alarme existente");
                    System.out.println("4. Afisati intreg continutul agendei");
                    System.out.println("5. Afisati continutul important al agendei");
                    System.out.println("0. Back");
                    cc = scn.nextInt();
                    scn.nextLine();
                    switch (cc)
                    {
                        case 1:
                        {
                            serv.selectTasks(-1, false);
                            audit.saveQuery("Afisare sarcini");
                        }
                        break;
                        case 2:
                        {
                            serv.selectMeets(-1, false);
                            audit.saveQuery("Afisare intalniri");
                        }
                        break;
                        case 3:
                        {
                            serv.selectAlarms(-1, false);
                            audit.saveQuery("Afisare alarme");
                        }
                        break;
                        case 4:
                        {
                            serv.selectAll(-1, false);
                            audit.saveQuery("Afisare continut intreg");
                        }
                        break;
                        case 5:
                        {
                            serv.selectAll(-1, true);
                            audit.saveQuery("Afisare continut important");
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
                    int noId, nrIds = serv.selectAll(0, false);
                    if(nrIds != 0)
                    {
                        System.out.print("Id corespunzator (1 - " + nrIds + "): ");
                        noId = scn.nextInt();
                        scn.nextLine();
                        if(noId <= 0)
                            System.out.println("Id invalid.\n");
                        else if(noId <= agendaMain.getAllTasks().size())
                        {
                            noId--;
                            //change a task
                            System.out.println("Alegeti:");
                            System.out.println("1. Schimbati titlu");
                            System.out.println("2. Schimbati descrierea");
                            System.out.println("3. Schimbati importanta");
                            if(agendaMain.getAllTasks().get(noId) instanceof TaskJob)
                                System.out.println("4. Schimbati deadline-ul");
                            else
                                System.out.println("4. Schimbati starea de obligatie");
                            System.out.println("5. Stergeti aceasta sarcina");
                            if(agendaMain.getAllTasks().get(noId) instanceof TaskJob)
                                System.out.println("6. Eliminati deadline-ul");
                            System.out.println("0. Back to menu");
                            cc = scn.nextInt();
                            scn.nextLine();
                            switch (cc)
                            {
                                case 1:
                                {
                                    System.out.print("Titlu nou: ");
                                    String titlu = scn.nextLine();
                                    agendaMain.getAllTasks().get(noId).setTitle(titlu);
                                    System.out.println("Titlul sarcinii a fost modificat.");
                                    audit.saveQuery("Schimbare titlu sarcina");
                                }
                                break;
                                case 2:
                                {
                                    System.out.print("Descriere noua: ");
                                    String desc = scn.nextLine();
                                    agendaMain.getAllTasks().get(noId).setDescriere(desc);
                                    System.out.println("Descrierea sarcinii a fost modificata.");
                                    audit.saveQuery("Schimbare descriere sarcina");
                                }
                                break;
                                case 3:
                                {
                                    agendaMain.getAllTasks().get(noId).setImportant(!agendaMain.getAllTasks().get(noId).getImportant());
                                    if(agendaMain.getAllTasks().get(noId).getImportant())
                                        System.out.println("Sarcina a fost setata ca fiind importanta!");
                                    else
                                        System.out.println("Sarcina a fost setata ca nemaifiind importanta.");
                                    audit.saveQuery("Schimbare importanta sarcina");
                                }
                                break;
                                case 4:
                                {
                                    if(agendaMain.getAllTasks().get(noId) instanceof TaskJob)
                                    {
                                        System.out.print("Deadline nou:\n");
                                        int zi, luna, an;
                                        System.out.print("Zi: ");
                                        zi = scn.nextInt();
                                        System.out.print("Luna: ");
                                        luna = scn.nextInt();
                                        System.out.print("An: ");
                                        an = scn.nextInt();
                                        scn.nextLine();
                                        ((TaskJob) agendaMain.getAllTasks().get(noId)).setDeadline(new ZDate(zi, luna, an));
                                        System.out.println("Deadline-ul sarcinii a fost modificat.");
                                        audit.saveQuery("Schimbare deadline sarcina");
                                    }
                                    else
                                    {
                                        ((TaskHome)agendaMain.getAllTasks().get(noId)).setMust(!((TaskHome)agendaMain.getAllTasks().get(noId)).getMust());
                                        if(((TaskHome)agendaMain.getAllTasks().get(noId)).getMust())
                                            System.out.println("Sarcina a fost setata ca fiind obligatorie.");
                                        else
                                            System.out.println("Sarcina a fost setata ca fiind optionala.");
                                        audit.saveQuery("Schimbare optionalitate sarcina ");
                                    }
                                }
                                break;
                                case 5:
                                {
                                    agendaMain.removeTaskById(noId);
                                    System.out.println("Sarcina a fost stearsa.");
                                    audit.saveQuery("Stergere sarcina");
                                }
                                break;
                                case 6:
                                {
                                    if(agendaMain.getAllTasks().get(noId) instanceof TaskJob)
                                    {
                                        ((TaskJob) agendaMain.getAllTasks().get(noId)).setDeadline(null);
                                        System.out.println("Deadline-ul a fost eliminat.");
                                        audit.saveQuery("Eliminare deadline sarcina");
                                    }
                                    else
                                        System.out.println("Optiune invalida.");
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
                        else if(noId <= agendaMain.getAllTasks().size() + agendaMain.getAllMeets().size())
                        {
                            noId -= agendaMain.getAllTasks().size();
                            noId--;
                            //change a meeting
                            System.out.println("Alegeti:");
                            System.out.println("1. Schimbati data");
                            System.out.println("2. Schimbati descrierea");
                            System.out.println("3. Schimbati importanta");
                            if(agendaMain.getAllMeets().get(noId) instanceof MeetingJob)
                                System.out.println("4. Schimbati daca intalnirea este cu conducerea");
                            else
                                System.out.println("4. Schimbati daca intalnirea este cu familia");
                            System.out.println("5. Stergeti aceasta intalnire");
                            System.out.println("0. Back to menu");
                            cc = scn.nextInt();
                            scn.nextLine();
                            switch (cc)
                            {
                                case 1:
                                {
                                    System.out.println("Data noua:");
                                    int zi, luna, an;
                                    System.out.print("Zi: ");
                                    zi = scn.nextInt();
                                    System.out.print("Luna: ");
                                    luna = scn.nextInt();
                                    System.out.print("An: ");
                                    an = scn.nextInt();
                                    scn.nextLine();
                                    agendaMain.getAllMeets().get(noId).setDate(new ZDate(zi, luna, an));
                                    Collections.sort(agendaMain.getAllMeets());
                                    System.out.println("Data intalnirii a fost modificata.");
                                    audit.saveQuery("Schimbare data intalnire");
                                }
                                break;
                                case 2:
                                {
                                    System.out.print("Descriere noua: ");
                                    String desc = scn.nextLine();
                                    agendaMain.getAllMeets().get(noId).setDescriere(desc);
                                    System.out.println("Descrierea intalnirii a fost modificata.");
                                    audit.saveQuery("Schimbare descriere intalnire");
                                }
                                break;
                                case 3:
                                {
                                    agendaMain.getAllMeets().get(noId).setImportant(!agendaMain.getAllMeets().get(noId).getImportant());
                                    if(agendaMain.getAllMeets().get(noId).getImportant())
                                        System.out.println("Intalnirea a fost setata ca fiind importanta!");
                                    else
                                        System.out.println("Intalnirea a fost setata ca nemaifiind importanta.");
                                    audit.saveQuery("Schimbare importanta intalnire");
                                }
                                break;
                                case 4:
                                {
                                    if(agendaMain.getAllMeets().get(noId) instanceof MeetingJob)
                                    {
                                        ((MeetingJob)agendaMain.getAllMeets().get(noId)).setWithBoss(!((MeetingJob)agendaMain.getAllMeets().get(noId)).getWithBoss());
                                        if(((MeetingJob)agendaMain.getAllMeets().get(noId)).getWithBoss())
                                            System.out.println("Intalnirea a fost setata ca fiind cu conducerea.");
                                        else
                                            System.out.println("Intalnirea a fost setata ca fiind cazuala.");
                                        audit.saveQuery("Schimbare cazualitate intalnire");
                                    }
                                    else
                                    {
                                        ((MeetingHome)agendaMain.getAllMeets().get(noId)).setFamily(!((MeetingHome)agendaMain.getAllMeets().get(noId)).getFamily());
                                        if(((MeetingHome)agendaMain.getAllMeets().get(noId)).getFamily())
                                            System.out.println("Intalnirea a fost setata ca fiind cu familia.");
                                        else
                                            System.out.println("Intalnirea a fost setata ca fiind personala.");
                                        audit.saveQuery("Schimbare colectivitate intalnire");
                                    }
                                }
                                break;
                                case 5:
                                {
                                    agendaMain.removeMeetingById(noId);
                                    System.out.println("Intalnirea a fost stearsa.");
                                    audit.saveQuery("Stergere intalnire");
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
                        else if(noId <= agendaMain.getAllTasks().size() + agendaMain.getAllMeets().size() + agendaMain.getAllAlarms().size())
                        {
                            noId -= (agendaMain.getAllTasks().size() + agendaMain.getAllMeets().size());
                            noId--;
                            //change an alarm
                            System.out.println("Alegeti:");
                            System.out.println("1. Schimbati ora");
                            System.out.println("2. Schimbati importanta");
                            System.out.println("3. Stergeti aceasta alarma");
                            if(agendaMain.getAlarmById(noId) instanceof AlarmReminder)
                                System.out.println("4. Schimbati reminder-ul");
                            System.out.println("0. Back to menu");
                            cc = scn.nextInt();
                            scn.nextLine();
                            switch (cc)
                            {
                                case 1:
                                {
                                    System.out.println("Ora noua:");
                                    int ora, minute;
                                    System.out.print("Ora: ");
                                    ora = scn.nextInt();
                                    System.out.print("Minute: ");
                                    minute = scn.nextInt();
                                    scn.nextLine();
                                    agendaMain.getAlarmById(noId).setTime(new ZTime(ora, minute));
                                    System.out.println("Ora alarmei a fost modificata.");
                                    audit.saveQuery("Schimbare ora alarma");
                                }
                                break;
                                case 2:
                                {
                                    agendaMain.getAlarmById(noId).setImportant(!agendaMain.getAlarmById(noId).getImportant());
                                    if(agendaMain.getAlarmById(noId).getImportant())
                                        System.out.println("Alarma a fost setata ca fiind importanta!");
                                    else
                                        System.out.println("Alarma a fost setata ca nemaifiind importanta.");
                                    audit.saveQuery("Schimbare importanta alarma");
                                }
                                break;
                                case 3:
                                {
                                    agendaMain.removeAlarmById(noId);
                                    System.out.println("Alarma a fost stearsa.");
                                    audit.saveQuery("Stergere alarma");
                                }
                                break;
                                case 4:
                                {
                                    if(agendaMain.getAlarmById(noId) instanceof AlarmReminder)
                                    {
                                        System.out.print("Remind: ");
                                        String rem = scn.nextLine();
                                        ((AlarmReminder)agendaMain.getAlarmById(noId)).setTextRemind(rem);
                                        System.out.println("Reminderul a fost modificat.");
                                        audit.saveQuery("Schimbare reminder");
                                    }
                                    else
                                        System.out.println("Optiune invalida.");
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
                        else
                            System.out.println("Id invalid.");
                    }
                    else
                        System.out.println("Nu exista nimic de modificat (agenda este goala).");
                    System.out.print("\n");
                }
                break;
                case 4:
                {
                    serv.deleteContents();
                    System.out.println("Continutul agendei a fost sters.\n");
                    audit.saveQuery("Stergere intreg continut al agendei");
                }
                break;
                case 0:
                {
                    audit.closeQuery();
                    fileManager.saveAgenda();
                }
                break;
                default:
                {
                    System.out.println("Optiune invalida.");
                }
            }
        } while (c != 0);

    }

}
