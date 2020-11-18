package com.example.myapplication.restaurant.jukjeon.korean;

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

public class KoreanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new KoreanActivity.ItemSelectedListener());
    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(KoreanActivity.this, HomeActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(KoreanActivity.this, MailActivity.class);
                    intent2.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(KoreanActivity.this, MypageActivity.class);
                    intent3.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }


    public void ilmi(View view) { Toast.makeText(this,"일미닭갈비",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,IlmiActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void son(View view) { Toast.makeText(this,"손가네칼국수",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SonActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void sangsang(View view) { Toast.makeText(this,"엉뚱상상",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SangsangActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void gosim(View view) { Toast.makeText(this,"고래심줄",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,GosimActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void kimchi(View view) { Toast.makeText(this,"선영이네김치짜글이",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,KimchiActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void sangol(View view) { Toast.makeText(this,"이모네산골",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SangolActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void war(View view) { Toast.makeText(this,"맛의 전쟁",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,WarActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void redpipe(View view) { Toast.makeText(this,"빨강파이프",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,RedpipeActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void happy(View view) { Toast.makeText(this,"해피덮",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,HappyActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void jjim(View view) { Toast.makeText(this,"내가찜한닭",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,JjimActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void kal(View view) { Toast.makeText(this,"참바지락칼제비",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,KalActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
}
