package com.example.nurseryapp.activities.teacher;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    private LinearLayout ll_multiple1, ll_multiple2, ll_multiple3, ll_multiple4, ll_multiple5;
    private RadioGroup rg_rf1, rg_rf2, rg_rf3, rg_rf4, rg_rf5;
    private TextInputLayout lo_fill1, lo_fill2, lo_fill3, lo_fill4, lo_fill5;
    private TextInputEditText et_fill1, et_fill2, et_fill3, et_fill4, et_fill5;

    private TextInputEditText et_a12, et_a13, et_a14, et_a15;
    private TextInputEditText et_a12_2, et_a13_2, et_a14_2, et_a15_2;
    private TextInputEditText et_a12_3, et_a13_3, et_a14_3, et_a15_3;
    private TextInputEditText et_a12_4, et_a13_4, et_a14_4, et_a15_4;
    private TextInputEditText et_a12_5, et_a13_5, et_a14_5, et_a15_5;

    private MaterialButton btn_create;

    private Spinner sp_q1, sp_q2, sp_q3, sp_q4, sp_q5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        initValues();
//        setListeners();
        evaluateChoice();
        initListeners();

    }

    @Override
    public void initValues()
    {
        db = new DBHelper(this);
        et_quiz_name = findViewById(R.id.etQuizName);
        btn_create = findViewById(R.id.btnCreateQuiz);

        sp_q2 = findViewById(R.id.spQ12);
        sp_q3 = findViewById(R.id.spQ13);
        sp_q4 = findViewById(R.id.spQ14);
        sp_q5 = findViewById(R.id.spQ15);

        ll_multiple1 = findViewById(R.id.llMultiple1);
        rg_rf1 = findViewById(R.id.rgTF1);
        lo_fill1 = findViewById(R.id.loFill1);
        sp_q1 = findViewById(R.id.spQ1);
        et_fill1 = findViewById(R.id.etFillBlank1);

        et_q1 = findViewById(R.id.etQ1);
        et_a1 = findViewById(R.id.etA1);

        et_a12 = findViewById(R.id.etA12);
        et_a13 = findViewById(R.id.etA13);
        et_a14 = findViewById(R.id.etA14);
        et_a15 = findViewById(R.id.etA15);



        et_q2 = findViewById(R.id.etQ2);
        et_a2 = findViewById(R.id.etA1_2);

        ll_multiple2 = findViewById(R.id.llMultiple2);
        rg_rf2 = findViewById(R.id.rgTF2);
        lo_fill2 = findViewById(R.id.loFill2);

        et_a12_2 = findViewById(R.id.etA12_2);
        et_a13_2 = findViewById(R.id.etA13_2);
        et_a14_2 = findViewById(R.id.etA14_2);
        et_a15_2 = findViewById(R.id.etA15_2);

        et_q3 = findViewById(R.id.etQ3);
        et_a3 = findViewById(R.id.etA1_3);
        ll_multiple3 = findViewById(R.id.llMultiple3);
        rg_rf3 = findViewById(R.id.rgTF3);
        lo_fill3 = findViewById(R.id.loFill3);
        et_a12_3 = findViewById(R.id.etA12_3);
        et_a13_3 = findViewById(R.id.etA13_3);
        et_a14_3 = findViewById(R.id.etA14_3);
        et_a15_3 = findViewById(R.id.etA15_3);

        et_q4 = findViewById(R.id.etQ4);
        et_a4 = findViewById(R.id.etA1_4);

        ll_multiple4 = findViewById(R.id.llMultiple4);
        rg_rf4 = findViewById(R.id.rgTF4);
        lo_fill4 = findViewById(R.id.loFill4);


        et_q5 = findViewById(R.id.etQ5);
        et_a5 = findViewById(R.id.etA1_5);


        ll_multiple5 = findViewById(R.id.llMultiple5);
        rg_rf5 = findViewById(R.id.rgTF5);
        lo_fill5 = findViewById(R.id.loFill5);

        et_a12_4 = findViewById(R.id.etA12_4);
        et_a13_4 = findViewById(R.id.etA13_4);
        et_a14_4 = findViewById(R.id.etA14_4);
        et_a15_4 = findViewById(R.id.etA15_4);

        et_fill2 = findViewById(R.id.etFillBlank2);
        et_fill3 = findViewById(R.id.etFillBlank3);
        et_fill4 = findViewById(R.id.etFillBlank4);
        et_fill5 = findViewById(R.id.etFillBlank5);


        et_a12_5 = findViewById(R.id.etA12_5);
        et_a13_5 = findViewById(R.id.etA13_5);
        et_a14_5 = findViewById(R.id.etA14_5);
        et_a15_5 = findViewById(R.id.etA15_5);

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


    private void evaluateChoice()
    {
        sp_q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String type = adapterView.getItemAtPosition(i).toString();
                updateQuestionVisibility(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        sp_q2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String type = adapterView.getItemAtPosition(i).toString();

                ll_multiple2.setVisibility(View.GONE);
                rg_rf2.setVisibility(View.GONE);
                lo_fill2.setVisibility(View.GONE);

                switch(type)
                {
                    case "Fill in the Blank":
                        lo_fill2.setVisibility(View.VISIBLE);
                        break;
                    case "Multiple Choice":
                        ll_multiple2.setVisibility(View.VISIBLE);
                        break;
                    case "True or False":
                        rg_rf2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });




        sp_q3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String type = adapterView.getItemAtPosition(i).toString();

                ll_multiple3.setVisibility(View.GONE);
                rg_rf3.setVisibility(View.GONE);
                lo_fill3.setVisibility(View.GONE);

                switch(type)
                {
                    case "Fill in the Blank":
                        lo_fill3.setVisibility(View.VISIBLE);
                        break;
                    case "Multiple Choice":
                        ll_multiple3.setVisibility(View.VISIBLE);
                        break;
                    case "True or False":
                        rg_rf3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });



        sp_q4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String type = adapterView.getItemAtPosition(i).toString();

                ll_multiple4.setVisibility(View.GONE);
                rg_rf4.setVisibility(View.GONE);
                lo_fill4.setVisibility(View.GONE);

                switch(type)
                {
                    case "Fill in the Blank":
                        lo_fill4.setVisibility(View.VISIBLE);
                        break;
                    case "Multiple Choice":
                        ll_multiple4.setVisibility(View.VISIBLE);
                        break;
                    case "True or False":
                        rg_rf4.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        sp_q5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String type = adapterView.getItemAtPosition(i).toString();

                ll_multiple5.setVisibility(View.GONE);
                rg_rf5.setVisibility(View.GONE);
                lo_fill5.setVisibility(View.GONE);

                switch(type)
                {
                    case "Fill in the Blank":
                        lo_fill5.setVisibility(View.VISIBLE);
                        break;
                    case "Multiple Choice":
                        ll_multiple5.setVisibility(View.VISIBLE);
                        break;
                    case "True or False":
                        rg_rf5.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

    }

    private void updateQuestionVisibility(String type)
    {
        ll_multiple1.setVisibility(View.GONE);
        rg_rf1.setVisibility(View.GONE);
        lo_fill1.setVisibility(View.GONE);

        switch(type)
        {
            case "Fill in the Blank":
                lo_fill1.setVisibility(View.VISIBLE);
                break;
            case "Multiple Choice":
                ll_multiple1.setVisibility(View.VISIBLE);
                break;
            case "True or False":
                rg_rf1.setVisibility(View.VISIBLE);
                break;
        }
    }

      private void initListeners()
      {
          btn_create.setOnClickListener(create -> {
              String quizName = et_quiz_name.getText().toString();
              String quizStatus = "active";

              int quiz_id = db.createQuiz(quizName, quizStatus);

              addQuestion(quiz_id);

          });
      }

    private void addQuestion(int quiz_id) {
        String questionType = sp_q1.getSelectedItem().toString();
        String question = et_q1.getText().toString();
        String answer = "";

        switch (questionType) {
            case "Multiple Choice":
                answer = et_a1.getText().toString();
                int id = db.createQuestionV2(questionType, question, answer, quiz_id);
                db.insertChoice(et_a12.getText().toString(), id);
                db.insertChoice(et_a13.getText().toString(), id);
                db.insertChoice(et_a14.getText().toString(), id);
                db.insertChoice(et_a15.getText().toString(), id);

                break;
            case "True or False":
                answer = rg_rf1.getCheckedRadioButtonId() == R.id.rbTrue1 ? "True" : "False";
                db.createQuestionV2(questionType, question, answer, quiz_id);

                break;
            case "Fill in the Blank":
                answer = et_fill1.getText().toString();
                db.createQuestionV2(questionType, question, answer, quiz_id);

                break;
        }



        String questionType2 = sp_q2.getSelectedItem().toString();
        String question2 = et_q2.getText().toString();
        String answer2 = "";

        switch (questionType2)
        {
            case "Multiple Choice":
                answer2 = et_a2.getText().toString();
                int id2 = db.createQuestionV2(questionType2, question2, answer2, quiz_id);
                db.insertChoice(et_a12_2.getText().toString(), id2);
                db.insertChoice(et_a13_2.getText().toString(), id2);
                db.insertChoice(et_a14_2.getText().toString(), id2);
                db.insertChoice(et_a15_2.getText().toString(), id2);
                break;
            case "True or False":
                answer2 = rg_rf2.getCheckedRadioButtonId() == R.id.rbTrue2 ? "True" : "False";
                db.createQuestionV2(questionType2, question2, answer2, quiz_id);
                break;
            case "Fill in the Blank":
                answer2 = et_fill2.getText().toString();
                db.createQuestionV2(questionType2, question2, answer2, quiz_id);
                break;
        }




        String questionType3 = sp_q3.getSelectedItem().toString();
        String question3 = et_q3.getText().toString();
        String answer3 = "";

        switch (questionType3)
        {
            case "Multiple Choice":
                answer3 = et_a3.getText().toString();
                int id2 = db.createQuestionV2(questionType3, question3, answer3, quiz_id);
                db.insertChoice(et_a12_3.getText().toString(), id2);
                db.insertChoice(et_a13_3.getText().toString(), id2);
                db.insertChoice(et_a14_3.getText().toString(), id2);
                db.insertChoice(et_a15_3.getText().toString(), id2);
                break;
            case "True or False":
                answer3 = rg_rf3.getCheckedRadioButtonId() == R.id.rbTrue3 ? "True" : "False";
                db.createQuestionV2(questionType3, question3, answer3, quiz_id);
                break;
            case "Fill in the Blank":
                answer3 = et_fill3.getText().toString();
                db.createQuestionV2(questionType3, question3, answer3, quiz_id);
                break;
        }



        String questionType4 = sp_q4.getSelectedItem().toString();
        String question4 = et_q4.getText().toString();
        String answer4 = "";

        switch (questionType4)
        {
            case "Multiple Choice":
                answer4 = et_a4.getText().toString();
                int id2 = db.createQuestionV2(questionType4, question4, answer4, quiz_id);
                db.insertChoice(et_a12_4.getText().toString(), id2);
                db.insertChoice(et_a13_4.getText().toString(), id2);
                db.insertChoice(et_a14_4.getText().toString(), id2);
                db.insertChoice(et_a15_4.getText().toString(), id2);
                break;
            case "True or False":
                answer4 = rg_rf4.getCheckedRadioButtonId() == R.id.rbTrue4 ? "True" : "False";
                db.createQuestionV2(questionType4, question4, answer4, quiz_id);
                break;
            case "Fill in the Blank":
                answer4 = et_fill4.getText().toString();
                db.createQuestionV2(questionType4, question4, answer4, quiz_id);
                break;
        }


        String questionType5 = sp_q5.getSelectedItem().toString();
        String question5 = et_q5.getText().toString();
        String answer5 = "";

        switch (questionType5)
        {
            case "Multiple Choice":
                answer5 = et_a5.getText().toString();
                int id2 = db.createQuestionV2(questionType5, question5, answer5, quiz_id);
                db.insertChoice(et_a12_5.getText().toString(), id2);
                db.insertChoice(et_a13_5.getText().toString(), id2);
                db.insertChoice(et_a14_5.getText().toString(), id2);
                db.insertChoice(et_a15_5.getText().toString(), id2);
                break;
            case "True or False":
                answer5 = rg_rf5.getCheckedRadioButtonId() == R.id.rbTrue5 ? "True" : "False";
                db.createQuestionV2(questionType5, question5, answer5, quiz_id);
                break;
            case "Fill in the Blank":
                answer5 = et_fill5.getText().toString();
                db.createQuestionV2(questionType5, question5, answer5, quiz_id);
                break;
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
                checkVisibility();
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