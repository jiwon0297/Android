package com.example.myapplication.restaurant.jukjeon.western;

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

public class DarinActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"SET MENU", "PASTA","PIZZA","BEER","BEVERAGE","COFFEE","BREAD","TEA","OTHER TEA","FRUIT JUICE(생과일 스무디로 가능)","SALAD","SIDE DISH","DESSERT"};
    String[][] childs={{"A set: Pasta set 18,000\n갓 구운빵\n오늘의 샐러드\n파스타(선택)\n치즈케익\n커피 or 차\n*soup 추가시 3,000","B set: Steak set 24,000\n갓 구운빵\n오늘의 샐러드\n청정우 호주산 채끝(200g)\n치즈케익\n커피 or 차\n*soup 추가시 3,000","C set: Steak set(3~4인 기준) 80,000\n갓 구운빵\n오늘의 샐러드\n오늘의 파스타(파스타 변경시 3,000원 추가)\n청정우 호주산 채끝(500g)\n오늘의 디저트\n커피 or 차"},
            {"Tomato pasta\n아마트리치아나 12,000\n머쉬룸토마토 13,000\n마레토마토 15,000","Cream pasta\n까르보나라 12,000\n새우날치알 13,000\n마레크림 15,000\n들깨 버섯 크림파스타 13,000\n바질 크랩 크림파스타 15,000","Oil pasta\n알리오올리오 12,000\n명란 13,000\n봉골레 13,000\n비앙코 15,000","RISOTTO\n머쉬룸크림 리조또 13,000\n마레토마토 리조또 15,000\n마레크림 리조또 15,000"},
            {"마르게리따 12,000","머쉬룸 피자 13,000","고르곤졸라 13,000","샐러드 피자 13,000","베지터블 피자 13,000","스파이시 새우 15,000","스테이크 피자 15,000"},
            {"카스 4,000","클라우드 5,000","하이네켄 6,000","코로나 6,000","호가든 6,000"},
            {"자몽주스 4,500","오렌지주스 4,500","아이스티 4,500","레몬에이드 5,000","유자에이드 5,000","오렌지에이드 5,000","스프라이트 3,000","콜라 3,000","밀크쉐이크 6,000"},
            {"*아이스: 500원 추가","에스프레소 4,000","에스프레소 콘파냐 4,500","아메리카노 4,500","카페라떼 5,000","카푸치노 5,000","바닐라 라떼 5,500","카라멜 마끼아또 5,500","카페모카 5,500","카라멜 카페모가 5,500","아포가토 5,500"},
            {"베이글 3,000","베이글세트(베이글+아메리카노 커피) 6,000","아미띠에 와플 8,000","아미띠에와플 세트(와플+아메리카노 커피2) 15,000"},
            {"녹차 5,500","얼그레이 5,500","다즐링 5,500","국화차 5,500","페퍼민트 5,500","로즈마리 5,500","루이보스 5,500","유자차 5,500","생강차 5,500","쌍화차 5,500","대추차 5,500"},
            {"레몬티 5,000","아이스티 4,500","핫초콜렛 5,500","그린티라떼 5,500","고구마라떼 5,500"},
            {"키위 6,000","토마토 6,000","바나나 6,000","딸기 6,000","사과 6,000"},
            {"Mista 10,000","Beef Salad 18,000","Hot Shrimp Salad 15,000"},
            {"마른안주 8,000","카프레제 13,000","모듬과일치즈 30,000","찹스테이크 30,000"},
            {"치즈케익 6,000","아이스크림(바닐라) 5,000"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darin);

        ExpandableListAdapter listAdapter = new DarinActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DarinActivity.this, MapFragmentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new DarinActivity.ItemSelectedListener());
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
            TextView textView = new TextView(DarinActivity.this);
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
                    Intent intent = new Intent(DarinActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(DarinActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(DarinActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}