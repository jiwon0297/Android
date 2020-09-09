package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.HomeActivity;

public class MateWriteActivity extends AppCompatActivity {
    private EditText title;
    private EditText nickname;
    private EditText content;
    private TextView cate;
    private RadioGroup campusgroup;
    private int id;
    private RadioButton radioselect;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_write);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_mypage, null);

        title = (EditText) findViewById(R.id.title);
        nickname = (EditText) findViewById(R.id.mypage.nickname);
        content = (EditText) findViewById(R.id.content);
        campusgroup = (RadioGroup) findViewById(R.id.campusgroup);
        id = campusgroup.getCheckedRadioButtonId();
        radioselect = (RadioButton) findViewById(id);
        mProgressView = (ProgressBar) findViewById(R.id.progressBar2);

        ImageButton loginButton = (ImageButton) findViewById(R.id.imageButton1);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button excuteButton = (Button) findViewById(R.id.excute);
        excuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MateWriteActivity.this, MateActivity.class);
                MateWriteActivity.this.startActivity(intent);
            }
        });

        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                cate = (TextView)s.getSelectedView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }
}
