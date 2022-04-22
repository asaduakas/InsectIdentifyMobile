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

import com.example.insectidentify.databinding.ActivityAnswerBinding;

public class AnswerActivity extends AppCompatActivity {

    private Button restartBtn, saveBtn;
    private QuestionViewModel _vm;
    private static int qID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        //TOOLBAR SETUP
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        ActivityAnswerBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_answer);
        Bundle b = getIntent().getExtras();
        _vm = MainActivity.questionViewModelDictionary.get(b.getInt("id"));
        binding.setVm(_vm);

        restartBtn = findViewById(R.id.restartBtn);
        saveBtn = findViewById(R.id.saveBtn);
        restartBtn.setOnClickListener(this::onButtonRestartClick);
        saveBtn.setOnClickListener(this::onButtonSaveClick);

        qID = this.getResources().getIdentifier(_vm.getImage(),
               "drawable", this.getPackageName());
    }

    @BindingAdapter("imgSrc3")
    public static void imgSrc3(ImageView imageView, String resName){
        imageView.setImageResource(qID);
    }

    private void onButtonRestartClick(View v){
        startActivity(MainActivity.questionIntents.get(0));
    }

    private void onButtonSaveClick(View v){
        //TODO: Save to database
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