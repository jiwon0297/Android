package com.example.myapplication.mate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.lost.LostData;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater = null;
    ArrayList<MateWriteData> sample = null;
    private ServiceApi service;

    private int nlistCnt=0;

    public MyAdapter(ArrayList<MateWriteData> _data){
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
            convertView = mLayoutInflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView oTextCampus = (TextView) convertView.findViewById(R.id.textCampus);
        TextView oTextTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView oTextId = (TextView) convertView.findViewById(R.id.textId);

        oTextCampus.setText(sample.get(position).campus);
        oTextTitle.setText(sample.get(position).title);
        oTextId.setText(sample.get(position).nickname);
        return convertView;

    }
}