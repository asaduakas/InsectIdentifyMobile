package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button logInBtn;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logInBtn = (Button) findViewById(R.id.LogInBtn);
        logInBtn.setOnClickListener(this);
        startBtn = (Button) findViewById(R.id.StartBtn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.LogInBtn:
                break;
            case R.id.StartBtn:
                break;
        }
    }
}