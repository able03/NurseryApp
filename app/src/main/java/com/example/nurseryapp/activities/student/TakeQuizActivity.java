package com.example.nurseryapp.activities.student;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nurseryapp.DBHelper;
import com.example.nurseryapp.IDefault;
import com.example.nurseryapp.IQuizListener;
import com.example.nurseryapp.R;
import com.example.nurseryapp.StaticQuizModel;
import com.example.nurseryapp.fragments.TakeQuizFragment;
import com.example.nurseryapp.models.QuestionV2Model;

import java.util.ArrayList;
import java.util.Currency;

public class TakeQuizActivity extends AppCompatActivity implements IDefault
{

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        initValues();


        ArrayList<QuestionV2Model> list = new ArrayList<>();
        list.addAll(db.getQuestionsV2(StaticQuizModel.getId()));

        if(list.isEmpty()) Toast.makeText(this, "list empty", Toast.LENGTH_SHORT).show();
        setFragment(new TakeQuizFragment(list));
    }
    @Override
    public void initValues()
    {
        db = new DBHelper(this);

    }

    @Override
    public void setListeners()
    {

    }

    @Override
    public void setStr()
    {

    }

    @Override
    public void clearFields()
    {

    }


    private void setFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl, fragment).commit();
    }

}
