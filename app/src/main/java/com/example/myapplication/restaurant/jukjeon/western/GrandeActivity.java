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

public class GrandeActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
    ExpandableListView listView;
    String[] groups= new String[]{"Grande 4인 Set","Grande 2인 코스", "ZUPPA & STEAK","PIZZA","INSALATA","PASTA & RISOTTO"};
    String[][] childs={{"68.0","포마지오/마르게리타/피칸테","리코타 깔쵸네/풍기 샐러드/기로스/카프레제/만조 샐러드","알리오 올리오/까르보나라/볼로네제/버팔로","봉골레/마레/해산물 빠에야/리조또 마레","음료 4잔"},
            {"65.0/n에피타이저\n오늘의 스프\n샐러드\n쉐프추천 파스타\n안심 스테이크\n디저트\n커피"},
            {"ZUPPA\nZuppa del giorno(오늘의 스프) 8.0","STEAK\nLamb chop(양갈비 스테이크) 32.0\nFIletto di manzo(안심 스테이크) 39.0"},
            {"Formaggio(포마지오) 18.0","Margherita(마르게리타) 18.0","Picante(피칸테) 18.0","Himalayas(히말라야) 21.0","Grande Roll(그란데 총각) 21.0"},
            {"Ricotta calzone(리코타 깔쵸네) 18.0","Caprese(카프레제) 18.0","Insalata di funghi(풍기 샐러드) 18.0","Insalata di manzo(만조 샐러드) 19.0","Gyros(기로스) 19.0"},
            {"Aglio e olio(알리오 올리오) 15.0","Carbonara(까르보나라) 16.0","Bolonese(볼로네제) 17.0","Buffalo(버팔로) 17.0","Vongole e vino bianco(봉골레) 18.0","Mare(마레) 18.0","Seafood paella(해산물 빠에야) 17.0","Risotto di mare(리조또 마레) 18.0","Real Madrid(리얼 마드리드) 19.0","Pane di Seafood(빠네) 19.0","Hangover(행오버) 19.0","Granchio con rosa(그란치오) 19.0"}};

    private ImageButton map_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grande);

        ExpandableListAdapter listAdapter = new GrandeActivity.MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        map_button = findViewById(R.id.map_button);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrandeActivity.this, MapFragmentActivity.class);
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new GrandeActivity.ItemSelectedListener());
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
            TextView textView = new TextView(GrandeActivity.this);
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
                    Intent intent = new Intent(GrandeActivity.this, HomeActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(GrandeActivity.this, MailActivity.class);
                    intent2.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(GrandeActivity.this, MypageActivity.class);
                    intent3.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}