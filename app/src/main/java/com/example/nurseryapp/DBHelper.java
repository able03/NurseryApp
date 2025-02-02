package com.example.nurseryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nurseryapp.models.ChoicesModel;
import com.example.nurseryapp.models.QuestionModel;
import com.example.nurseryapp.models.QuestionV2Model;
import com.example.nurseryapp.models.QuizModel;
import com.example.nurseryapp.models.ScoreModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(@Nullable Context context)
    {
        super(context, "Nursery.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE users(user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_type TEXT, name TEXT, username TEXT, password TEXT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE quizzes(quiz_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, status TEXT)");
        db.execSQL("CREATE TABLE quiz_scores(score_id INTEGER PRIMARY KEY AUTOINCREMENT, quiz_id INTEGER, user_id INTEGER, score INTEGER)");
        db.execSQL("CREATE TABLE choices(choice_id INTEGER PRIMARY KEY AUTOINCREMENT, choice TEXT, question_id INTEGER)");
        db.execSQL("CREATE TABLE questions(id INTEGER PRIMARY KEY AUTOINCREMENT, question_type TEXT, question TEXT, answer TEXT, quiz_id INTEGER)");
    }


    public void createDefaultTeacher() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if a teacher account already exists
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users WHERE user_type = ?", new String[]{"teacher"});

        if (cursor.moveToFirst() && cursor.getInt(0) == 0) { // No teacher exists, insert new one
            ContentValues values = new ContentValues();
            values.put("user_type", "teacher");
            values.put("name", "Default Teacher");
            values.put("username", "teacher");
            values.put("password", "password123"); // Change this for security

            db.insert("users", null, values);
        }

        cursor.close();
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS quizzes");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS quiz_scores");
        db.execSQL("DROP TABLE IF EXISTS choices");
        db.execSQL("DROP TABLE IF EXISTS questions_v2");
        onCreate(db);
    }


    public int getTotalUserCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        return count;
    }

    public Cursor getUser(String user, String pw){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{user, pw});


    }

    public Cursor getQuestions()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("questions", null, null, null, null, null, null);
    }


    public Cursor getQuestionsV2()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("questions", null, null, null, null, null, null);
    }

    public ArrayList<QuizModel> getQuizzes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quizzes", null);

        ArrayList<QuizModel> quizzes = new ArrayList<>();
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("quiz_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

                quizzes.add(new QuizModel(id, name, status));
            }
        }

        return quizzes;

    }

    public boolean createUser(String type, String name,  String uname, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_type", type);
        values.put("name", name);
        values.put("username", uname);
        values.put("password", password);
        return db.insert("users", null, values) != -1;
    }

    public void deleteUsersOlderThanOneMonth() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM users WHERE created_at < (datetime('now', '-1 month'))";

        db.execSQL(query);
    }

    public int getRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }


    public int createQuiz(String name, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("status", status);

        long quizId = db.insert("quizzes", null, values);
        return (int) quizId;
    }

    public boolean createQuestion(String question, String answer, int quiz_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("answer", answer);
        values.put("quiz_id", quiz_id);
        return db.insert("questions", null, values) != -1;
    }

    public int createQuestionV2(String question_type, String question, String answer, int quiz_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question_type", question_type);
        values.put("question", question);
        values.put("answer", answer);
        values.put("quiz_id", quiz_id);
        return (int) db.insert("questions", null, values);
    }



    public ArrayList<QuestionModel> getQuestions(int quiz_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "quiz_id = ?";
        String[] selectionArgs = {String.valueOf(quiz_id)};

        Cursor cursor = db.query("questions", null , selection, selectionArgs, null, null, null);

        ArrayList<QuestionModel> questions = new ArrayList<>();
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));

                String question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
                String answer = cursor.getString(cursor.getColumnIndexOrThrow("answer"));
                int quiz = cursor.getInt(cursor.getColumnIndexOrThrow("quiz_id"));

                questions.add(new QuestionModel(id, question, answer, quiz));
            }
        }

        return questions;
    }

    public ArrayList<QuestionV2Model> getQuestionsV2(int quiz_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "quiz_id = ?";
        String[] selectionArgs = {String.valueOf(quiz_id)};

        Cursor cursor = db.query("questions", null , selection, selectionArgs, null, null, null);

        ArrayList<QuestionV2Model> questions = new ArrayList<>();
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));

                String question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
                String answer = cursor.getString(cursor.getColumnIndexOrThrow("answer"));
                int quiz = cursor.getInt(cursor.getColumnIndexOrThrow("quiz_id"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("question_type"));

                questions.add(new QuestionV2Model(id, type,  question, answer, quiz));
            }
        }

        return questions;
    }

    public boolean insertChoice(String choice, int question_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("choice", choice);
        values.put("question_id", question_id);
        return db.insert("choices", null, values) != -1;
    }

    public ArrayList<ChoicesModel> getChoices(int question_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "question_id = ?";
        String[] selectionArgs = {String.valueOf(question_id)};
        Cursor cursor = db.query("choices", null, selection, selectionArgs, null, null, null);
        ArrayList<ChoicesModel> choices = new ArrayList<>();
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
                {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("choice_id"));
                String choice = cursor.getString(cursor.getColumnIndexOrThrow("choice"));
                int question = cursor.getInt(cursor.getColumnIndexOrThrow("question_id"));
                choices.add(new ChoicesModel(id, choice, question));
            }

        }

        return choices;

    }


    public boolean checkAnswer(int question_id, String answer)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "id = ? AND answer = ?";
        String[] selectionArgs = {String.valueOf(question_id), answer};
        Cursor cursor = db.query("questions", null, selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst())
        {
            String correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow("answer"));
            cursor.close();
            return correctAnswer.equalsIgnoreCase(answer);
        }

        cursor.close();
        return false;
    }

    public boolean insertScore(int quiz_id, int user_id, int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quiz_id", quiz_id);
        values.put("user_id", user_id);
        values.put("score", score);

        return db.insert("quiz_scores", null, values) != -1;

    }

    public String getQuestion(int question_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("questions", null, "id = ?", new String[]{String.valueOf(question_id)}, null, null, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndexOrThrow("answer"));
    }


    public String getUser(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"name"}, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        String name = "";
        if(cursor.moveToFirst())
        {
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        }
        return name;
    }

    public String getQuizName(int quiz_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("quizzes", new String[]{"name"}, "quiz_id = ?", new String[]{String.valueOf(quiz_id)}, null, null, null);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
            return name;
        }

        cursor.close();
        return "Unknown Quiz";
    }


    public ArrayList<ScoreModel> getScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quiz_scores", null);

        ArrayList<ScoreModel> scores = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int scoreId = cursor.getInt(cursor.getColumnIndexOrThrow("score_id"));
                int quizId = cursor.getInt(cursor.getColumnIndexOrThrow("quiz_id"));
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));

                scores.add(new ScoreModel(scoreId, quizId, userId, score));
            }
        }
        cursor.close();
        return scores;
    }

    public ArrayList<ScoreModel> getScores(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "user_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query("quiz_scores", null, selection, selectionArgs, null, null, null);

        ArrayList<ScoreModel> scores = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int scoreId = cursor.getInt(cursor.getColumnIndexOrThrow("score_id"));
                int quizId = cursor.getInt(cursor.getColumnIndexOrThrow("quiz_id"));
                int user = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));

                scores.add(new ScoreModel(scoreId, quizId, user, score));
            }
        }
        cursor.close();
        return scores;
    }








}
