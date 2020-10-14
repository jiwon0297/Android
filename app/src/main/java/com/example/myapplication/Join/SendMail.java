package com.example.myapplication.Join;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class SendMail extends AppCompatActivity {
    String user ="unniedankook"; // 보내는 계정의 id
    String password = "llxtexvgtacpeoqz"; // 보내는 계정의 pw

    public void sendSecurityCode(Context context, String sendTo) {
        try {
            GMailSender gMailSender = new GMailSender(user, password);
            String code = gMailSender.getEmailCode();
            gMailSender.sendMail("제목입니다", "인증번호입니다\n" + code, sendTo);
            Toast.makeText(context, "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,JoinActivity.class);
            intent.putExtra("CODE","hello");
            context.startActivity(intent);
        } catch (SendFailedException e) {
            Toast.makeText(context, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(context, "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

