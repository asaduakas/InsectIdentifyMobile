package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button cancelBtn;
    Button logInBtn;
    TextInputLayout usernameIL;
    TextInputLayout passwordIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cancelBtn = findViewById(R.id.CancelBtn);
        cancelBtn.setOnClickListener(this);
        logInBtn = findViewById(R.id.LoginScreenBtn);
        logInBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.CancelBtn:
                finish();
                break;
            case R.id.LoginScreenBtn:
                usernameIL = findViewById(R.id.tlUsername);
                passwordIL = findViewById(R.id.tlPassword);
                Editable tempU = usernameIL.getEditText().getText();
                Editable tempP = passwordIL.getEditText().getText();
                String username = tempU.toString();
                String password = tempP.toString();
                String tlUsername = sha256(username);
                String tlPassword = sha256(password);
                if(tlUsername.equals(getString(R.string.AdminUname)) && tlPassword.equals(getString(R.string.AdminPass))){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("status","admin");
                    startActivity(intent);
                    finish();
                }
                else if(tlUsername.equals(getString(R.string.DefaultUname)) && tlPassword.equals(getString(R.string.DefaultPass))){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("status","default");
                    startActivity(intent);
                    finish();
                }
                else{
                    Snackbar errorMessage = Snackbar.make(findViewById(R.id.LoginScreenBtn), "Wrong Username or Password!", BaseTransientBottomBar.LENGTH_LONG);
                    View view = errorMessage.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    errorMessage.show();
                }
                break;
        }
    }

    private static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}