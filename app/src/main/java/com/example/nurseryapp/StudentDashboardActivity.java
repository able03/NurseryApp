package com.example.nurseryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDashboardActivity extends AppCompatActivity implements IDefault
{

    private ImageView iv_logout;
    private TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        initValues();
        setListeners();
    }

    @Override
    public void initValues()
    {
        iv_logout = findViewById(R.id.ivLogout);
        tv_name = findViewById(R.id.tvStudName);

        SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);
        if(name != null) tv_name.setText("Hello " + name);

    }


    @Override
    public void setListeners()
    {

        iv_logout.setOnClickListener(logout -> {
            SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();;

            Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
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
}