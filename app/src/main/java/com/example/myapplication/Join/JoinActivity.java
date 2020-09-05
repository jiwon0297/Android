package com.example.myapplication.Join;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        EditText emailText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);
        EditText passwordconfirmText = (EditText) findViewById(R.id.passwordconfirm);
        EditText nameText = (EditText) findViewById(R.id.name);
        EditText nicknameText = (EditText) findViewById(R.id.nickname);
        RadioGroup genderRadio = (RadioGroup) findViewById(R.id.genderGroup);
        RadioButton manButton = (RadioButton) findViewById(R.id.genderMan);
        RadioButton womanButton = (RadioButton) findViewById(R.id.genderWoman);
        Button registerButton = (Button) findViewById(R.id.join);
        Button cancelButton = (Button) findViewById(R.id.cancel);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(JoinActivity.this, LoginActivity.class);
                JoinActivity.this.startActivity(registerIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(JoinActivity.this, LoginActivity.class);
                JoinActivity.this.startActivity(cancelIntent);
            }
        });
    }
}
