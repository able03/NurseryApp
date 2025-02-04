package com.example.nurseryapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.R;
import com.example.nurseryapp.activities.student.StudentDashboardActivity;
import com.example.nurseryapp.activities.teacher.TeacherDashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity implements IDefault
{

    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
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
        db = new DBHelper(this);
        checkAutoLogin();
        setListeners();


        LinearLayout ll = findViewById(R.id.main);

        db.createDefaultTeacher();
        db.deleteUsersOlderThanOneMonth();

        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.login_bg, null)).getBitmap();



        executor.execute(() ->
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
            runOnUiThread(() -> {
                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                ll.setBackground(drawable);
            });
        });
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

    private boolean checkAutoLogin()
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user", this.MODE_PRIVATE);
        String user = sharedPreferences.getString("username", null);
        String pass = sharedPreferences.getString("password", null);

        if(user != null && pass != null)
        {
            Cursor cursor = db.getUser(user, pass);
            if(cursor.moveToFirst())
            {
                String type = sharedPreferences.getString("user_type", null);
                Intent intent;
                if(type.equals("teacher"))
                {
//                    clearCredentials();
                    intent = new Intent(LoginActivity.this, TeacherDashboardActivity.class);
                    startActivity(intent);
                }
                else if(type.equals("student"))
                {
//                    clearCredentials();
                    intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                    startActivity(intent);
                }

            }
            else
            {
                Toast.makeText(this, "User does not exists", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }

        return false;
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
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("user_type"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

            Intent intent;
            if(type.equals("teacher"))
            {
                clearCredentials();
                saveCredentials(id, type, name, username, password);
                intent = new Intent(LoginActivity.this, TeacherDashboardActivity.class);
                startActivity(intent);
            }
            else if(type.equals("student"))
            {
                clearCredentials();
                saveCredentials(id, type, name, username, password);
                intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                startActivity(intent);
            }

        }
        else{
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();

        }
    }

    private void saveCredentials(int id, String type, String name, String username, String password)
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.putString("user_type", type);
        editor.putString("name", name);
        editor.putString("username", username);
        editor.putString("password", password);

        editor.apply();
    }

    private void clearCredentials()
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }



}