package com.example.nurseryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurseryapp.models.QuestionModel;
import com.example.nurseryapp.R;
import com.example.nurseryapp.models.QuestionV2Model;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>
{
    private List<QuestionV2Model> questionModelList;
    private Context context;

    public void setQuestionModelList(List<QuestionV2Model> questionModelList)
    {
        this.questionModelList = questionModelList;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_question_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position)
    {
        holder.tv_question.setText(questionModelList.get(position).getQuestion());
        holder.tv_answer.setText(questionModelList.get(position).getAnswer());
    }

    @Override
    public int getItemCount()
    {
        return questionModelList.size() != 0 ? questionModelList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_question, tv_answer;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tvQuestion);
            tv_answer = itemView.findViewById(R.id.tvAnswer);
        }
    }

}
