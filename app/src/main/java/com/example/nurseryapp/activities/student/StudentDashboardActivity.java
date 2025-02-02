package com.example.nurseryapp.activities.student;

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

public class StudentDashboardActivity extends AppCompatActivity implements IDefault
{

    private ImageView iv_logout;
    private TextView tv_name;
    private TextView tv_see_quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        initValues();
        setListeners();
        setFragment(new ShowQuizzesFragment(), R.id.flQuizzes);

        TakenQuizzesFragment takenQuizzesFragment = new TakenQuizzesFragment();
        takenQuizzesFragment.setType("student");
        setFragment(takenQuizzesFragment, R.id.flTakenQuizzes);

    }

    @Override
    public void initValues()
    {
        iv_logout = findViewById(R.id.ivLogout);
        tv_name = findViewById(R.id.tvStudName);
        tv_see_quiz = findViewById(R.id.tvSeeQuizzes);

        SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);
        if(name != null) tv_name.setText("Hello " + name);

    }


    @Override
    public void setListeners()
    {

        iv_logout.setOnClickListener(logout -> {
            SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();;

            Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        tv_see_quiz.setOnClickListener(see -> {

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