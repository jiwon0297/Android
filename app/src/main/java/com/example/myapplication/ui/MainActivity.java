package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gobutton = (Button) findViewById(R.id.button);
        gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(go);
            }
        });

        Button testbutton = (Button) findViewById(R.id.test);
        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(test);
            }
        });
    }
}
