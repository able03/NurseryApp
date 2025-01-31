package com.example.nurseryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class CreateQuizActivity extends AppCompatActivity implements IDefault
{

    private TextInputEditText et_quiz_name,
            et_q1, et_a1, et_q2, et_a2, et_q3,
            et_a3, et_q4, et_a4, et_q5, et_a5;

    private DBHelper db;

    private MaterialButton btn_create_quiz;
    private LinearLayout ll0, ll2, ll3, ll4, ll5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        initValues();
        setListeners();

    }

    @Override
    public void initValues()
    {
        db = new DBHelper(this);
        et_quiz_name = findViewById(R.id.etQuiz);

        et_q1 = findViewById(R.id.etQ1);
        et_a1 = findViewById(R.id.etA1);

        et_q2 = findViewById(R.id.etQ2);
        et_a2 = findViewById(R.id.etA2);

        et_q3 = findViewById(R.id.etQ3);
        et_a3 = findViewById(R.id.etA3);

        et_q4 = findViewById(R.id.etQ4);
        et_a4 = findViewById(R.id.etA4);

        et_q5 = findViewById(R.id.etQ5);
        et_a5 = findViewById(R.id.etA5);

        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        ll4 = findViewById(R.id.ll4);
        ll5 = findViewById(R.id.ll5);
        ll0 = findViewById(R.id.ll0);

        btn_create_quiz = findViewById(R.id.btnCreateQuiz);
    }
    private void checkVisibility() {
        if (et_quiz_name.getText().toString().isEmpty()) {
            ll0.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            ll3.setVisibility(View.GONE);
            ll4.setVisibility(View.GONE);
            ll5.setVisibility(View.GONE);
            return;
        }

        ll0.setVisibility(View.VISIBLE);

        if (!et_q1.getText().toString().isEmpty() && !et_a1.getText().toString().isEmpty()) {
            ll2.setVisibility(View.VISIBLE);
            ll3.setVisibility(View.GONE);
            ll4.setVisibility(View.GONE);
            ll5.setVisibility(View.GONE);
        }
        else
        {
            ll2.setVisibility(View.GONE);
            ll3.setVisibility(View.GONE);
            ll4.setVisibility(View.GONE);
            ll5.setVisibility(View.GONE);
        }

        if (!et_q2.getText().toString().isEmpty() && !et_a2.getText().toString().isEmpty()) {
            ll3.setVisibility(View.VISIBLE);
            ll4.setVisibility(View.GONE);
            ll5.setVisibility(View.GONE);
        }
        else
        {
            ll3.setVisibility(View.GONE);
            ll4.setVisibility(View.GONE);
            ll5.setVisibility(View.GONE);
        }

        if (!et_q3.getText().toString().isEmpty() && !et_a3.getText().toString().isEmpty()) {
            ll4.setVisibility(View.VISIBLE);
            ll5.setVisibility(View.GONE);
        }
        else
        {
            ll4.setVisibility(View.GONE);
            ll5.setVisibility(View.GONE);
        }

        if (!et_q4.getText().toString().isEmpty() && !et_a4.getText().toString().isEmpty()) {
            ll5.setVisibility(View.VISIBLE);
        }
        else
        {
            ll5.setVisibility(View.GONE);
        }
    }


    @Override
    public void setListeners()
    {

        btn_create_quiz.setOnClickListener(quiz -> {

            String quizName = et_quiz_name.getText().toString();
            String quizStatus = "active";

            int quiz_id = db.createQuiz(quizName, quizStatus);

            questionProcess(quiz_id);

            Cursor cursor = db.getQuestions();
            if(cursor.getCount() > 0)
            {
                while(cursor.moveToNext())
                {
                    String question = cursor.getString(1);
                    String answer = cursor.getString(2);
                    Toast.makeText(this, question + " " + answer, Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "No question", Toast.LENGTH_SHORT).show();
            }
        });

        et_quiz_name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                checkVisibility();
            }
        });

        et_q1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();  // Check visibility whenever the question field changes
            }
        });

        et_a1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });

        et_q2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });

        et_a2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });

        et_q3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });

        et_a3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });


        et_q4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });

        et_a4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkVisibility();
            }
        });


    }


    private void questionProcess(int quiz_id) {
        if (!et_q1.getText().toString().isEmpty() && !et_a1.getText().toString().isEmpty()) {
            Map<String, String> question1 = new HashMap<>();
            question1.put("question", et_q1.getText().toString());
            question1.put("answer", et_a1.getText().toString());
            db.createQuestion(question1.get("question"), question1.get("answer"), quiz_id);
        }

        if (!et_q2.getText().toString().isEmpty() && !et_a2.getText().toString().isEmpty()) {
            Map<String, String> question2 = new HashMap<>();
            question2.put("question", et_q2.getText().toString());
            question2.put("answer", et_a2.getText().toString());
            db.createQuestion(question2.get("question"), question2.get("answer"), quiz_id);
        }

        if (!et_q3.getText().toString().isEmpty() && !et_a3.getText().toString().isEmpty()) {
            Map<String, String> question3 = new HashMap<>();
            question3.put("question", et_q3.getText().toString());
            question3.put("answer", et_a3.getText().toString());
            db.createQuestion(question3.get("question"), question3.get("answer"), quiz_id);
        }

        if (!et_q4.getText().toString().isEmpty() && !et_a4.getText().toString().isEmpty()) {
            Map<String, String> question4 = new HashMap<>();
            question4.put("question", et_q4.getText().toString());
            question4.put("answer", et_a4.getText().toString());
            db.createQuestion(question4.get("question"), question4.get("answer"), quiz_id);
        }

        if (!et_q5.getText().toString().isEmpty() && !et_a5.getText().toString().isEmpty()) {
            Map<String, String> question5 = new HashMap<>();
            question5.put("question", et_q5.getText().toString());
            question5.put("answer", et_a5.getText().toString());
            db.createQuestion(question5.get("question"), question5.get("answer"), quiz_id);
        }
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