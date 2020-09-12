package com.components;
import com.helper.Function;

public abstract class Task implements Function
{
    protected String titlu;
    protected String descriere;
    protected boolean  important;
    protected int id;

    public Task(String _titlu)
    {
        titlu = _titlu;
        descriere = null;
    }

    public void setTitle(String title)
    {
        titlu = title;
    }

    public void setDescriere(String _descriere)
    {
        descriere = _descriere;
    }

    public String getTitle()
    {
        return titlu;
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
}







