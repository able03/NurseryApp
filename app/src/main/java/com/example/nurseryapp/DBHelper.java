package com.example.nurseryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("CREATE TABLE questions(id INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT, answer TEXT, quiz_id INTEGER)");
        db.execSQL("CREATE TABLE quiz_scores(score_id INTEGER PRIMARY KEY AUTOINCREMENT, quiz_id INTEGER, user_id INTEGER, score INTEGER)");
    }


//    public void createDefaultTeacher() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // Check if a teacher account already exists
//        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user_type = ?", new String[]{"teacher"});
//
//        if (cursor.getCount() == 0) { // No teacher exists, create one
//            ContentValues values = new ContentValues();
//            values.put("user_type", "teacher");
//            values.put("name", "Default Teacher");
//            values.put("username", "teacher");
//            values.put("password", "password123"); // Change this for security
//
//            db.insert("users", null, values);
//        }
//
//        cursor.close();
//    }

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


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS quizzes");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS quiz_scores");
        onCreate(db);
    }

    public Cursor getQuestions()
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




//    public boolean createQuiz(String name, String status)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("status", status);
//        return db.insert("quizzes", null, values) != -1;
//    }

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



}
