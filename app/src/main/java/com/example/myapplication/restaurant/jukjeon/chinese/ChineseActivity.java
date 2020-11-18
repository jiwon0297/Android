package com.example.myapplication.restaurant.jukjeon.chinese;

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

public class ChineseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ChineseActivity.ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(ChineseActivity.this, HomeActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(ChineseActivity.this, MailActivity.class);
                    intent2.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(ChineseActivity.this, MypageActivity.class);
                    intent3.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }


    public void hongchun(View view) { Toast.makeText(this,"홍춘",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HongchunActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void maratang(View view) { Toast.makeText(this,"천향마라탕",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MaratangActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void sabu(View view) { Toast.makeText(this,"사부",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SabuActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void hongban(View view) { Toast.makeText(this,"홍콩반점0410",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HongbanActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
}
