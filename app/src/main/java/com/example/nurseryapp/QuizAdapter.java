package com.example.nurseryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_quizzes_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position)
    {
        int id = quizModelList.get(position).getId();
        String name = quizModelList.get(position).getName();
        String status = quizModelList.get(position).getStatus();

        holder.tv_quiz_name.setText(name);

        holder.cv.setOnClickListener(click -> {
            Intent intent = new Intent(context, QuestionsActivity.class);

            new StaticQuizModel(id, name, status);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount()
    {
        return quizModelList.size() != 0 ? quizModelList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView tv_quiz_name;
        private CardView cv;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_quiz_name = itemView.findViewById(R.id.tvQuizName);
            cv = itemView.findViewById(R.id.cv);
        }
    }

}
