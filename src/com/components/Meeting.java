package com.components;
import com.helper.ZDate;
import com.helper.Function;

public abstract class Meeting implements Function, Comparable<Meeting>
{
    protected String descriere;
    protected ZDate data;
    protected boolean important;
    protected int id;

    public Meeting(int zi, int luna, int an)
    {
        data = new ZDate(zi, luna, an);
        descriere = null;
    }

    public void setDate(ZDate date)
    {
        data = date;
    }

    public void setDescriere(String _descriere)
    {
        descriere = _descriere;
    }

    public ZDate getDate()
    {
        return data;
    }

    public String getDesc()
    {
        return descriere;
    }

    public void setImportant(boolean important)
    {
        this.important = important;
    }

    public boolean getImportant()
    {
        return important;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int compareTo(Meeting o)
    {
        if(data.an == o.data.an)
        {
            if(data.luna == o.data.luna)
                return data.zi - o.data.zi;
            else
                return data.luna - o.data.luna;
        }
        else
                return data.an - o.data.an;
    }
}