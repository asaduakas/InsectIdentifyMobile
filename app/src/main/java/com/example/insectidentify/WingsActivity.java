package com.example.insectidentify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WingsActivity extends AppCompatActivity implements View.OnClickListener {
    Button wingsP;
    Button wingsA;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wings);
        //TOOLBAR SETUP
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        wingsP = findViewById(R.id.wp);
        wingsP.setOnClickListener(this);
        wingsA = findViewById(R.id.wa);
        wingsA.setOnClickListener(this);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.wp:
                break;
            case R.id.wa:
                break;
            case R.id.backBtn:
                this.finish();
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