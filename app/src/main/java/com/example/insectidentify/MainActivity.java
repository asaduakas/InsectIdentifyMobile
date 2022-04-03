package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button logInBtn;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logInBtn = findViewById(R.id.LogInBtn);
        logInBtn.setOnClickListener(this);
        startBtn = findViewById(R.id.StartBtn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.LogInBtn:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.StartBtn:
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        TextView statusBar = findViewById(R.id.status);
        Intent intent = getIntent();
        String receivedStatus = intent.getStringExtra("status");
        if(receivedStatus != null){
            if(receivedStatus.equals("admin")){
                statusBar.setText(R.string.statusAdmin);
            }
            else if (receivedStatus.equals("default")){
                statusBar.setText(R.string.statusDefault);
            }
        }
    }
}