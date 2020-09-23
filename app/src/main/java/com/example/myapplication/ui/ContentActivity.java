package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import com.example.myapplication.R;


import com.example.myapplication.lost.FindActivity;
import com.example.myapplication.lost.GetActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;



public class ContentActivity extends AppCompatActivity {

    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MymateActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MylostActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });
    }
}