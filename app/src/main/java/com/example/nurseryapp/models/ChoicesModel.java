package com.example.nurseryapp.models;

public class ChoicesModel
{
    private int id;
    private String choice;
    private int question_id;

    public ChoicesModel(int id, String choice, int question_id)
    {
        this.id = id;
        this.choice = choice;
        this.question_id = question_id;
    }

    public int getId()
    {
        return id;
    }

    public String getChoice()
    {
        return choice;
    }

    public int getQuestion_id()
    {
        return question_id;
    }
}
