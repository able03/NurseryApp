package com.example.nurseryapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>
{
    private List<QuizModel> quizModelList;
    private Context context;

    public void setQuizModelList(List<QuizModel> quizModelList)
    {
        this.quizModelList = quizModelList;
    }

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

}
