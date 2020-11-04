package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.login.Adapter;

import android.os.Bundle;
import android.view.Menu;

import com.example.myapplication.R;

public class SeulDanActivity extends AppCompatActivity {

    Adapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seul_dan);

        viewPager = (ViewPager) findViewById(R.id.view);
        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);
    }
}