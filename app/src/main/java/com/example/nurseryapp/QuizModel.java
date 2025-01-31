package com.example.nurseryapp;

public class QuizModel
{
    private int id;
    private int name;
    private int status;

    public QuizModel(int id, int name, int status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public int getName()
    {
        return name;
    }

    public int getStatus()
    {
        return status;
    }
}
