package com.example.myapplication.restaurant.cheonan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurant.cheonan.cafe.CafeActivity2;
import com.example.myapplication.restaurant.cheonan.chinese.ChineseActivity2;
import com.example.myapplication.restaurant.cheonan.japanese.JapaneseActivity2;
import com.example.myapplication.restaurant.cheonan.korean.KoreanActivity2;
import com.example.myapplication.restaurant.cheonan.western.WesternActivity2;

public class RestaurantActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);

        ViewGroup cafe = (ViewGroup) findViewById(R.id.cafe);
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, CafeActivity2.class);
                startActivity(intent);
            }
        });

        ViewGroup korean = (ViewGroup) findViewById(R.id.korean);
        korean.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, KoreanActivity2.class);
                startActivity(intent);
            }
        });
        ViewGroup japanese = (ViewGroup) findViewById(R.id.japanese);
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, JapaneseActivity2.class);
                startActivity(intent);
            }
        });
        ViewGroup chinese = (ViewGroup) findViewById(R.id.chinese);
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, ChineseActivity2.class);
                startActivity(intent);
            }
        });
        ViewGroup western = (ViewGroup) findViewById(R.id.western);
        western.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, WesternActivity2.class);
                startActivity(intent);
            }
        });

        ViewGroup dessert = (ViewGroup) findViewById(R.id.dessert);
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, WesternActivity2.class);
                startActivity(intent);
            }
        });
    }


}