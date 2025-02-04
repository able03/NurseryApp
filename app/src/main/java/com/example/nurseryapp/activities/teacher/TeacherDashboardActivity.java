package com.example.nurseryapp.activities.teacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.activities.LoginActivity;
import com.example.nurseryapp.R;
import com.example.nurseryapp.fragments.ShowQuizzesFragment;
import com.example.nurseryapp.fragments.TakenQuizzesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherDashboardActivity extends AppCompatActivity implements IDefault
{

    private TextView tv_see_quizzes, tv_name, tv_see_scores;
    private FloatingActionButton fab_quiz, fab_stud;
    private ImageView iv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        initValues();
        setListeners();
        setFragment(new ShowQuizzesFragment(), R.id.flQuizzes);

        TakenQuizzesFragment takenQuizzesFragment = new TakenQuizzesFragment();
        takenQuizzesFragment.setType("teacher");
        setFragment(takenQuizzesFragment, R.id.flTakenQuizzes);
    }

    @Override
    public void initValues()
    {
        fab_quiz = findViewById(R.id.fabAddQuiz);
        fab_stud = findViewById(R.id.fabAddStud);
        tv_see_quizzes = findViewById(R.id.tvSeeQuizzes);
        iv_logout = findViewById(R.id.ivLogout);
        tv_name = findViewById(R.id.tvStudName);
        tv_see_scores = findViewById(R.id.tvSeeStudScores);
    }

    @Override
    public void setListeners()
    {
        tv_see_scores.setOnClickListener(see -> {
            Intent intent = new Intent(TeacherDashboardActivity.this, TakenQuizzesActivity.class);
            startActivity(intent);
        });

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


    private void setFragment(Fragment fragment, int id)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(id, fragment).commit();
    }
}