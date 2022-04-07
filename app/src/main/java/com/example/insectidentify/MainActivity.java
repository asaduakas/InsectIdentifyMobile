package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button logInBtn;
    Button startBtn;
    String status = "notLoggedIn";

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
                if(this.status.equals("notLoggedIn")){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    break;
                }
                else if(this.status.equals("loggedIn")){
                    logInBtn.setText(R.string.logIn);
                    this.status = "notLoggedIn";
                    TextView statusBar = findViewById(R.id.status);
                    statusBar.setText(R.string.statusEmpty);
                    //Log out from database
                    Snackbar confirmationMessage = Snackbar.make(findViewById(R.id.LogInBtn), "Signed out successfully!", BaseTransientBottomBar.LENGTH_LONG);
                    confirmationMessage.show();
                    break;
                }
            case R.id.StartBtn:
                Intent intent = new Intent(this,DichoActivity.class);
                startActivity(intent);
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
                this.status = "loggedIn";
                logInBtn.setText(R.string.logOut);
            }
            else if (receivedStatus.equals("default")){
                statusBar.setText(R.string.statusDefault);
                this.status = "loggedIn";
                logInBtn.setText(R.string.logOut);
            }
        }
    }

}