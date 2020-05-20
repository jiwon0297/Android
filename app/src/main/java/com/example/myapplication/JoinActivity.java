package com.example.myapplication;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.login.LoginViewModel;
import com.example.myapplication.ui.login.LoginViewModelFactory;

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
        Button cancleButton = (Button) findViewById(R.id.cancle);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(JoinActivity.this, LoginActivity.class);
                JoinActivity.this.startActivity(registerIntent);
            }
        });

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(JoinActivity.this, LoginActivity.class);
                JoinActivity.this.startActivity(registerIntent);
            }
        });
    }
}
