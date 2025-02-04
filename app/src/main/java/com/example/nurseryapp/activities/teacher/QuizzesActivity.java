package com.example.nurseryapp.activities.teacher;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.adapters.QuizAdapter;
import com.example.nurseryapp.models.QuizModel;
import com.example.nurseryapp.R;

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
        SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        String type = sharedPreferences.getString("user_type", null);

        rv = findViewById(R.id.rv);
        quizAdapter = new QuizAdapter(type, this);
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