package com.example.myapplication.restaurant.cheonan.japanese;

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

public class MadangActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"돈까스/롤","뽁밥/덮밥","초밥","세트메뉴"};
    String[][] childs={{"정식(미니우동) +1,000원","순살돈까스 7,000원","이탈리안돈까스 8,000원","생선까스 8,000원","일식돈까스 7,000원","일식치즈돈까스 8,000원","킬링(새우튀김롤) 9,000원","터치미(날치알롤) 8,000원","딥키스(크림치즈연어롤) 9,000원","드레곤파이어(장어롤) 10,000원"},
            {"새우볶음밥 6,000원","낙지볶음밥 6,500원","알밥 6,000원","회 덮밥 6,500원","장어 덮밥 7,000원","돈가스 덮밥 7,000원","왕새우 돈까스 덮밥 8,000원"},
            {"정식(미니우동) +1,000원","스시마당(모듬)초밥(10PCS) 9,000원","특모듬 A초밥(20PCS) 17,000원","특모듬 B초밥(30PCS) 25,000원","새우초밥(10PCS) 8,000원","유부알초밥(10PCS) 7,000원","연어초밥(10PCS) 10,000원","문어초밥(10PCS) 9,000원","한치초밥(10PCS) 9,000원","장어초밥(10PCS) 10,000원","광어초밥(10PCS) 11,000원","도미초밥(10PCS) 11,000원"},
            {"커플스페셜(2인) 23,000원\n초밥 18pcs + 터치미 5pcs + 미니우동 2","기분ZONE세트(3인) 35,000원\n초밥 25pcs + 킬링롤 5pcs + 새우롤 3pcs + 치즈스틱 3pcs + 지마구 3pcs + 미니우동 3","나홀로세트(앞뜰) 12,000원\n터치미롤 5pcs + 초밥 5pcs + 미니우동 1","나홀로세트(뒷뜰) 12,000원\n 킬링롤 5pcs + 초밥 5pcs + 미니우동 1",
                    "어린이세트(1인) 11,000원\n초밥 8pcs + 치즈스틱 3pcs + 미니우동 1","덤앤덤세트A(1인) 12,000원\n순살돈까스 + 초밥 5pcs + 미니우동 1","덤앤덤세트B(1인) 13,000원\n이탈리안돈까스 + 초밥 5pcs + 미니우동 1","돈까스&뽁밥세트A(2인) 17,000원\n순살돈까스 + 낙지볶음밥 + 미니우동 2","돈까스&뽁밥세트B(2인) 17,500원\n이탈리안돈까스 + 새우볶음밥 + 미니우동 2",
            "알밥&우동세트(1인) 8,000원\n알밥 + 미니우동 1","덮밥&우동세트(1인) 8,500원\n회덮밥 + 미니우동 1","뽁밥&우동세트(1인) 8,000원\n낙지볶음밥 + 미니우동 1"}};

    private Button map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madang);

        ExpandableListAdapter listAdapter = new MadangActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MadangActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new MadangActivity.ItemSelectedListener());
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
            TextView textView = new TextView(MadangActivity.this);
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
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.home:
                    Intent intent = new Intent(MadangActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(MadangActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(MadangActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}