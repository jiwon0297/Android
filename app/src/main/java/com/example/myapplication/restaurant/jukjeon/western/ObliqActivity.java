package com.example.myapplication.restaurant.jukjeon.western;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ObliqActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"Pasta", "Salad","Pizza","Risotto","Paella","AID"};
    String[][] childs={{"Tomato\nSpicy Arraviatar(매콤한 토마토 파스타) 13.0\nMushroom Bacon Tomato(머쉬룸 베이컨 토마토 파스타) 13.0\nChilli Shrimp Tomato(칠리 새우 토마토 파스타) 14.0\nSeafood Tomato(해산물 토마토 파스타) 15.5\nRagu Bolognese(라구 볼로네제 파스타) 13.5\nZuppa di Pesce(해장 파스타) 16.5",
            "Cream/Rose\nMushroom Bacon Cream(머쉬룸 베이컨 크림 파스타) 13.0\nMushroom Bacon Rose(머쉬룸 베이컨 로제 파스타) 14.0\nChilli Cream(매콤 크림 파스타) 13.5\nCarbonara(까르보나라) 14.0\nGorgonzola Mushroom Cream(고르곤졸라 머쉬룸 크림 파스타) 14.0\nSeafood Cream(해산물 크림 파스타) 15.5\nSeafood Rose(해산물 로제 파스타) 16.0\nReal Cheese ream(리얼치즈 크림 파스타) 14.0",
            "Oil\nAglio e Olio(알리오 올리오 파스타) 12.5\nJalapeno Oil(할라피뇨 오일 파스타) 13.0\nGarlic Annchovy Oil(갈릭&엔초비 오일 파스타) 13.0\nNero di Sepia Oil(오징어먹물 오일 파스타) 13.0\nVongole(봉골레 파스타) 14.0"},
            {"Oblique House(오블리끄 하우스 샐러드) 6.0","Ricotta cheese(리코타 치즈 샐러드) 12.5","Dijion Mushroom Chicken 12.8\n(디존 머쉬룸 치킨 샐러드)","Chop grill Steak(300g) 36.0(찹 그릴 스테이크 샐러드)  *음료 1+1)","Chicken Casesar 12.5\n(시저 샐러드(닭 가슴살, 통 로메인))"},
            {"Gorgonzola Pastry Pizza 16.0\n(고르곤졸라 루꼴라 패스츄리 피자)","Jalapeno Tomato Pastry Pizza 16.0\n(할라피뇨 토마토 패스츄리 피자)"},
            {"Chilli SHrimp Tomato 14.0\n(칠리 새우 토마토 리조또)","Mushroom Cream 13.5\n(머쉬룸 크림 리조또)","Gorgonzola Mushroom Cream 14.0\n(고르곤졸라 머쉬룸 크림 리조또)","Seafood Tomato 15.5\n(해산물 토마토 리조또)","Seafood Cream 15.5\n(해산물 크림 리조또)","Seafood Rose 16.0\n(해산물 로제 리조또)"},
            {"Seafood Paella(해산물 빠에야) 16.5","Nero di sepia Paella(오징어 먹물 빠에야) 16.0"},
            {"자몽/레몬/오렌지 5.0","애플망고/청포도 5.5","콜라/스프라이트 3.0"}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obliq);

        ExpandableListAdapter listAdapter = new ObliqActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ObliqActivity.ItemSelectedListener());
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
            TextView textView = new TextView(ObliqActivity.this);
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
                    Intent intent = new Intent(ObliqActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(ObliqActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(ObliqActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}