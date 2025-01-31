package com.example.nurseryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherDashboardActivity extends AppCompatActivity implements IDefault
{

    private FloatingActionButton fab_quiz, fab_stud;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        initValues();
        setListeners();
    }

    @Override
    public void initValues()
    {
        fab_quiz = findViewById(R.id.fabAddQuiz);
        fab_stud = findViewById(R.id.fabAddStud);
    }

    @Override
    public void setListeners()
    {
        fab_quiz.setOnClickListener(quiz -> {
            Intent intent = new Intent(TeacherDashboardActivity.this, CreateQuizActivity.class);
            startActivity(intent);
        });


        fab_stud.setOnClickListener(stud -> {
            Intent intent = new Intent(TeacherDashboardActivity.this, CreateStudentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void setStr()
    {


    }

    @Override
    public void clearFields()
    {

    }
}