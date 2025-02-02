package com.example.nurseryapp.models;

public class QuestionV2Model
{
    private int id;
    private String question_type;
    private String question;
    private String answer;
    private int quiz_id;


    public QuestionV2Model(int id, String question_type, String question, String answer, int quiz_id)
    {
        this.id = id;
        this.question_type = question_type;
        this.question = question;
        this.answer = answer;
        this.quiz_id = quiz_id;
    }

    public int getId()
    {
        return id;
    }

    public String getQuestion_type()
    {
        return question_type;
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
