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
import com.example.nurseryapp.adapters.ScoreAdapter;
import com.example.nurseryapp.models.ScoreModel;

import java.util.ArrayList;


public class TakenQuizzesFragment extends Fragment implements IDefault
{


    private RecyclerView rv;
    private String type = "";

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_taken_quizzes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();

        DBHelper db = new DBHelper(getContext());
        ScoreAdapter adapter = new ScoreAdapter();
        ArrayList<ScoreModel> list = new ArrayList<>();

       if(type.equals("teacher"))
       {
           list.addAll(db.getScores());
           adapter.setScoreModels(list, type);
           rv.setAdapter(adapter);
           rv.setLayoutManager(new LinearLayoutManager(getContext()));
       }
       else if(type.equals("student"))
       {
           SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
           int id = sharedPreferences.getInt("user_id", -1);
           list.addAll(db.getScores(id));
           adapter.setScoreModels(list, type);
           rv.setAdapter(adapter);
           rv.setLayoutManager(new LinearLayoutManager(getContext()));
       }

    }

    @Override
    public void initValues()
    {
        rv = getView().findViewById(R.id.rv);
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