package com.example.insectidentify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.*;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    Button startBtn;
    Button dataBtn;
    NavigationView navigationView;
    String status;
    private ImageButton shortcutBtn;

    private View popupInputDialogView;
    private View popupTutorialView;
    private AutoCompleteTextView cutDropdown;
    private Button jumpBtn;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    AlertDialog alertDialog;

    public static HashMap<Integer, Intent> questionIntents;
    public static HashMap<Integer, QuestionViewModel> questionViewModelDictionary;

    public static HashMap<String, Integer> insectList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.StartBtn);
        startBtn.setOnClickListener(this);
        dataBtn = findViewById(R.id.dataBtn);
        dataBtn.setOnClickListener(this);
        if (SaveSharedPreferences.getUserName(this).length() != 0) {
            dataBtn.setEnabled(true);
        } else {
            dataBtn.setEnabled(false);
        }

        navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState != null) {
            status = savedInstanceState.getString("status");
        }

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

        shortcutBtn = findViewById(R.id.shtButton);
        shortcutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // Set title, icon, can cancel properties.
                alertDialogBuilder.setTitle("Shortcut");
                alertDialogBuilder.setCancelable(true);

                // Init popup dialog view and it's ui controls.
                initPopupViewControls();

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void initPopupViewControls() {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);

        // Get user input edittext and button ui controls in the popup dialog.
        cutDropdown = popupInputDialogView.findViewById(R.id.shortcutDropdown);
        jumpBtn = popupInputDialogView.findViewById(R.id.jumpBtn);

        //Trapping dropdown
        String[] type = getResources().getStringArray(R.array.shortcuts);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_item,
                        type);
        cutDropdown.setAdapter(adapter);

        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String destination = cutDropdown.getText().toString();
                switch (destination) {
                    case "(Hymenoptera) With wings and a cinched waist":
                        startActivity(questionIntents.get(39));
                        break;
                    case "(Coleoptera) With hard/shell like forewings":
                        startActivity(questionIntents.get(6));
                        break;
                    case "(Heteroptera) Forewings with hard corium and soft wing tips":
                        startActivity(questionIntents.get(7));
                        break;
                    case "(Ephemeroptera) With 2–3 long appendages":
                        startActivity(questionIntents.get(35));
                        break;
                    case "(Dermaptera) With pincer abdomen":
                        startActivity(questionIntents.get(26));
                        break;
                }
            }
        });
    }


    private HashMap<Integer, QuestionViewModel> createModels() {
        HashMap<Integer, QuestionViewModel> _tmp = new HashMap<>();
        JsonObject object;
        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(
                new InputStreamReader(
                        getResources().openRawResource(R.raw.key_en)));
        object = element.getAsJsonObject();

        for (String id : object.keySet()) {
            JsonElement _e = object.get(id);
            QuestionViewModel vm = gson.fromJson(_e, QuestionViewModel.class);
            _tmp.put(Integer.parseInt(id), vm);
        }

        return _tmp;
    }

    private HashMap<Integer, Intent> createIntents() {
        HashMap<Integer, Intent> map = new HashMap<>();
        for (Integer i : questionViewModelDictionary.keySet()) {
            QuestionViewModel __tmp = questionViewModelDictionary.get(i);
            Intent intent;
            if (__tmp.question3description != null) {
                intent = new Intent(this, TripleQuestionActivity.class);
            } else if (__tmp.description != null) {
                intent = new Intent(this, AnswerActivity.class);
            } else {
                intent = new Intent(this, DoubleQuestionActivity.class);
            }
            intent.putExtra("id", i);
            map.put(i, intent);
        }
        return map;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        } else if (item.getItemId() == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_logout:
                status = null;
                SaveSharedPreferences.setUserName(this, "");
                navigationView.getMenu().clear(); //clear old inflated items.
                navigationView.inflateMenu(R.menu.nav_menu); //inflate new items.
                invalidateOptionsMenu();
                Snackbar confirmationMessage = Snackbar.make(findViewById(R.id.StartBtn), "Signed out successfully!", BaseTransientBottomBar.LENGTH_LONG);
                View view = confirmationMessage.getView();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                recreate();
                confirmationMessage.show();
                break;
            case R.id.nav_darkMode:
                int nightModeFlags = getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
                if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
                    AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_NO);
                }
                break;
            case R.id.nav_lang:
                Configuration config = getBaseContext().getResources().getConfiguration();
                if(config.locale.getLanguage().equals("de")){
                    Locale locale = new Locale("EN");
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    recreate();
                }
                else{
                    Locale locale = new Locale("DE");
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    recreate();
                }
                break;
            case R.id.nav_help:
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                popupTutorialView = layoutInflater.inflate(R.layout.popup_tutorial, null);
                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Tutorial");
                alertDialogBuilder.setCancelable(true);
                // Init popup dialog view and it's ui controls.
                FloatingActionButton fab = popupTutorialView.findViewById(R.id.floatingActionButton);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupTutorialView);
                // Create AlertDialog and show.
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (SaveSharedPreferences.getUserName(this).equals("admin")) {
            getMenuInflater().inflate(R.menu.main_menu_authorized, menu);
            navigationView.getMenu().clear(); //clear old inflated items.
            navigationView.inflateMenu(R.menu.nav_menu_authorized); //inflate new items.
        } else if (SaveSharedPreferences.getUserName(this).equals("default")) {
            getMenuInflater().inflate(R.menu.main_menu_authorized_default, menu);
            navigationView.getMenu().clear(); //clear old inflated items.
            navigationView.inflateMenu(R.menu.nav_menu_authorized); //inflate new items.
        } else {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        Configuration config = getBaseContext().getResources().getConfiguration();
        if (config.locale.getLanguage().equals("de")) {
            navigationView.getMenu().findItem(R.id.nav_lang).setTitle("EN");
        }
        return true;
    }

    public static void addToSavedList(String ins){
        int temp = 1;
        if(insectList.get(ins) != null){
            temp = insectList.get(ins);
            temp++;
        }
        insectList.put(ins,temp);
    }

    public static HashMap<String,Integer> getSavedList(){
        return insectList;
    }

}