package com.example.insectidentify;

import android.content.Intent;
import android.os.Bundle;

import com.example.insectidentify.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.insectidentify.ui.main.SectionsPagerAdapter;
import com.example.insectidentify.databinding.ActivityInsectPagerBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsectPagerActivity extends AppCompatActivity {

    private ActivityInsectPagerBinding binding;
    SectionsPagerAdapter sectionsPagerAdapter;
    String batchName;
    HashMap<String, Integer> savedInsects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        batchName = getIntent().getStringExtra("bName");

        binding = ActivityInsectPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        if(!MainActivity.getSavedList().isEmpty()){
            savedInsects = MainActivity.getSavedList();
            for (Map.Entry<String, Integer> set : savedInsects.entrySet()) {
                sectionsPagerAdapter.add(PlaceholderFragment.newInstance(sectionsPagerAdapter.getCount(), set.getValue(),set.getKey()),
                        "INSECT " + String.valueOf(sectionsPagerAdapter.getCount() + 1));
                sectionsPagerAdapter.notifyDataSetChanged();
            }
        }
        else{
            //Create first page automatically
            sectionsPagerAdapter.add(PlaceholderFragment.newInstance(sectionsPagerAdapter.getCount()),
                    "INSECT " + String.valueOf(sectionsPagerAdapter.getCount() + 1));
            sectionsPagerAdapter.notifyDataSetChanged();
        }

    }

    public void onClickAddTab(){
        int curCount = sectionsPagerAdapter.getCount();
        sectionsPagerAdapter.add(PlaceholderFragment.newInstance(curCount),
                "INSECT " + String.valueOf(curCount + 1));
        sectionsPagerAdapter.notifyDataSetChanged();
    }


    public void onClickSub() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    //TOOLBAR FUNCTIONS
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

    public void setBatchName(TextView batchName) {
        batchName.setText(this.batchName);
    }

}