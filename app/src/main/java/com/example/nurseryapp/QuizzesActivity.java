package com.example.nurseryapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizzesActivity extends AppCompatActivity implements IDefault
{

    private RecyclerView rv;
    private QuizAdapter quizAdapter;
    private List<QuizModel> quizModelList;
    private DBHelper db;

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
        quizAdapter = new QuizAdapter();
        quizModelList = new ArrayList<>();
        db = new DBHelper(this);


        quizModelList.addAll(db.getQuizzes());
        quizAdapter.setQuizModelList(quizModelList);
        rv.setAdapter(quizAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
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