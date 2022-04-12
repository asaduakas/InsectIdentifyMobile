package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.insectidentify.databinding.ActivityDoubleQuestionBinding;

public class DoubleQuestionActivity extends AppCompatActivity {

    private Button question1button, question2button;
    private QuestionViewModel _vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

//    @Override
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.question1button:
//                startActivity(MainActivity.questionIntents.get(_vm.question1reference));
//                break;
//            case R.id.question2button:
//                startActivity(MainActivity.questionIntents.get(_vm.question2reference));
//                break;
//        }
//    }
}