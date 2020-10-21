package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.network.ServiceApi;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MailActivity extends AppCompatActivity {

    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        mContext = getApplicationContext();

        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("받은 쪽지함")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("보낸 쪽지함")));

        mViewPager = (ViewPager) findViewById(R.id.pager_content);
        mContentPagerAdapter = new ContentsPagerAdapter(
                getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mContentPagerAdapter);

        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        ImageButton backbutton = (ImageButton) findViewById(R.id.imageButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MailActivity.this, HomeActivity.class);
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                MailActivity.this.startActivity(intent);
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void onBackPressed() {
        //super.onBackPressed();
    }

    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        return tabView;
    }

    public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

        private int mPageCount;

        public ContentsPagerAdapter(FragmentManager fm, int pageCount) {
            super(fm);
            this.mPageCount = pageCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ReceiveListFragment receiveListFragment = new ReceiveListFragment();
                    Bundle bundle = new Bundle();
                    String nickname = getIntent().getStringExtra("NICKNAME_EXTRA");
                    bundle.putString("nickname", nickname);
                    receiveListFragment.setArguments(bundle);
                    return receiveListFragment;

                case 1:
                    SendListFragment sendListFragment = new SendListFragment();
                    Bundle bundle1 = new Bundle();
                    String nickname1 = getIntent().getStringExtra("NICKNAME_EXTRA");
                    bundle1.putString("nickname", nickname1);
                    sendListFragment.setArguments(bundle1);
                    return sendListFragment;

                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return mPageCount;
        }
    }
}