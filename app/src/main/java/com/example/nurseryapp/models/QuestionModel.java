package com.example.nurseryapp.models;

public class QuestionModel
{
    private int id;
    private String question;
    private String answer;
    private int quiz_id;

    public QuestionModel(int id, String question, String answer, int quiz_id)
    {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.quiz_id = quiz_id;
    }

    public int getId()
    {
        return id;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public int getQuiz_id()
    {
        return quiz_id;
    }
}
