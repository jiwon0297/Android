package com.example.myapplication.lost;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ProgressBar;
    import android.widget.Toast;

    import com.example.myapplication.R;
    import com.example.myapplication.login.LoginData;
    import com.example.myapplication.network.ServiceApi;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

public class LostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetActivity.class);
                startActivity(intent);
            }
        });

    }

}

