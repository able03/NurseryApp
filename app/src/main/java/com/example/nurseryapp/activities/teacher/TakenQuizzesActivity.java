package com.example.nurseryapp.activities.teacher;

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

public class TakenQuizzesActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_quizzes);
        TakenQuizzesFragment takenQuizzesFragment = new TakenQuizzesFragment();
        takenQuizzesFragment.setType("teacher");
        setFragment(takenQuizzesFragment, R.id.fl);
    }
    private void setFragment(Fragment fragment, int id)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(id, fragment).commit();
    }
}