package com.example.myapplication.restaurant.jukjeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new RestaurantActivity.ItemSelectedListener());
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

    public void Western(View view) {
        Toast.makeText(this,"양식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WesternActivity.class);
        startActivity(intent);
    }

}
