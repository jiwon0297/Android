package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;

public class MateViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_view);

        Intent intent = getIntent();

        TextView title = (TextView) findViewById(R.id.title);
        TextView writer = (TextView) findViewById(R.id.writer);
        TextView date = (TextView) findViewById(R.id.date);
        TextView content = (TextView) findViewById(R.id.content);

        title.setText(intent.getStringExtra("title"));
        writer.setText(intent.getStringExtra("writer"));
        date.setText(intent.getStringExtra("date"));
        content.setText(intent.getStringExtra("content"));

        ImageButton loginButton = (ImageButton) findViewById(R.id.imageButton1);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
