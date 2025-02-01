package com.example.nurseryapp;

public class StaticQuizModel
{
    private static int id;
    private static String name;
    private static String status;

    public StaticQuizModel(int id, String name, String status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public static int getId()
    {
        return id;
    }

    public static String getName()
    {
        return name;
    }

    public static String getStatus()
    {
        return status;
    }
}
