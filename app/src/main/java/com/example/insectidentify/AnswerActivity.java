package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.insectidentify.databinding.ActivityAnswerBinding;

public class AnswerActivity extends AppCompatActivity {

    private Button restartBtn, saveBtn;
    private QuestionViewModel _vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        //TOOLBAR SETUP
        Toolbar thisToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(thisToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        ActivityAnswerBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_answer);
        Bundle b = getIntent().getExtras();
        _vm = MainActivity.questionViewModelDictionary.get(b.getInt("id"));
        binding.setVm(_vm);

        restartBtn = findViewById(R.id.restartBtn);
        saveBtn = findViewById(R.id.saveBtn);
        restartBtn.setOnClickListener(this::onButtonRestartClick);
        saveBtn.setOnClickListener(this::onButtonSaveClick);
    }

    private void onButtonRestartClick(View v){
        startActivity(MainActivity.questionIntents.get(0));
    }

    private void onButtonSaveClick(View v){
        //TODO: Save to database
    }

}