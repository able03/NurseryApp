package com.example.nurseryapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.activities.student.TakeQuizActivity;
import com.example.nurseryapp.models.QuizModel;
import com.example.nurseryapp.R;
import com.example.nurseryapp.StaticQuizModel;
import com.example.nurseryapp.activities.teacher.QuestionsActivity;

import java.util.List;


public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private List<QuizModel> quizModelList;
    private Context context;
    private String type;
    private DBHelper dbHelper; // Add DBHelper to interact with the database

    public QuizAdapter(String type, Context context) {
        this.type = type;
        this.context = context;
        this.dbHelper = new DBHelper(context); // Initialize DBHelper
    }

    public void setQuizModelList(List<QuizModel> quizModelList) {
        this.quizModelList = quizModelList;
    }

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_quizzes_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position) {
        QuizModel quiz = quizModelList.get(position);
        int id = quiz.getId();
        String name = quiz.getName();
        String status = quiz.getStatus();

        holder.tv_quiz_name.setText(name);

        // Show the switch only for the teacher
        if (type.equals("teacher")) {
            holder.switchActivate.setVisibility(View.VISIBLE);
            holder.switchActivate.setChecked(status.equals("active")); // Set switch state based on quiz status

            // Handle switch toggle
            holder.switchActivate.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String newStatus = isChecked ? "active" : "inactive";
                dbHelper.updateQuizStatus(id, newStatus); // Update quiz status in the database
                quiz.setStatus(newStatus); // Update the local quiz status
            });
        } else {
            holder.switchActivate.setVisibility(View.GONE); // Hide the switch for students
        }

        holder.cv.setOnClickListener(click -> {
            switch (type) {
                case "teacher":
                    new StaticQuizModel(id, name, status);
                    Intent intent = new Intent(context, QuestionsActivity.class);
                    context.startActivity(intent);
                    break;
                case "student":
                    if (status.equals("active")) {
                        new StaticQuizModel(id, name, status);
                        Intent intent1 = new Intent(context, TakeQuizActivity.class);
                        context.startActivity(intent1);
                    } else {
                        Toast.makeText(context, "This quiz is not active.", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_quiz_name;
        private CardView cv;
        private Switch switchActivate; // Add Switch to the ViewHolder

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quiz_name = itemView.findViewById(R.id.tvQuizName);
            cv = itemView.findViewById(R.id.cv);
            switchActivate = itemView.findViewById(R.id.switchActivate); // Initialize Switch
        }
    }
}