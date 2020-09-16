package com.example.myapplication.lost;

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

public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private ArrayList<LostData> data = null;
    private ServiceApi service;

    private int nlistCnt=0;

    public ListAdapter(ArrayList<LostData> _data){
        data = _data;
        nlistCnt = data.size();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView oTextCampus = (TextView) convertView.findViewById(R.id.textCampus);
        TextView oTextTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView oTextId = (TextView) convertView.findViewById(R.id.textId);
        TextView oTextNumber = (TextView) convertView.findViewById(R.id.textNumber);
        TextView oTextContent = (TextView) convertView.findViewById(R.id.textContent);
        TextView oTextType = (TextView) convertView.findViewById(R.id.type);

        oTextCampus.setText(data.get(position).campus);
        oTextTitle.setText(data.get(position).title);
        oTextId.setText(data.get(position).nickname);
        oTextNumber.setText(data.get(position).number);
        oTextContent.setText(data.get(position).content);
        oTextType.setText(data.get(position).type);

        return convertView;

    }
}
