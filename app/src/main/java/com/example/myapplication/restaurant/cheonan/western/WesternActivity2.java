package com.example.myapplication.restaurant.cheonan.western;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WesternActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_western2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new WesternActivity2.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(WesternActivity2.this, HomeActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(WesternActivity2.this, MailActivity.class);
                    intent2.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(WesternActivity2.this, MypageActivity.class);
                    intent3.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void sungtan(View view) {Toast.makeText(this,"성탄수제버거앤갈비",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SungtanActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void submeal(View view) {Toast.makeText(this,"SubMeal",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SubmealActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void babalab(View view) {Toast.makeText(this,"BaBaLab",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, BabalabActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
}