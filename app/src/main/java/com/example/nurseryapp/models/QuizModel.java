package com.example.nurseryapp.models;

public class QuizModel
{
    private int id;
    private String name;
    private String status;

    public QuizModel(int id, String name, String status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String newStatus)
    {
        this.status = newStatus;
    }
}
