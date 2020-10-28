package com.example.myapplication.restaurant.jukjeon.korean;

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

public class KimchiActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"메인메뉴", "주류"};
    String[][] childs={{"김치짜글이 8,000원","고기튀김 5,000원"},
            {"소주 4,000원","맥주 4,000원","막걸리 3,000원","음료 1,500원"}};

    private Button map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kimchi);

        ExpandableListAdapter listAdapter = new KimchiActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KimchiActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new KimchiActivity.ItemSelectedListener());
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
            TextView textView = new TextView(KimchiActivity.this);
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
                    Intent intent = new Intent(KimchiActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(KimchiActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(KimchiActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}