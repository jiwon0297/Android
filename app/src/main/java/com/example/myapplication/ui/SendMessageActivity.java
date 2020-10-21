package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class SendMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        String sender = getIntent().getStringExtra("SENDER");
        String recipient = getIntent().getStringExtra("RECIPIENT");

        TextView RecipText = (TextView) findViewById(R.id.recipient);

        RecipText.setText(recipient);

    }
}