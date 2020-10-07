package com.example.myapplication.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.lost.LostActivity;
import com.example.myapplication.mate.MateActivity;
import com.example.myapplication.restaurant.RestaurantwhereActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    String[] str1 = {"일미닭갈비파전","손가네칼국수","엉뚱상상","고래심줄","선영이네김치짜글이","이모네산골","맛의전쟁","빨강파이프","해피덮","내가찜한닭","참바지락칼제비",
            "홍춘","천향마라탕","사부","홍콩반점0410", "호식당","아리가또맘마","우메다","겐코","미소야", "브라더 양식당","그란데","다린","오블리끄","부리또 정거장","도스마스"};
    String[] str2 = {"Cafe다락다락방", "위치스아일랜드", "아지트커피", "이디야커피","솔레일","CAFE TRIANON","크리에이티브커피"};
    TextView textView;
    ImageButton imgbutton;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView = (TextView) findViewById(R.id.textView10);
        group= (RadioGroup) findViewById(R.id.radioGroup1);
        imgbutton = (ImageButton) findViewById(R.id.imageButton);

        group.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton14:
                            {
                                imgbutton.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Random r = new Random();
                                        int index = r.nextInt(str1.length);
                                        textView.setText(str1[index]);
                                    }
                                });
                            }
                            case R.id.radioButton15:
                            {
                                imgbutton.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Random r = new Random();
                                        int index = r.nextInt(str2.length);
                                        textView.setText(str2[index]);
                                    }
                                });
                            }
                        }
                    }
                });


        Button mate = (Button) findViewById(R.id.textView2);
        Drawable alpha1 = mate.getBackground();
        alpha1.setAlpha(180);

        Button lost = (Button) findViewById(R.id.textView3);
        Drawable alpha2 = lost.getBackground();
        alpha2.setAlpha(180);

        Button restaurant = (Button) findViewById(R.id.textView5);
        Drawable alpha3 = restaurant.getBackground();
        alpha3.setAlpha(180);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(HomeActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(HomeActivity.this, MypageActivity.class);
                    intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void mate(View v) {
        Toast.makeText(this,"메이트 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MateActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
    public void lost(View v) {
        Toast.makeText(this,"분실물 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LostActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
    public void restaurantwhere(View v){
        Toast.makeText(this,"식당 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RestaurantwhereActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);

    }
}
