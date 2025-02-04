package com.example.nurseryapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.R;
import com.example.nurseryapp.models.ScoreModel;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder>
{
    private List<ScoreModel> scoreModels;
    private Context context;
    private String type;

    public void setScoreModels(List<ScoreModel> scoreModels, String type)
    {
        this.scoreModels = scoreModels;
        this.type = type;
    }

    @NonNull
    @Override
    public ScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_scores_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ViewHolder holder, int position)
    {
        DBHelper dbHelper = new DBHelper(context);
        ScoreModel scoreModel = scoreModels.get(position);

        String score = scoreModel.getScore() + "/5";
        holder.tv_score.setText(score);

        if (type.equals("teacher")) {
            holder.tv_title.setText(dbHelper.getUser(scoreModel.getUser_id()));
        } else if (type.equals("student")) {
            String quizName = dbHelper.getQuizName(scoreModel.getQuiz_id());
            holder.tv_title.setText(quizName);
        }
    }

    @Override
    public int getItemCount()
    {
        return scoreModels.size() != 0 ? scoreModels.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        private CardView cv;
        private TextView tv_title, tv_score;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tv_title = itemView.findViewById(R.id.tvTitle);
            tv_score = itemView.findViewById(R.id.tvScore);
        }
    }
}
