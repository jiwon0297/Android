package com.example.myapplication.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReceiveMailAdapter extends BaseAdapter {
    LayoutInflater mLayoutInflater = null;
    ArrayList<MailReceiveListData> sample = null;
    private ServiceApi service;

    private int nlistCnt=0;

    public ReceiveMailAdapter(ArrayList<MailReceiveListData> _data){
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
            convertView = mLayoutInflater.inflate(R.layout.maillist_item, parent, false);
        }

        TextView oTextNumber = (TextView) convertView.findViewById(R.id.number);
        TextView oTextSender = (TextView) convertView.findViewById(R.id.person);
        TextView oTextDate = (TextView) convertView.findViewById(R.id.textDate);
        TextView oTextContent = (TextView) convertView.findViewById(R.id.content);

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetext = transFormat.format(sample.get(position).date);

        oTextNumber.setText(String.valueOf(sample.get(position).number));
        oTextSender.setText(sample.get(position).sender);
        oTextDate.setText(datetext);
        oTextContent.setText(sample.get(position).content);

        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = 170;
        convertView.setLayoutParams(layoutParams);

        return convertView;
    }
}
