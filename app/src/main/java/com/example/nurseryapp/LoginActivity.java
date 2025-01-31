package com.example.nurseryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements IDefault
{

    private TextInputEditText et_uname, et_password;
    private DBHelper db;
    private String uname, pass;
    private MaterialButton btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initValues();
        setListeners();

        db = new DBHelper(this);
        db.createDefaultTeacher();
        db.deleteUsersOlderThanOneMonth();
    }

    @Override
    public void initValues()
    {

        et_uname = findViewById(R.id.etUsername);
        et_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btnLogin);
    }

    @Override
    public void setListeners()
    {
        btn_login.setOnClickListener(login -> {
            loginProcess();
        });
    }

    @Override
    public void setStr()
    {
        uname = et_uname.getText().toString();
        pass = et_password.getText().toString();
    }

    @Override
    public void clearFields()
    {
        et_uname.setText("");
        et_password.setText("");
    }

    public void loginProcess(){
        setStr();
        if(db.getUser(uname, pass).moveToFirst()){
            Cursor cursor = db.getUser(uname, pass);
            cursor.moveToFirst();
            String type = cursor.getString(cursor.getColumnIndexOrThrow("user_type"));

            if(type.equals("teacher"))
            {
                Intent intent = new Intent(LoginActivity.this, TeacherDashboardActivity.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                startActivity(intent);
            }

        }
        else{
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();

        }
    }


}