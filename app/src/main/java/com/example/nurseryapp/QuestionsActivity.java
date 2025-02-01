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

public class QuestionsActivity extends AppCompatActivity implements IDefault
{

    private RecyclerView rv;
    private List<QuestionModel> questionModelList;
    private QuestionAdapter adapter;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initValues();
    }

    @Override
    public void initValues()
    {
        rv = findViewById(R.id.rv);
        questionModelList = new ArrayList<>();
        adapter = new QuestionAdapter();
        db = new DBHelper(this);

        questionModelList.addAll(db.getQuestions(StaticQuizModel.getId()));

        adapter.setQuestionModelList(questionModelList);
        rv.setAdapter(adapter);
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