package com.example.nurseryapp.activities.student;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nurseryapp.R;
import com.example.nurseryapp.fragments.TakenQuizzesFragment;

public class StudTakenQuizActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_taken_quiz);
        TakenQuizzesFragment takenQuizzesFragment = new TakenQuizzesFragment();
        takenQuizzesFragment.setType("student");
        setFragment(takenQuizzesFragment, R.id.fl);
    }

    private void setFragment(Fragment fragment, int id)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(id, fragment).commit();
    }
}