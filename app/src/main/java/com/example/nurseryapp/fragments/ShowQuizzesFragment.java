package com.example.nurseryapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.R;
import com.example.nurseryapp.StaticQuizModel;
import com.example.nurseryapp.adapters.QuestionAdapter;
import com.example.nurseryapp.adapters.QuizAdapter;
import com.example.nurseryapp.models.QuestionModel;
import com.example.nurseryapp.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class ShowQuizzesFragment extends Fragment implements IDefault
{

    private RecyclerView rv;
    private List<QuizModel> quizModelList;
    private QuizAdapter adapter;
    private DBHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_show_quuizzes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();
    }

    @Override
    public void initValues()
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String type = sharedPreferences.getString("user_type", null);
        rv = getView().findViewById(R.id.rv);
        quizModelList = new ArrayList<>();
        adapter = new QuizAdapter(type);
        db = new DBHelper(getContext());

        quizModelList.addAll(db.getQuizzes());

        adapter.setQuizModelList(quizModelList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
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