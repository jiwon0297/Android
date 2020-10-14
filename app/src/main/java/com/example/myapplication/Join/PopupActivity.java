package com.example.myapplication.Join;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;

public class PopupActivity extends Activity {

    EditText AuthText;
    Button AuthBtn;
    TextView time_counter;
    CountDownTimer countDownTimer;
    final int MILLISINFUTURE = 300 * 1000; //총 시간 (300초 = 5분)
    final int COUNT_DOWN_INTERVAL = 1000; //onTick 메소드를 호출할 간격 (1초)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        //UI 객체생성
        AuthText = (EditText)findViewById(R.id.emailAuth_number);
        AuthBtn = (Button)findViewById(R.id.emailAuth_btn);

        countDownTimer();

        AuthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AuthText.setError(null);
                boolean cancel = false;
                View focusView = null;

                //데이터 가져오기
                String code = getIntent().getStringExtra("CODE");
                String usercode = AuthText.getText().toString();

                // 닉네임의 유효성 검사
                if (usercode.isEmpty()) {
                    AuthText.setError("인증코드를 입력해주세요.");
                    focusView = AuthText;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    Intent intent = new Intent();
                    if(usercode.equals(code)){
                        intent.putExtra("result", "인증이 완료되었습니다. 회원가입을 진행합니다.");
                        setResult(RESULT_OK, intent);
                    } else {
                        intent.putExtra("result", "인증에 실패하셨습니다. 다시 시도해주세요.");
                        setResult(RESULT_CANCELED, intent);
                    }
                    finish();
                }
            }
        });
    }

    public void countDownTimer() { //카운트 다운 메소드

        time_counter = (TextView) findViewById(R.id.emailAuth_time_counter);
        //줄어드는 시간을 나타내는 TextView

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) { //(300초에서 1초 마다 계속 줄어듬)

                long emailAuthCount = millisUntilFinished / 1000;
                Log.d("Alex", emailAuthCount + "");

                if ((emailAuthCount - ((emailAuthCount / 60) * 60)) >= 10) { //초가 10보다 크면 그냥 출력
                    time_counter.setText((emailAuthCount / 60) + " : " + (emailAuthCount - ((emailAuthCount / 60) * 60)));
                } else { //초가 10보다 작으면 앞에 '0' 붙여서 같이 출력. ex) 02,03,04...
                    time_counter.setText((emailAuthCount / 60) + " : 0" + (emailAuthCount - ((emailAuthCount / 60) * 60)));
                }

                //emailAuthCount은 종료까지 남은 시간임. 1분 = 60초 되므로,
                // 분을 나타내기 위해서는 종료까지 남은 총 시간에 60을 나눠주면 그 몫이 분이 된다.
                // 분을 제외하고 남은 초를 나타내기 위해서는, (총 남은 시간 - (분*60) = 남은 초) 로 하면 된다.

            }

            @Override
            public void onFinish() { //시간이 다 되면 다이얼로그 종료
                Intent intent = new Intent();
                intent.putExtra("result", "시간이 초과되었습니다. 다시 시도해주세요.");
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        }.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}