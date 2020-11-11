package com.example.myapplication.restaurant.jukjeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurant.jukjeon.cafe.CafeActivity;
import com.example.myapplication.restaurant.jukjeon.chinese.ChineseActivity;
import com.example.myapplication.restaurant.jukjeon.japanese.JapaneseActivity;
import com.example.myapplication.restaurant.jukjeon.korean.KoreanActivity;
import com.example.myapplication.restaurant.jukjeon.western.WesternActivity;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);



        ViewGroup cafe = (ViewGroup) findViewById(R.id.cafe);
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, CafeActivity.class);
                startActivity(intent);
            }
        });

        ViewGroup korean = (ViewGroup) findViewById(R.id.korean);
        korean.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
        ViewGroup japanese = (ViewGroup) findViewById(R.id.japanese);
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, JapaneseActivity.class);
                startActivity(intent);
            }
        });
        ViewGroup chinese = (ViewGroup) findViewById(R.id.chinese);
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, ChineseActivity.class);
                startActivity(intent);
            }
        });
        ViewGroup western = (ViewGroup) findViewById(R.id.western);
        western.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, WesternActivity.class);
                startActivity(intent);
            }
        });

        ViewGroup dessert = (ViewGroup) findViewById(R.id.dessert);
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, WesternActivity.class);
                startActivity(intent);
            }
        });
    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(RestaurantActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(RestaurantActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(RestaurantActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}
