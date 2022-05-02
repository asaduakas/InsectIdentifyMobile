package com.example.insectidentify;

import android.content.Intent;
import android.os.Bundle;

import com.example.insectidentify.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.insectidentify.ui.main.SectionsPagerAdapter;
import com.example.insectidentify.databinding.ActivityInsectPagerBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsectPagerActivity extends AppCompatActivity {

    private ActivityInsectPagerBinding binding;
    SectionsPagerAdapter sectionsPagerAdapter;
    String batchName;
    HashMap<String, Integer> savedInsects;
    List<Entry> saveList;

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
        ArrayList<Fragment> fragmentArrayList = sectionsPagerAdapter.getFragments();
        for (int i=0; i < fragmentArrayList.size(); i++){
            PlaceholderFragment currFragment = (PlaceholderFragment) fragmentArrayList.get(i);
            String currCounter = currFragment.getCounter();
            if(!currCounter.equals("0")){
                String tm = getIntent().getStringExtra("trapMethod");
                String ct = getIntent().getStringExtra("catchTime");
                String field = getIntent().getStringExtra("field");
                String tn = getIntent().getStringExtra("trapNum");
                String sd = getIntent().getStringExtra("startDate");
                String st = getIntent().getStringExtra("startTime");
                String od = currFragment.getInsectOrder();
                String tx = currFragment.getTaxName();
                String nt = currFragment.getNote();
                Entry newEntry = new Entry(batchName, tm, ct, field, tn, sd, st, od, tx, nt);
            }
        }
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

    public static class Entry{
        String bName;
        String trapMethod;
        String catchTime;
        String field;
        String trapNum;
        String sDate;
        String sTime;
        String order;
        String taxName;
        String note;

        public Entry(String bName, String trapMethod, String catchTime, String field, String trapNum,
                     String sDate, String sTime, String order, String taxName, String note){
            this.bName = bName;
            this.trapMethod = trapMethod;
            this.catchTime = catchTime;
            this.field = field;
            this.trapNum = trapNum;
            this.sDate = sDate;
            this.sTime = sTime;
            this.order = order;
            this.taxName = taxName;
            this.note = note;
        }
    }

}