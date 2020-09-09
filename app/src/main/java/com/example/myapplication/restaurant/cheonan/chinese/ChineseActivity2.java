package com.example.myapplication.restaurant.cheonan.chinese;

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

public class ChineseActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ChineseActivity2.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(ChineseActivity2.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(ChineseActivity2.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(ChineseActivity2.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void dongchun(View view) { Toast.makeText(this,"동춘옥",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DongchunActivity.class);
        startActivity(intent);
    }

    public void hyrin(View view) {Toast.makeText(this,"하이린",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HyrinActivity.class);
        startActivity(intent);
    }

    public void heungbu(View view) {Toast.makeText(this,"흥부반점",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HeungbuActivity.class);
        startActivity(intent);
    }

}