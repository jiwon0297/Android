package com.example.myapplication.restaurant.cheonan.chinese;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurant.map.MapFragmentActivity;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DongchunActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"세트메뉴","면류","밥류","돼지류","닭고기류","새우류"};
    String[][] childs={{"탕수육+짜장 20,000원","사춘탕수육+짜장2 27,000원","깐쇼새우+짜장2 27,000원","깐풍기+짜장2 27,000원","팔보채+짜장2 30,000원"},
            {"손짜장면 6,000원","손짬뽕 7,000원","손간짜장면 7,000원","손삼선간짜장면 8,000원","손매운삼선간짜장 9,000원","손해물짬뽕 8,000원","손해물우동 8,000원","손삼선짬뽕 9,000원","손삼선우동 9,000원","손갈비짬뽕 9,000원","손꼬막짬뽕 9,000원","손쟁반짜장(2인) 15,000원","손볶음짬뽕(2인) 16,000원"},
            {"짬뽕밥 7,000원","볶음밥 7,000원","잡채밥 8,000원","버섯덮밥 8,000원","마파두부밥 8,000원","삼선짬뽕밥 9,000원","유산슬밥 12,000원","잡탕밥(팔보채덮밥) 13,000원"},
            {"탕수육\nS 15,000원\nM 19,000원\nL 25,000원","사천탕수육\nS 20,000원\nM 25,000원\nL 30.000원","난자완스\nS 23,000원\nM 25,000원\nL 30.000원","동파육\nS 27,000원\nM 37,000원\nL 47,000원"},
            {"깐풍기\nS 23,000원\nM 33,000원\nL 43,000원","라조기\nS 23,000원\nM 33,000원\nL 43,000원","유린기\nS 23,000원\nM 33,000원\nL 43,000원"},
            {"크림새우\nS 23,000원\nM 33,000원\nL 43,000원","크림중새우\nS 27,000원\nM 37,000원\nL 47,000원","칠리중새우\nS 27,000d원\nM 37,000원\nL 47,000원","깐풍중새우\nS 27,000원\nM 37,000원\nL 47,000원","깐풍새우\nS 23,000원\nM 33,000원\nL 43,000원","깐소(칠리)새우\nS 23,000원\nM 33,000원\nL 43,000원"}};

    private Button map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongchun);

        ExpandableListAdapter listAdapter = new DongchunActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DongchunActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new DongchunActivity.ItemSelectedListener());
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
            TextView textView = new TextView(DongchunActivity.this);
            textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            textView.setPadding(30,40,0,40);
            textView.setTextSize(15);

            return textView;
        }

        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent){
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition,childPosition).toString());

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

        public View getGroupView(int groupPosition,boolean isExpanded,
                                 View convertView, ViewGroup parent){
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());

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
                    Intent intent = new Intent(DongchunActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(DongchunActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(DongchunActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}