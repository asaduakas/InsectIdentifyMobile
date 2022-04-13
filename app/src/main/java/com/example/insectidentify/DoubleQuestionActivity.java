package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.insectidentify.databinding.ActivityDoubleQuestionBinding;

public class DoubleQuestionActivity extends AppCompatActivity {

    private Button question1button, question2button;
    private QuestionViewModel _vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_question);
        //TOOLBAR SETUP
        Toolbar thisToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(thisToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ActivityDoubleQuestionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_double_question);
        Bundle b = getIntent().getExtras();
        _vm = MainActivity.questionViewModelDictionary.get(b.getInt("id"));
        binding.setVm(_vm);

        question1button = findViewById(R.id.question1button);
        question2button = findViewById(R.id.question2button);
        question1button.setOnClickListener(this::onButton1Click);
        question2button.setOnClickListener(this::onButton2Click);
    }


    private void onButton1Click(View v){
        startActivity(MainActivity.questionIntents.get(_vm.question1reference));
    }

    private void onButton2Click(View v){
        startActivity(MainActivity.questionIntents.get(_vm.question2reference));
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}