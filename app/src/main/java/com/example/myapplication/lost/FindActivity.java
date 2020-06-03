package com.example.myapplication.lost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.ui.FragHome;
import com.example.myapplication.ui.FragMail;
import com.example.myapplication.ui.FragMypage;
import com.example.myapplication.ui.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragHome fragHome = new FragHome();
    private FragMail fragMail = new FragMail();
    private FragMypage fragMypage = new FragMypage();

    private ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        String[] strDate = {"2020-04-07", "2020-05-27"};
        int nCnt = 0;
        ArrayList<ItemData> oData = new ArrayList<>();
        for (int i=0;i<1000;i++){
            ItemData oItem = new ItemData();
            oItem.strTitle = "데이터 " + (i+1);
            oItem.strDate = strDate[nCnt++];
            oData.add(oItem);
            if (nCnt >= strDate.length) nCnt = 0;

        }
        listView = (ListView)findViewById(R.id.listView);
        ListAdapter oAdapter = new ListAdapter(oData);
        listView.setAdapter(oAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new FindActivity.ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    transaction.replace(R.id.Main_Frame, fragHome).commitAllowingStateLoss();
                    break;
                case R.id.mail:
                    transaction.replace(R.id.Main_Frame, fragMail).commitAllowingStateLoss();
                    break;
                case R.id.mypage:
                    transaction.replace(R.id.Main_Frame, fragMypage).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }


}
