package com.example.myapplication.restaurant.jukjeon.western;

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

public class WesternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_western);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new WesternActivity.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(WesternActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(WesternActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(WesternActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void beuradeo(View view) { Toast.makeText(this,"브라더 양식당",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, BeuradeoActivity.class);
        startActivity(intent);
    }

    public void grande(View view) { Toast.makeText(this,"그란데",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, GrandeActivity.class);
        startActivity(intent);
    }

    public void darin(View view) { Toast.makeText(this,"다린",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DarinActivity.class);
        startActivity(intent);
    }

    public void obliq(View view) { Toast.makeText(this,"오블리끄",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ObliqActivity.class);
        startActivity(intent);
    }

    public void bistro(View view) { Toast.makeText(this,"비스트로 모아이",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, BistroActivity.class);
        startActivity(intent);
    }

    public void burrito(View view) { Toast.makeText(this,"부리또 정거장",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, BurritoActivity.class);
        startActivity(intent);
    }

    public void dosmas(View view) { Toast.makeText(this,"도스마스",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DosmasActivity.class);
        startActivity(intent);
    }
}
