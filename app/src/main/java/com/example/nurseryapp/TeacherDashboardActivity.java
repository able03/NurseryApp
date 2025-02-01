package com.example.nurseryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherDashboardActivity extends AppCompatActivity implements IDefault
{

    private TextView tv_see_quizzes, tv_name;
    private FloatingActionButton fab_quiz, fab_stud;
    private ImageView iv_logout;

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
        tv_see_quizzes = findViewById(R.id.tvSeeQuizzes);
        iv_logout = findViewById(R.id.ivLogout);
        tv_name = findViewById(R.id.tvStudName);
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

        tv_see_quizzes.setOnClickListener(see -> {
            Intent intent = new Intent(TeacherDashboardActivity.this, QuizzesActivity.class);
            startActivity(intent);
        });


        iv_logout.setOnClickListener(logout -> {
            SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();;

            Intent intent = new Intent(TeacherDashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
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