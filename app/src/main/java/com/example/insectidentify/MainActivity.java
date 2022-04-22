package com.example.insectidentify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.gson.*;

import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button logInBtn;
    Button startBtn;
    Button dataBtn;
    String status = "notLoggedIn";
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public static HashMap<Integer, Intent> questionIntents;
    public static HashMap<Integer, QuestionViewModel> questionViewModelDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.StartBtn);
        startBtn.setOnClickListener(this);
        dataBtn = findViewById(R.id.dataBtn);
        dataBtn.setOnClickListener(this);

        //Nav Bar setup
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        questionViewModelDictionary = createModels();
        questionIntents = createIntents();
    }

    private HashMap<Integer, QuestionViewModel> createModels(){
        HashMap<Integer, QuestionViewModel> _tmp = new HashMap<>();
        JsonObject object;
        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(
                new InputStreamReader(
                        getResources().openRawResource(R.raw.key_en)));
        object = element.getAsJsonObject();

        for (String id : object.keySet()){
            JsonElement _e = object.get(id);
            QuestionViewModel vm = gson.fromJson(_e, QuestionViewModel.class);
            _tmp.put(Integer.parseInt(id), vm);
        }

        return _tmp;
    }

    private HashMap<Integer, Intent> createIntents(){
        HashMap<Integer, Intent> map = new HashMap<>();
        for (Integer i: questionViewModelDictionary.keySet()) {
            QuestionViewModel __tmp = questionViewModelDictionary.get(i);
            Intent intent;
            if(__tmp.question3description != null){
                intent = new Intent(this, TripleQuestionActivity.class);
            }
            else if(__tmp.description != null){
                intent = new Intent(this, AnswerActivity.class);
            }
            else{
                intent = new Intent(this, DoubleQuestionActivity.class);
            }
            intent.putExtra("id", i);
            map.put(i, intent);
        }
        return map;
    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.StartBtn:
                startActivity(questionIntents.get(0));
                break;
            case R.id.dataBtn:
                Intent intent = new Intent(this, DataActivity.class);
                startActivity(intent);
        }
        //TODO: Add snackbar popup message upon logging out
        //Snackbar confirmationMessage = Snackbar.make(findViewById(R.id.LogInBtn), "Signed out successfully!", BaseTransientBottomBar.LENGTH_LONG);
        //confirmationMessage.show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else if (item.getItemId() == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        //TextView statusBar = findViewById(R.id.status);
        Intent intent = getIntent();
        String receivedStatus = intent.getStringExtra("status");
        if(receivedStatus != null){
            if(receivedStatus.equals("admin")){
                //statusBar.setText(R.string.statusAdmin);
                this.status = "loggedIn";
                logInBtn.setText(R.string.logOut);
            }
            else if (receivedStatus.equals("default")){
               // statusBar.setText(R.string.statusDefault);
                this.status = "loggedIn";
                logInBtn.setText(R.string.logOut);
            }
        }
    }

}