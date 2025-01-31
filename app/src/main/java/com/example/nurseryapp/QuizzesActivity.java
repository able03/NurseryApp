package com.example.nurseryapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class QuizzesActivity extends AppCompatActivity implements IDefault
{

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);
        initValues();
        setListeners();
    }

    @Override
    public void initValues()
    {
        rv = findViewById(R.id.rv);
    }

    @Override
    public void setListeners()
    {

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