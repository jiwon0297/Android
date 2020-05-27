package com.example.myapplication.mate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public MyAdapter(Context context, ArrayList<SampleData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SampleData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.matelist_item, null);

        TextView title = (TextView)view.findViewById(R.id.title);
        TextView writer = (TextView)view.findViewById(R.id.writer);
        TextView date = (TextView)view.findViewById(R.id.date);
        TextView content = (TextView)view.findViewById(R.id.content);

        title.setText(sample.get(position).getTitle());
        writer.setText(sample.get(position).getWriter());
        date.setText(sample.get(position).getDate());
        content.setText(sample.get(position).getContent());

        return view;
    }
}
