package com.example.myapplication.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

    }

    public void cafe(View view) {
        Toast.makeText(this,"카페 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CafeActivity.class);
        startActivity(intent);
    }

    public void Korean(View view) {
        Toast.makeText(this,"한식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, KoreanActivity.class);
        startActivity(intent);
    }

    public void Chinese(View view) {
        Toast.makeText(this,"중식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ChineseActivity.class);
        startActivity(intent);
    }

    public void Japanese(View view) {
        Toast.makeText(this,"일식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, JapaneseActivity.class);
        startActivity(intent);
    }

}
