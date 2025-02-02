package com.example.nurseryapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurseryapp.activities.student.TakeQuizActivity;
import com.example.nurseryapp.models.QuizModel;
import com.example.nurseryapp.R;
import com.example.nurseryapp.StaticQuizModel;
import com.example.nurseryapp.activities.teacher.QuestionsActivity;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>
{
    private List<QuizModel> quizModelList;
    private Context context;

    private String type;

    public QuizAdapter(String type)
    {
        this.type = type;
    }

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

            switch (type)
            {
                case "teacher":
                    new StaticQuizModel(id, name, status);
                    Intent intent = new Intent(context, QuestionsActivity.class);
                    context.startActivity(intent);
                    break;
                case "student":
                    new StaticQuizModel(id, name, status);
                    Intent intent1 = new Intent(context, TakeQuizActivity.class);
                    context.startActivity(intent1);
                    break;
            }

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
