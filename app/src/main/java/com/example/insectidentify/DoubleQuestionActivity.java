package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.insectidentify.databinding.ActivityDoubleQuestionBinding;

public class DoubleQuestionActivity extends AppCompatActivity {

    private QuestionViewModel _vm;
    private static int q1ID, q2ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_question);

        //TOOLBAR SETUP
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        //BINDING
        ActivityDoubleQuestionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_double_question);
        Bundle b = getIntent().getExtras();
        _vm = MainActivity.questionViewModelDictionary.get(b.getInt("id"));
        binding.setVm(_vm);

        Button question1button = findViewById(R.id.question1button);
        Button question2button = findViewById(R.id.question2button);
        question1button.setOnClickListener(this::onButton1Click);
        question2button.setOnClickListener(this::onButton2Click);

        q1ID = this.getResources().getIdentifier(_vm.getQuestion1image(),
                "drawable", this.getPackageName());
        q2ID = this.getResources().getIdentifier(_vm.getQuestion2image(),
                "drawable", this.getPackageName());
    }

    @BindingAdapter("imgSrc")
    public static void imgSrc(ImageView imageView, String resName){
        if (resName.contains("_1"))
            imageView.setImageResource(q1ID);
        else
            imageView.setImageResource(q2ID);
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