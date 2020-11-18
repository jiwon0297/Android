package com.example.myapplication.restaurant.cheonan.chinese;

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

public class HyrinActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"하이린 짬뽕특선","별미","나홀로세트","세트메뉴","하이린 세트메뉴","면류","밥류","요리부"};
    String[][] childs={{"전복짬뽕 12,000원","육해공짬뽕 10,000원","속풀이짬뽕 9,000원","불짬뽕 9,000원","홍합짬뽕 9,000원","삼선짬뽕 9,000원","백짬뽕 9,000원"},
            {"갈비탕 7,000원","육개장 7,000원"},
            {"탕수육+식사류(택1) 13,000원","사천탕수육+식사류(택1) 15,000원"},
            {"세트 A(제육볶음 2인분+공기밥2) 16,000원","세트 B(오징어볶음 2인분+공기밥2) 16,000원","A코스(탕수육+양장피+군만두) 35,000원","B코스(탕수육+양장피+팔보채+군만두) 50,000원","C코스(탕수육+양장피+팔보채+깐풍기+군만두) 65,000원"},
            {"1세트(탕수육+짜장2+콜라) 18,000원","2세트(탕수육+짜장1+짬뽕1+콜라) 20,000원","3세트(탕수육+짬뽕2+콜라) 21,000원","4세트(탕수육+볶음밥2+콜라) 21,000원","5세트(탕수육+쟁반짜장2人+콜라) 22,000원","6세트(탕수육+쟁반짬뽕2人+콜라) 24,000원","7세트(사천탕수육+짜장2+콜라) 20,000원","8세트(사천탕수육+쟁반짜장2人+콜라) 24,000원","9세트(사천탕수육+쟁반짬뽕2人+콜라) 26,000원"},
            {"짜장면 5,000원","짬뽕 7,000원","간짜장 7,000원","우동 7,000원","울면 7,000원","삼선짜장 8,000원","불짜장 8,000원","고추짜장 8,000원","고추간짜장 8,000원","삼선우동 9,000원","삼선울면 9,000원","쟁반짜장(2인) 14,000원","쟁반짬뽕(2인) 16,000원"},
            {"볶음밥 7,000원","짬뽕밥 7,000원","짜장밥 7,000원","오므라이스 8,000원","제육덮밥 7,000원","오징어덮밥 8,000원","송이덮밥 8,000원","잡채밥 8,000원","새우볶음밥 8,000원","삼선볶음밥 8,000원","고추잡채밥 10,000원","유산슬밥 10,000원","잡탕밥 11,000원","공기밥 1,000원"},
            {"탕수육\n(小) 15,000원\n(中) 20,000원\n(大) 25,000원","사천탕수육\n(小) 18,000원\n(中) 23,000원\n(大) 28,000원","깐풍기\n(中) 25,000원\n(大) 25,000원","고추잡채\n(中) 25,000원\n(大) 35,000원","양장피\n(中) 25,000원\n(大) 35,000원","유산슬\n(中) 25,000원\n(大) 35,000원","팔보채\n(中) 25,000원\n(大) 35,000원","깐쇼새우\n(中) 25,000원\n(大) 35,000원","잡탕 30,000원","군만두 4,000원","물만두 4,000원"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyrin);

        ExpandableListAdapter listAdapter = new HyrinActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HyrinActivity.this, MapFragmentActivity.class);
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new HyrinActivity.ItemSelectedListener());
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
            TextView textView = new TextView(HyrinActivity.this);
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
            Typeface typeface = getResources().getFont(R.font.lottemartdreamlight);
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
            textView.setTextColor(Color.parseColor("#3F51B5"));
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextSize(17);
            Typeface typeface = getResources().getFont(R.font.lottemartdreamlight);
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
                    Intent intent = new Intent(HyrinActivity.this, HomeActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(HyrinActivity.this, MailActivity.class);
                    intent2.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(HyrinActivity.this, MypageActivity.class);
                    intent3.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}