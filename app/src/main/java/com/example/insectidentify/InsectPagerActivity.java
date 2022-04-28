package com.example.insectidentify;

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

import com.example.insectidentify.ui.main.SectionsPagerAdapter;
import com.example.insectidentify.databinding.ActivityInsectPagerBinding;

public class InsectPagerActivity extends AppCompatActivity {

    private ActivityInsectPagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInsectPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curCount = sectionsPagerAdapter.getCount();
                sectionsPagerAdapter.add(PlaceholderFragment.newInstance(curCount),
                        "TAB " + String.valueOf(curCount));
                sectionsPagerAdapter.notifyDataSetChanged();
            }
        });
    }
}