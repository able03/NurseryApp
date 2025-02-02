package com.example.nurseryapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.IQuizListener;
import com.example.nurseryapp.R;
import com.example.nurseryapp.StaticQuizModel;
import com.example.nurseryapp.activities.student.StudentDashboardActivity;
import com.example.nurseryapp.models.ChoicesModel;
import com.example.nurseryapp.models.QuestionModel;
import com.example.nurseryapp.models.QuestionV2Model;
import com.google.android.material.button.MaterialButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;


public class TakeQuizFragment extends Fragment implements IDefault
{
    private String question, answer;
    private TextView tv_score, tv_timer, tv_question;
    private DBHelper db;

    private RadioGroup rgMain;
    private EditText et_fill;
    private ArrayList<QuestionV2Model> questionV2Model;
    private MaterialButton btn_submit;
    private int score = 0;
    private int currentIndex = 0;

    public TakeQuizFragment(ArrayList<QuestionV2Model> questionV2Model)
    {
        this.questionV2Model = questionV2Model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_take_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initValues();
        setTimer();
        displayQuestion(currentIndex);
        setListeners();
    }

    @Override
    public void initValues()
    {
        tv_score = getView().findViewById(R.id.tvScore);
        tv_timer = getView().findViewById(R.id.tvTimer);
        db = new DBHelper(getContext());
        rgMain = getView().findViewById(R.id.rg);
        et_fill = getView().findViewById(R.id.etFill);
        tv_question = getView().findViewById(R.id.tvQuestion);
        btn_submit = getView().findViewById(R.id.btnSubmit);
    }

    private void setTimer()
    {
        CountDownTimer countDownTimer = new CountDownTimer(180000, 1000)
        {
            @Override
            public void onTick(long l)
            {

                int seconds = (int) l / 1000;
                tv_timer.setText(formatTime(seconds));
            }

            @Override
            public void onFinish()
            {
                tv_timer.setText("Finished");
            }
        };

        countDownTimer.start();
    }

    private String formatTime(int seconds)
    {
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        String time = String.format("%02d:%02d", minutes, secondsLeft);
        return time;

    }


    @Override
    public void setListeners()
    {
        btn_submit.setOnClickListener(click -> {
            checkAnswer();
        });
    }

    @Override
    public void setStr()
    {

    }

    @Override
    public void clearFields()
    {

    }

    private void checkAnswer()
    {
        String userAnswer = "";


        QuestionV2Model currentQuestion = questionV2Model.get(currentIndex);

        if (currentQuestion.getQuestion_type().equals("Multiple Choice") ||
                currentQuestion.getQuestion_type().equals("True or False"))
        {
            int selectedId = rgMain.getCheckedRadioButtonId();
            if (selectedId != -1)
            {
                RadioButton selectedRadioButton = getView().findViewById(selectedId);
                userAnswer = selectedRadioButton.getText().toString();
            }
        }
        else if (currentQuestion.getQuestion_type().equals("Fill in the Blank"))
        {
            userAnswer = et_fill.getText().toString().trim();
        }

        if (!userAnswer.isEmpty() && db.checkAnswer(currentQuestion.getId(), userAnswer))
        {
            score++;
            tv_score.setText("Score: " + score + "/5");
        }


        currentIndex++;
        if (currentIndex < questionV2Model.size()) {
            displayQuestion(currentIndex);
        } else {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            int user_id = sharedPreferences.getInt("id", -1);
            btn_submit.setText("Finish");
            btn_submit.setOnClickListener(v -> {
                db.insertScore(StaticQuizModel.getId(), user_id, score);
                Intent intent = new Intent(getContext(), StudentDashboardActivity.class);
                startActivity(intent);
                getActivity().finish();
            });
        }

    }




    private void displayQuestion(int index)
    {
        rgMain.removeAllViews();
        et_fill.setText("");
        rgMain.setVisibility(View.GONE);
        et_fill.setVisibility(View.GONE);


        QuestionV2Model currentQuestion = questionV2Model.get(index);
        tv_question.setText(currentQuestion.getQuestion());

        if (currentQuestion.getQuestion_type().equals("Multiple Choice"))
        {
            rgMain.setVisibility(View.VISIBLE);

            ArrayList<ChoicesModel> choices = db.getChoices(currentQuestion.getId());

            String answer = db.getQuestion(currentQuestion.getId());

            RadioButton rd = new RadioButton(getContext());
            rd.setText(answer);
            rgMain.addView(rd);
            for (ChoicesModel choice : choices)
            {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(choice.getChoice());
                rgMain.addView(radioButton);
            }

        }

        else if(currentQuestion.getQuestion_type().equals("True or False"))
        {
            rgMain.setVisibility(View.VISIBLE);
            RadioButton rbTrue = new RadioButton(getContext());
            rbTrue.setText("True");
            rgMain.addView(rbTrue);

            RadioButton rbFalse = new RadioButton(getContext());
            rbFalse.setText("False");
            rgMain.addView(rbFalse);
        }

        else if(currentQuestion.getQuestion_type().equals("Fill in the Blank"))
        {
            et_fill.setVisibility(View.VISIBLE);
        }
    }


}