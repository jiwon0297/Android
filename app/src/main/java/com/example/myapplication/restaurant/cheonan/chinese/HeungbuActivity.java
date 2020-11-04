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

public class HeungbuActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"세트메뉴","흥부반점 별미","반반메뉴","면류","밥류","요리류"};
    String[][] childs={{"나홀로 1(탕수육+짜장1) 14,000원","나홀로 2(탕수육+짬뽕1) 15,000원","나홀로 3(탕수육+볶음밥1) 15,000원","혼자서(탕수육+간짜장1) 15,000원","둘이서(탕수육+짜장2) 16,000원","A(탕수육+간짜장1+짜장1) 19,000원","B(탕수육+간짜장1+짬뽕1) 20,000원","C(탕수육+간짜장1+볶음밥1) 21,000원","D(탕수육+볶음밥1+짜장1) 20,000원","E(탕수육+볶음밥1+짬뽕1) 21,000원","1(탕수육+짜장2) 18,000원","1.5(탕수육+짜장1+짬뽕1) 19,000원","2(탕수육+짬뽕2) 21,000원","3(탕수육+짜장1+짬뽕1+군만두) 22,000원","4(탕수육+간짜장2) 20,000원","5(탕수육+볶음밥2) 21,000원","6(탕수육+해물쟁반짜장1) 23,000원","7(탕수육+해물쟁반짬뽕1) 25,000원"},
            {"해물쟁반짜장 15,000원","해물쟁반짬뽕 17,000원"},
            {"짬짜면 7,000원","볶짬면 8,000원","볶짜면 8,000원","탕짜면 8,000원","탕짬면 8,000원","탕볶밥 8,000원"},
            {"짜장면 5,000원","짬뽕 7,000원","간짜장 7,000원","우동 7,000원","울면 7,000원","기스면 8,000원","삼선짜장 9,000원","삼선간짜장 9,000원","삼선짬뽕 9,000원","삼선열폭탄짬뽕 9,000원","삼선우동 9,000원","삼선울면 9,000원","고추짬뽕 9,000원","쟁반짜장(2인) 15,000원","쟁반짬뽕(2인) 15,000원","흥부열폭탄짬뽕 7,000원","콩국수 7,000원","물냉면 7,000원","비빔냉면 7,000원","냉짬뽕 8,000원"},
            {"짜장밥 7,000원","짬뽕밥 7,000원","볶음밥 7,000원","오므라이스 7,000원","갈비탕 7,000원","육개장 7,000원","제육덮밥 7,000원","오징어덮밥 8,000원","송이덮밥 8,000원","잡채밥 8,000원","마파두부밥 8,000원","새우볶음밥 8,000원","삼선볶음밥 8,000원","유산슬밥 10,000원","고추잡채밥 10,000원","잡탕밥 10,000원","삼선짬뽕밥 9,000원","고추짬뽕밥 9,000원","삼선폭탄짬뽕밥\n(보통) 9,000원\n(곱빼기) 11,000원","공기밥 추가 1,000원"},
            {"탕수육\n(소) 15,000원\n(중) 20,000원\n(대) 25,000원","잡채 20,000원","깐풍기 30,000원","고추잡채 30,000원","팔보채 30,000원","양장피 30,000원","잡탕 30,000원","유산슬 30,000원","깐쇼새우 30,000원"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heungbu);

        ExpandableListAdapter listAdapter = new HeungbuActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeungbuActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new HeungbuActivity.ItemSelectedListener());
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
            TextView textView = new TextView(HeungbuActivity.this);
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
                    Intent intent = new Intent(HeungbuActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(HeungbuActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(HeungbuActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}