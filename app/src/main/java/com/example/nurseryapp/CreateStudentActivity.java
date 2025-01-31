package com.example.nurseryapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CreateStudentActivity extends AppCompatActivity implements IDefault
{

    private TextInputEditText et_studNum, et_name, et_password;
    private DBHelper db;
    private String studNum, name, pass;
    private MaterialButton btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        initValues();
        et_studNum.setText("02000" + (db.getTotalUserCount() + 1));
        setListeners();

    }

    @Override
    public void initValues()
    {
        et_studNum = findViewById(R.id.etStudNum);
        et_name = findViewById(R.id.etName);
        et_password = findViewById(R.id.etPassword);
        btn_register = findViewById(R.id.btnRegister);
        db = new DBHelper(this);
    }

    @Override
    public void setListeners()
    {
        btn_register.setOnClickListener(register -> {
            setStr();
            if(db.createUser("student", name,studNum, pass))
            {
                Toast.makeText(this, "Student registration success", Toast.LENGTH_SHORT).show();
                clearFields();
            }
            else
            {
                Toast.makeText(this, "Student registration failed", Toast.LENGTH_SHORT).show();
            }

        });

        et_name.addTextChangedListener(new TextWatcher()
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
                generatePassword();
            }
        });
    }

    public void generatePassword()
    {
        String name = et_name.getText().toString();
        String firstThree = name.length() >= 3 ? name.substring(0, 3) : name;
        String pass = et_studNum.getText().toString() + firstThree;

        et_password.setText(pass);
    }
    @Override
    public void setStr()
    {
        studNum = et_studNum.getText().toString();
        name = et_name.getText().toString();
        pass = et_password.getText().toString();
    }

    @Override
    public void clearFields()
    {
        et_studNum.setText("");
        et_name.setText("");
        et_password.setText("");
    }
}