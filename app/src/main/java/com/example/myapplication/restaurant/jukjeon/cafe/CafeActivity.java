package com.example.myapplication.restaurant.jukjeon.cafe;

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

public class CafeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new CafeActivity.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(CafeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(CafeActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(CafeActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void darak(View view) { Toast.makeText(this,"Cafe다락다락방",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DarakActivity.class);
        startActivity(intent);
    }

    public void witch(View view) {Toast.makeText(this,"위치스아일랜드",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WitchActivity.class);
        startActivity(intent);
    }

    public void azit(View view) {Toast.makeText(this,"아지트커피",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AzitActivity.class);
        startActivity(intent);
    }

    public void ediya(View view) {Toast.makeText(this,"이디야커피",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, EdiyaActivity.class);
        startActivity(intent);
    }

    public void soleil(View view) {Toast.makeText(this,"솔레일",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DarakActivity.class);
        startActivity(intent);
    }

    public void trianon(View view) {Toast.makeText(this,"cafe trianon",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SoleilActivity.class);
        startActivity(intent);
    }

    public void creative(View view) {Toast.makeText(this,"크리에이티브커피",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TrianonActivity.class);
        startActivity(intent);
    }
}
