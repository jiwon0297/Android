package com.example.myapplication.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;

public class MyLostAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater = null;
    ArrayList<MyLostData> sample = null;
    private ServiceApi service;

    private int nlistCnt=0;

    public MyLostAdapter(ArrayList<MyLostData> _data){
        sample = _data;
        nlistCnt = sample.size();
    }

    @Override
    public int getCount() {
        Log.i("TAG", "getCount");
        return nlistCnt;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (mLayoutInflater == null)
            {
                mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = mLayoutInflater.inflate(R.layout.mymatelist_item, parent, false);
        }

        TextView oTextNumber = (TextView) convertView.findViewById(R.id.textNumber);
        TextView oTextCate = (TextView) convertView.findViewById(R.id.textCate);
        TextView oTextTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView oTextNickname = (TextView) convertView.findViewById(R.id.textNickname);
        TextView oTextDate = (TextView) convertView.findViewById(R.id.textDate);
        TextView oTextContent = (TextView) convertView.findViewById(R.id.textContent);

        oTextNumber.setText(String.valueOf(sample.get(position).number));
        oTextTitle.setText(sample.get(position).title);
        oTextNickname.setText(sample.get(position).nickname);
        oTextContent.setText(sample.get(position).content);

        return convertView;

    }
}
