package com.components;
import com.helper.ZTime;
import com.helper.Function;
import java.util.Objects;

public class Alarm implements Function
{
    protected ZTime ora;
    protected boolean important;

    public Alarm(int _ora, int minut)
    {
        ora = new ZTime(_ora, minut);
    }

    public void setTime(ZTime hour)
    {
        ora = hour;
    }

    public ZTime getTime()
    {
        return ora;
    }

    public void getInfo()
    {
        System.out.print("-alarma; ");
    }

    public void setImportant(boolean important)
    {
        this.important = important;
    }

    public boolean getImportant()
    {
        return important;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alarm alarm = (Alarm) o;
        return ora.ora == alarm.ora.ora && ora.minute == alarm.ora.minute;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ora.ora, ora.minute);
    }
}