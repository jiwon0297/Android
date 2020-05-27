package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TextView emailText = (TextView) findViewById(R.id.username);
        EditText nicknameText = (EditText) findViewById(R.id.nickname);
        TextView nameText = (TextView) findViewById(R.id.name);
        EditText passwordText = (EditText) findViewById(R.id.password);
        TextView genderText = (TextView) findViewById(R.id.gender);

        Button editButton = (Button) findViewById(R.id.edit);
        Button cancelButton = (Button) findViewById(R.id.cancel);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editIntent = new Intent(UserActivity.this, HomeActivity.class);
                UserActivity.this.startActivity(editIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(UserActivity.this, HomeActivity.class);
                UserActivity.this.startActivity(cancelIntent);
            }
        });
    }
}

