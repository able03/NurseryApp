package com.example.nurseryapp.activities.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.adapters.QuestionAdapter;
import com.example.nurseryapp.models.QuestionModel;
import com.example.nurseryapp.R;
import com.example.nurseryapp.StaticQuizModel;
import com.example.nurseryapp.models.QuestionV2Model;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements IDefault
{

    private RecyclerView rv;
    private List<QuestionV2Model> questionModelList;
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

        questionModelList.addAll(db.getQuestionsV2(StaticQuizModel.getId()));

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