package com.example.myapplication.restaurant.cheonan.cafe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurant.map.MapFragmentActivity;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GravityActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"Espresso(Ice +0.5)","Signature Variation","Expresso Blended","Affogato","Signature Ade","Signature Tea","Black&Hub Tea","Yogurt","Smoothie&Fruit Juice","Shake","Real Juice"};
    String[][] childs={{"에스프레소 4.0","아메리카노 4.0","카페라떼 4.5","드라이카푸치노 4.5","바닐라라떼 5.0","헤이즐넛라떼 5.0","카라멜마끼아또 5.0","파베카페모카 5.0","파베화이트모카 5.0"},
            {"연유크림 라떼 5.5","융프라우 쇼콜라 5.5","히말라야 라떼 6.0","히말라야 솔트 슈페너 6.5","미숫가루 슈페너 6.0","크림 모카 5.0","콜드 브루 5.0","플랫 화이트 4.5","흑임자라떼 6.0","방탄 커피(버터 커피) 4.5"},
            {"넛트 아포가토 6.0","녹차 아포가토 6.5"},
            {"생자몽 에이드 6.0","생레몬 에이드 6.0","고흥 유자 에이드 6.0","피치 블라썸 6.0","얼그레이 레몬 에이드 6.0","패션프루트 에이드 6.0"},
            {"배도라지생강차 6.0","말차라떼 6.0","코코아 5.5","수제 자몽차 6.0","수제 레몬차 6.0","고흥 유자차 6.0","밀크티(루이보스, 얼그레이) 6.0","달고나 밀크티 6.5"},
            {"블랙티 6.0","허브티 6.0"},
            {"딸기 요거트 6.0","레몬 요거트 6.5","블루베리 요거트 6.5","플레인 요거트 6.0"},
            {"딸기 스무디 6.0","키위 스무디 6.0","딸기 바나나 스무디 6.5","키위 바나나 스무디 6.5","망고 바나나 스무디 6.5","흑당 버블티 6.5"},
            {"누텔라 초코 쉐이크 6.0","누텔라 오레오 쉐이크 6.5","누텔라 바닐라 쉐이크 6.5","말차 쉐이크 6.5","미숫가루 쉐이크 6.5"},
            {"키위 쥬스 6.5","생 딸기 라떼(시즌) 6.5","생 딸기 쥬스(시즌) 6.5"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        ExpandableListAdapter listAdapter = new GravityActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GravityActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new GravityActivity.ItemSelectedListener());
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    public class MyExpandableListAdapter extends BaseExpandableListAdapter {

        public Object getChild(int groupPosition, int childPosition){
            return childs[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition){
            return childPosition;
        }

        public int getChildrenCount(int groupPosition){
            return childs[groupPosition].length;
        }

        public TextView getGenericView(){
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,64);
            TextView textView = new TextView(GravityActivity.this);
            textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            textView.setPadding(30,40,0,40);

            return textView;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent){
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition,childPosition).toString());
            textView.setTextSize(14);
            Typeface typeface = getResources().getFont(R.font.kyobo);
            textView.setTypeface(typeface);

            return textView;
        }

        public Object getGroup(int groupPosition){
            return groups[groupPosition];
        }

        public int getGroupCount(){
            return groups.length;
        }

        public long getGroupId(int groupPosition){
            return groupPosition;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent){
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            textView.setTextColor(Color.parseColor("#E87A9DEA"));
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextSize(17);
            Typeface typeface = getResources().getFont(R.font.kyobo);
            textView.setTypeface(typeface);

            listView.setGroupIndicator(null);

            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition){
            return true;
        }

        public boolean hasStableIds(){
            return true;
        }
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(GravityActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(GravityActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(GravityActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}