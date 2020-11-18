package com.example.myapplication.restaurant.cheonan.korean;

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

public class GobgobgobActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"세트 메뉴","곱창 메뉴(기본 2인분 이상 주문가능)","막창 메뉴(기본 2인분 이상 주문가능)","직화 메뉴","토핑 메뉴","사이드 메뉴","주류&음료"};
    String[][] childs={{"세트 공통구성\n콘치즈/치즈/눈꽃치즈 택1 + 날치마요주먹밥/볶음밥 택1","태양계 세트\nA. 마늘곱창/야채곱창/데리야끼곱창 2인분 + 공통구성 23,500원\nB. 순대곱창/새우곱창 2인분 + 공통구성 24,500원","은하계 세트\nA. 소금구이막창/데리야끼막창/공통구성 25,500원\nB. 양념막창 2인분 + 공통구성 26,500원","안드로메다 세트\nA. 곱창메뉴 2인분 + 얼큰곱창전골(중) 41,500원\nB. 막창메뉴 2인분 + 얼큰곱창전골(중) 43,000원"},
            {"매콤마늘곱창 9,500원","매콤야채곱창 9,000원","데리야끼곱창 9,500원","매콤순대곱창 10,000원","매콤새우곱창 10,000원","마라야채곱창 11,000원","얼큰곱창전골(소) 11,000원","얼큰곱창전골(중) 21,000원"},
            {"소금구이막창 11,000원","데리야끼막창 11,000원","매콤양념막창 11,500원","마라야채막창 12,000원"},
            {"볼케이노오소리 22,000원","데리야끼오소리 22,000원","매콤오돌뼈 22,000원","간장오돌뼈 22,000원","석쇠불고기 22,000원","고추장석쇠불고기 22,000원"},
            {"눈꽃치즈 3,000원","치즈추가 3,000원","콘치즈 3,000원","가쓰오부시 3,000원"},
            {"날치마요주먹밥 3,000원","날치볶음밥 3,000원","계란찜추가 2,000원","떡추가 2,000원","당면추가 2,000원","공기밥 1,000원"},
            {"음료수 2,000원","소주 3,500원","맥주 4,000원","막걸리 4,000원"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gobgobgob);

        ExpandableListAdapter listAdapter = new GobgobgobActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GobgobgobActivity.this, MapFragmentActivity.class);
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new GobgobgobActivity.ItemSelectedListener());
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
            TextView textView = new TextView(GobgobgobActivity.this);
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
                    Intent intent = new Intent(GobgobgobActivity.this, HomeActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(GobgobgobActivity.this, MailActivity.class);
                    intent2.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(GobgobgobActivity.this, MypageActivity.class);
                    intent3.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}