package com.example.myapplication.mypage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.lost.LostActivity;
import com.example.myapplication.mate.MateActivity;
import com.example.myapplication.restaurant.RestaurantActivity;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.mypage.MypageActivity;

public class ContentManageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_mypage);
        Button ContentButton = (Button) findViewById(R.id.ContentManage);
        Button UserButton = (Button) findViewById(R.id.UserManage);
        Button LogoutButton = (Button) findViewById(R.id.Logout);


        ContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Button 클릭될 시 할 코드작성
                Intent ContentIntent = new Intent(ContentManageActivity.this, MypageActivity.class);
                startActivity(ContentIntent);
            }
        });
        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Button 클릭될 시 할 코드작성
                Intent UserIntent = new Intent(ContentManageActivity.this, UserActivity.class);
                startActivity(UserIntent);
            }
        });
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Button 클릭될 시 할 코드작성
                Intent LogoutIntent = new Intent(ContentManageActivity.this, MainActivity.class);
                startActivity(LogoutIntent);
            }
        });

    }
}

