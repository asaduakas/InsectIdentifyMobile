package com.example.insectidentify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.*;

import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    Button startBtn;
    Button dataBtn;
    String receivedStatus;
    NavigationView navigationView;
    MenuItem loginMi;

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
        navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_logout:
                receivedStatus = null;
                navigationView.getMenu().clear(); //clear old inflated items.
                navigationView.inflateMenu(R.menu.nav_menu); //inflate new items.
                invalidateOptionsMenu();
                Snackbar confirmationMessage = Snackbar.make(findViewById(R.id.StartBtn), "Signed out successfully!", BaseTransientBottomBar.LENGTH_LONG);
                View view = confirmationMessage.getView();
                FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                confirmationMessage.show();
                break;
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem logInMi = menu.findItem(R.id.action_login);
        MenuItem logInIcon = menu.findItem(R.id.loginIcon);
        MenuItem logOutMi = menu.findItem(R.id.nav_logout);
        if(receivedStatus != null){
            if(receivedStatus.equals("admin")){
                logInMi.setTitle(R.string.statusAdmin);
                logInMi.setEnabled(false);
                logInIcon.setVisible(true);
                navigationView.getMenu().clear(); //clear old inflated items.
                navigationView.inflateMenu(R.menu.nav_menu_authorized); //inflate new items.
            }
            else if (receivedStatus.equals("default")){
                logInMi.setTitle(R.string.statusDefault);
                logInMi.setEnabled(false);
                logInIcon.setVisible(true);
                logOutMi.setVisible(true);
                navigationView.getMenu().clear(); //clear old inflated items.
                navigationView.inflateMenu(R.menu.nav_menu_authorized); //inflate new items.
            }
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        receivedStatus = intent.getStringExtra("status");
    }

}