package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.insectidentify.databinding.ActivityTripleQuestionBinding;

public class TripleQuestionActivity extends AppCompatActivity{

    private Button question1button, question2button, question3button;
    private QuestionViewModel _vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_question);
        //TOOLBAR SETUP
        Toolbar thisToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(thisToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        ActivityTripleQuestionBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_triple_question);
        Bundle b = getIntent().getExtras();
        _vm = MainActivity.questionViewModelDictionary.get(b.getInt("id"));
        binding.setVm(_vm);

        question1button = findViewById(R.id.question1button);
        question2button = findViewById(R.id.question2button);
        question3button = findViewById(R.id.question3button);
        question1button.setOnClickListener(this::onButton1Click);
        question2button.setOnClickListener(this::onButton2Click);
        question3button.setOnClickListener(this::onButton3Click);
    }

    private void onButton1Click(View v){
        startActivity(MainActivity.questionIntents.get(_vm.question1reference));
    }

    private void onButton2Click(View v){
        startActivity(MainActivity.questionIntents.get(_vm.question2reference));
    }

    private void onButton3Click(View v){
        startActivity(MainActivity.questionIntents.get(_vm.question3reference));
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}