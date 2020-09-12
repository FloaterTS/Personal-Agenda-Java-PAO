package com.components;

public class AlarmReminder extends Alarm
{
    private String textRemind;

    public AlarmReminder(int _ora, int minut, String remind)
    {
        super(_ora, minut);
        textRemind = remind;
    }

    public void setTextRemind(String remind)
    {
        textRemind = remind;
    }

    public String getTextRemind()
    {
        return textRemind;
    }

    public void getInfo()
    {
        System.out.print("-reminder: " + textRemind + "; ");
    }
}
