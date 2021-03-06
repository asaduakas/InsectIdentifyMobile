package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.insectidentify.databinding.ActivityTripleQuestionBinding;

public class TripleQuestionActivity extends AppCompatActivity{

    private Button question1button, question2button, question3button;
    private QuestionViewModel _vm;
    private static int q1ID, q2ID, q3ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_question);
        //TOOLBAR SETUP
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

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

        q1ID = this.getResources().getIdentifier(_vm.getQuestion1image(),
                "drawable", this.getPackageName());
        q2ID = this.getResources().getIdentifier(_vm.getQuestion2image(),
                "drawable", this.getPackageName());
        q3ID = this.getResources().getIdentifier(_vm.getQuestion3image(),
                "drawable", this.getPackageName());
    }

    @BindingAdapter("imgSrc2")
    public static void imgSrc2(ImageView imageView, String resName){
        if (resName.contains("_1"))
            imageView.setImageResource(q1ID);
        else if (resName.contains("_2"))
            imageView.setImageResource(q2ID);
        else
            imageView.setImageResource(q3ID);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dicho, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) {// User chose the "Home" item, go to main page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }else// If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
    }


}