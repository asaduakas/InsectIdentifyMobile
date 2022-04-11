package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

public class DoubleQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDoubleQuestionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_double_question);
        User user = new User("Test", "User");
        binding.setUser(user);
        setContentView(R.layout.activity_double_question);
    }
}