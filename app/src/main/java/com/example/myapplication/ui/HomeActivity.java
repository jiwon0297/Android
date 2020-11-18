package com.example.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.lost.LostActivity;
import com.example.myapplication.mate.MateActivity;
import com.example.myapplication.restaurant.RestaurantwhereActivity;
import com.example.myapplication.restaurant.cheonan.cafe.GravityActivity;
import com.example.myapplication.restaurant.cheonan.cafe.GroasterActivity;
import com.example.myapplication.restaurant.cheonan.cafe.LikeActivity;
import com.example.myapplication.restaurant.cheonan.cafe.MarisActivity;
import com.example.myapplication.restaurant.cheonan.cafe.PeopleActivity;
import com.example.myapplication.restaurant.cheonan.cafe.SlowActivity;
import com.example.myapplication.restaurant.cheonan.chinese.DongchunActivity;
import com.example.myapplication.restaurant.cheonan.chinese.HeungbuActivity;
import com.example.myapplication.restaurant.cheonan.chinese.HyrinActivity;
import com.example.myapplication.restaurant.cheonan.japanese.ChonActivity;
import com.example.myapplication.restaurant.cheonan.japanese.MadangActivity;
import com.example.myapplication.restaurant.cheonan.japanese.MaeulActivity;
import com.example.myapplication.restaurant.cheonan.japanese.StoryActivity;
import com.example.myapplication.restaurant.cheonan.korean.AfterschoolActivity;
import com.example.myapplication.restaurant.cheonan.korean.BirdActivity;
import com.example.myapplication.restaurant.cheonan.korean.BuanActivity;
import com.example.myapplication.restaurant.cheonan.korean.CheonhojiActivity;
import com.example.myapplication.restaurant.cheonan.korean.GobgobgobActivity;
import com.example.myapplication.restaurant.cheonan.western.BabalabActivity;
import com.example.myapplication.restaurant.cheonan.western.SubmealActivity;
import com.example.myapplication.restaurant.cheonan.western.SungtanActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.AzitActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.CreativeActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.DarakActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.EdiyaActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.SoleilActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.TrianonActivity;
import com.example.myapplication.restaurant.jukjeon.cafe.WitchActivity;
import com.example.myapplication.restaurant.jukjeon.chinese.HongbanActivity;
import com.example.myapplication.restaurant.jukjeon.chinese.HongchunActivity;
import com.example.myapplication.restaurant.jukjeon.chinese.MaratangActivity;
import com.example.myapplication.restaurant.jukjeon.chinese.SabuActivity;
import com.example.myapplication.restaurant.jukjeon.japanese.HosikdangActivity;
import com.example.myapplication.restaurant.jukjeon.japanese.KenkoActivity;
import com.example.myapplication.restaurant.jukjeon.japanese.MamaActivity;
import com.example.myapplication.restaurant.jukjeon.japanese.MisoyaActivity;
import com.example.myapplication.restaurant.jukjeon.japanese.UmedaActivity;
import com.example.myapplication.restaurant.jukjeon.korean.GosimActivity;
import com.example.myapplication.restaurant.jukjeon.korean.HappyActivity;
import com.example.myapplication.restaurant.jukjeon.korean.IlmiActivity;
import com.example.myapplication.restaurant.jukjeon.korean.JjimActivity;
import com.example.myapplication.restaurant.jukjeon.korean.KalActivity;
import com.example.myapplication.restaurant.jukjeon.korean.KimchiActivity;
import com.example.myapplication.restaurant.jukjeon.korean.RedpipeActivity;
import com.example.myapplication.restaurant.jukjeon.korean.SangolActivity;
import com.example.myapplication.restaurant.jukjeon.korean.SangsangActivity;
import com.example.myapplication.restaurant.jukjeon.korean.SonActivity;
import com.example.myapplication.restaurant.jukjeon.korean.WarActivity;
import com.example.myapplication.restaurant.jukjeon.western.BeuradeoActivity;
import com.example.myapplication.restaurant.jukjeon.western.BurritoActivity;
import com.example.myapplication.restaurant.jukjeon.western.DarinActivity;
import com.example.myapplication.restaurant.jukjeon.western.DosmasActivity;
import com.example.myapplication.restaurant.jukjeon.western.GrandeActivity;
import com.example.myapplication.restaurant.jukjeon.western.ObliqActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    String[] str1 = {"일미닭갈비파전","손가네칼국수","엉뚱상상","고래심줄","선영이네김치짜글이","이모네산골","맛의전쟁","빨강파이프","해피덮","내가찜한닭","참바지락칼제비",
            "홍춘","천향마라탕","사부","홍콩반점0410", "호식당","아리가또맘마","우메다","겐코","미소야", "브라더 양식당","그란데","다린","오블리끄","부리또 정거장","도스마스"};
    String[] str2 = {"Cafe다락다락방", "위치스아일랜드", "아지트커피", "이디야커피","솔레일","CAFE TRIANON","크리에이티브커피"};
    String[] str3 = {"천호지","원조부안집","은하철도곱곱곱","수업이 끝난 오후","참새방","동춘옥","하이린","흥부반점","스시마당","돈까스이야기","유생촌","돈까스마을","성탄수제버거앤갈비",
            "SUBMEAL","BABALAB","로씨"};
    String[] str4 = {"마리스커피","카페고메","지로스터","피플앤스토리","GRAVITY LAKE","카페라이크","슬로우커피2"};

    private Button btn1;
    private Button btn2;
    private ImageButton imgbutton2;
    private ImageButton imgbutton3;
    private RadioGroup group1;
    private RadioGroup group2;
    long backKeyPressedTime;
    private TextView cateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn1 = (Button) findViewById(R.id.button36);
        btn2 = (Button) findViewById(R.id.button37);
        group1= (RadioGroup) findViewById(R.id.radioGroup1);
        group2= (RadioGroup) findViewById(R.id.radioGroup2);
        imgbutton2 = (ImageButton) findViewById(R.id.imageButton2);
        imgbutton3 = (ImageButton) findViewById(R.id.imageButton3);

        ViewGroup homepage = (ViewGroup) findViewById(R.id.homepage);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://portal.dankook.ac.kr/web/portal/"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup map = (ViewGroup) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dankook.ac.kr/web/kor/campusmap?p_p_id=Campus_WAR_campusportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_Campus_WAR_campusportlet_sCampusId=1&_Campus_WAR_campusportlet_pageView=detail&_Campus_WAR_campusportlet_action=view"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup elearning = (ViewGroup) findViewById(R.id.elearning);
        elearning.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nlms.dankook.ac.kr/"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup bus = (ViewGroup) findViewById(R.id.bus);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dankook.ac.kr/web/kor/-69"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup webinfo = (ViewGroup) findViewById(R.id.webinfo);
        webinfo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webinfo.dankook.ac.kr/"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup hakbok = (ViewGroup) findViewById(R.id.hakbok);
        hakbok.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.univstore.com/"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup apple = (ViewGroup) findViewById(R.id.apple);
        apple.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apple.com/kr-k12/shop?afid=p238%7Cs2KHj9FlR-dc_mtid_18707vxu38484_pcrid_258798361876_pgrid_50422948013_&cid=aos-kr-kwgo-edu--slid---product--"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup lg = (ViewGroup) findViewById(R.id.lg);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.univstore.com/brand/lg?sort=recommend"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup samsung = (ViewGroup) findViewById(R.id.samsung);
        samsung.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.univstore.com/brand/samsung?sort=recommend"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup wacom = (ViewGroup) findViewById(R.id.wacom);
        wacom.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.univstore.com/brand/wacom?sort=recommend"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup adobe = (ViewGroup) findViewById(R.id.adobe);
        adobe.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.univstore.com/brand/adobe?sort=recommend"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup microsoft = (ViewGroup) findViewById(R.id.microsoft);
        microsoft.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.microsoft.com/ko-kr/education/products/office/"));
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup mate = (ViewGroup) findViewById(R.id.relativeLayout1);
        mate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MateActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup lost = (ViewGroup) findViewById(R.id.relativeLayout2);
        lost.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LostActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ViewGroup restaurant = (ViewGroup) findViewById(R.id.relativeLayout3);
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RestaurantwhereActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });



        group1.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group1, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton14:
                            {
                                imgbutton2.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Random r = new Random();
                                        int index = r.nextInt(str1.length);
                                        btn1.setText(str1[index]);

                                        switch (str1[index]) {
                                            case "일미닭갈비파전":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, IlmiActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "손가네칼국수":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SonActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "엉뚱상상":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SangsangActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "고래심줄":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, GosimActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "선영이네김치짜글이":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, KimchiActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "이모네산골":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SangolActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "맛의전쟁":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, WarActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "빨강파이프":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, RedpipeActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "해피덮":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, HappyActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "내가찜한닭":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, JjimActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "참바지락칼제비":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, KalActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "홍춘":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, HongchunActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "천향마라탕":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, MaratangActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case  "사부":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SabuActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "홍콩반점0410":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, HongbanActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "호식당":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, HosikdangActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "아리가또맘마":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, MamaActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "우메다":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, UmedaActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "겐코":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, KenkoActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "미소야":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, MisoyaActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "브라더 양식당":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, BeuradeoActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "그란데":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, GrandeActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "다린":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, DarinActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "오블리끄":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, ObliqActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "부리또 정거장":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, BurritoActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "도스마스":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, DosmasActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                });
                                break;
                            }
                            case R.id.radioButton15:
                            {
                                imgbutton2.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Random r = new Random();
                                        int index = r.nextInt(str2.length);
                                        btn1.setText(str2[index]);

                                        switch (str2[index]) {
                                            case "Cafe다락다락방":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, DarakActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "위치스아일랜드":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, WitchActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "아지트커피":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, AzitActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "이디야커피":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, EdiyaActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "쏠레이":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SoleilActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "CAFE TRIANON":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, TrianonActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "크리에이티브커피":
                                                btn1.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, CreativeActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                });
                                break;
                            }
                            default:
                                break;
                        }
                    }
                });

        group2.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group2, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton16:
                            {
                                imgbutton3.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Random r = new Random();
                                        int index = r.nextInt(str3.length);
                                        btn2.setText(str3[index]);

                                        switch (str3[index]) {
                                            case "천호지":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, CheonhojiActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "원조부안집":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, BuanActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "은하철도곱곱곱":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, GobgobgobActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "수업이 끝난 오후":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, AfterschoolActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "참새방":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, BirdActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "동춘옥":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, DongchunActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case  "하이린":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, HyrinActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "흥부반점":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, HeungbuActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "스시마당":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, MadangActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "돈까스이야기":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, StoryActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "유생촌":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, ChonActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "돈까스마을":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, MaeulActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "성탄수제버거앤갈비":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SungtanActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "SUBMEAL":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, SubmealActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "BABALAB":
                                                btn2.setOnClickListener(new View.OnClickListener(){
                                                    public void onClick(View v){
                                                        Intent intent = new Intent(HomeActivity.this, BabalabActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                });
                                break;
                            }
                            case R.id.radioButton17:
                            {
                                imgbutton3.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Random r = new Random();
                                        int index = r.nextInt(str4.length);
                                        btn2.setText(str4[index]);

                                        switch (str4[index]) {
                                            case "마리스커피":
                                                btn2.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(HomeActivity.this, MarisActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "지로스터":
                                                btn2.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(HomeActivity.this, GroasterActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "피플앤스토리":
                                                btn2.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(HomeActivity.this, PeopleActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "GRAVITY LAKE":
                                                btn2.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(HomeActivity.this, GravityActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "카페라이크":
                                                btn2.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(HomeActivity.this, LikeActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            case "슬로우커피2":
                                                btn2.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(HomeActivity.this, SlowActivity.class);
                                                        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                                                        startActivity(intent);
                                                    }
                                                });
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                });
                                break;
                            }
                            default:
                                break;
                        }
                    }
                });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(HomeActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(HomeActivity.this, MypageActivity.class);
                    intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //1번째 백버튼 클릭
        if(System.currentTimeMillis()>backKeyPressedTime+2000){
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르시면 앱을 종료합니다.", Toast.LENGTH_SHORT).show();
        }
        //2번째 백버튼 클릭 (종료)
        else{
            AppFinish();
        }
    }

    public void AppFinish(){
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
