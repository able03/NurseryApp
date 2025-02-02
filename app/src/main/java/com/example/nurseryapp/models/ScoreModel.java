package com.example.nurseryapp.models;

public class ScoreModel
{
    private int score_id;
    private int quiz_id;
    private int user_id;
    private int score;

    public ScoreModel(int score_id, int quiz_id, int user_id, int score)
    {
        this.score_id = score_id;
        this.quiz_id = quiz_id;
        this.user_id = user_id;
        this.score = score;
    }

    public int getScore_id()
    {
        return score_id;
    }

    public int getQuiz_id()
    {
        return quiz_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public int getScore()
    {
        return score;
    }
}
