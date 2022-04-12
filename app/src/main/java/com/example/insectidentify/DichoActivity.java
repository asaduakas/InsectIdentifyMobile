package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DichoActivity extends AppCompatActivity implements View.OnClickListener {
    Button leg8;
    Button leg6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicho);
        //TOOLBAR SETUP
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        leg6 = findViewById(R.id.l6);
        leg6.setOnClickListener(this);
        leg8 = findViewById(R.id.l8);
        leg8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.l6:
                Intent intent = new Intent(this,WingsActivity.class);
                startActivity(intent);
                break;
            case R.id.l8:
                Intent intent2 = new Intent(this,ArachnidaActivity.class);
                startActivity(intent2);
                break;
        }
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}