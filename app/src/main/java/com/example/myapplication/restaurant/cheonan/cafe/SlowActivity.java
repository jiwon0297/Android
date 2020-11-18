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

public class SlowActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"커피","봉달","브루","기타 음료","디저트"};
    String[][] childs={{"아메리카노 4,000원","플랫화이트 4,000원","카푸치노 4,500원","카페라떼 4,500원","카페모카 5,000원","바닐라라떼 5,000원","아인슈페너 5,000원"},
            {"봉달 아메리카노 4,000원","봉달 돌체라떼 5,000원","봉달 모카 5,000원","봉달 초코 5,000원","봉달 그린티 라떼 5,000원"},
            {"콜드 브루 6,000원","콜드 브루 라떼 6,500원","브루 커피 브라질 5,000원","브루 커피 스페셜티_에티오피아 5,000원","브루 커피 과테말라 5,000원"},
            {"카모마일 4,000원","얼 그레이 4,000원","밀크 티 5,000원","트리플 초콜릿 5,000원","그린티 라떼 5,000원","플레인 요거트 6,000원","레몬에이드 6,000원","오렌지 주스 6,000원","토마토 주스 6,000원","그레이트프룻 블랙티 5,000원"},
            {"당근 케이크 6,000원","치즈 케이크 6,000원","브라우니 5,000원","파운드 케이크 3,000원"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow);

        ExpandableListAdapter listAdapter = new SlowActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlowActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new SlowActivity.ItemSelectedListener());
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
            TextView textView = new TextView(SlowActivity.this);
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
                    Intent intent = new Intent(SlowActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(SlowActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(SlowActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}