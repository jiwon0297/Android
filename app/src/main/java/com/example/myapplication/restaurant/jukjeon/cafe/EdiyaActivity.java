package com.example.myapplication.restaurant.jukjeon.cafe;

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

public class EdiyaActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
        ExpandableListView listView;
        String[] groups={"커피","콜드브루(Only ICED)","음료","버블티(Only ICED)","플랫치노","블렌딩티","에이드","쉐이크","빙수","베이커리"};
        String[][] childs={{"Extra +200원","연유 카페 라떼 3,800원","카페 아메리카노 3,200원","카페 라떼 3,700원","카페 모카 3,900원","카라멜 마끼아또 3,900원","바닐라 라떼 3,900원","화이트 초콜릿 모카 3,900원","민트 모카 4,200원"},
                {"디카페인 +300원","콜드브루 크림넛 4,500원","콜드브루 티라미수 4,800원","연유 콜드브루 4,300원","흑당 콜드브루 3,700원","콜드브루 아메리카노 3,700원","콜드브루 라떼 4,200원","콜드브루 화이트 비엔나 4,500원"},
                {"달고나 라떼(Only ICED) 3,500원","흑당 라떼(Only ICED) 3,300원","초콜릿 3,700원", "화이트 초콜릿 3,700원","민트 초콜릿 4,000원","녹차 라떼 3,700원","이곡 라떼 3,500원","토피 넛 라떼 4,000원","고구마 라떼 4,000원","딸기 주스 4,200원","골드키위주스 4,200원","홍시주스 4,200원"},
                {"버블 흑당 라떼 4,300원","버블 흑당 콜드브루 4,700원","디카페인 버블흑당 콜드브루 5,000원","버블 크림 밀크티 4,300원"},
                {"그린파인 후룻치노 3,500원","블루코코 후룻치노 3,800원","커피 플랫치노 3,500원","망고 플랫치노 3,500원","모카 플랫치노 3,800원","카라멜 플랫치노 3,800원","꿀복숭아 플랫치노 3,500원","자몽 플랫치노 3,800원","녹차 플랫치노 4,200원","초콜릿 칩 플랫치노 4,200원","민트 초콜릿 칩 플랫치노 4,200원","플레인 요거트 플랫치노 4,200원","블루베리 요거트 플랫치노 4,200원","딸기 요거트 플랫치노 4,200원"},
                {"달고나 밀크티(Only ICED) 3,800원","밀크티 3,800원","Extra ICED 석류 애플라임 4,800원","Extra ICED 유자 피나콜라다 4,800원","Extra ICED 자몽 네이블오렌지 4,800원","아이스티(복숭아/레몬) 2,500원","어린잎 녹차 2,800원","얼그레이 홍차 2,800원","로즈 자스민 티 2,800원","캐모마일 레드 티 3,000원","페퍼민트 티 3,000원","레몬차(Only HOT) 3,800원","자몽차(Only HOT) 3,800원","유자차(Only HOT) 3,800원","레몬 스윗플럼(Only HOT) 4,200원","자몽 네이블오렌지(Only HOT) 4,200원","유자 피나콜라다(Only HOT) 4,200원","석류 오리지널(Only HOT) 3,800원","제주청귤 오리지널(Only HOT) 3,800원","석류 애플라임(Only HOT) 4,200원","제주청귤 블라썸(Only HOT) 4,200원"},
                {"Extra +1,000원","레몬 에이드 3,800원","자몽 에이드 3,800원","청포도 에이드 3,800원"},
                {"초코묻고더블 4,800원","밥대신라이스 4,800원","치즈가쿠키했대 4,800원","오리진 쉐이크 4,300원","초코 쿠키 쉐이크 4,500원","딸기 쉐이크 4,800원"},
                {"바닐라쿠키빙수 10,800원","망고샤베트빙수 10,800원","딸기치즈빙수 10,800원","눈꽃 빙수(팥/초코) 9,800원"},
                {"허니 카라멜 브레드 4,600원","플레인 와플 2,300원","크림치즈 와플 3,000원","생크림 와플 2,500원","프레즐 2,300원","베이글(플레인/어니언/블루베리) 1,900원","크로크무슈 3,800원"}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediya);

        ExpandableListAdapter listAdapter = new MyExpandableListAdapter();
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new EdiyaActivity.ItemSelectedListener());
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    public class MyExpandableListAdapter extends BaseExpandableListAdapter{

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
            TextView textView = new TextView(EdiyaActivity.this);
            textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            textView.setPadding(30,40,0,40);

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
                    Intent intent = new Intent(EdiyaActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(EdiyaActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(EdiyaActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}