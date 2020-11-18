package com.example.myapplication.lost;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LostSearchAdapter extends BaseAdapter {
    Context context;

    LayoutInflater inflater = null;
    ArrayList<LostSearchData> data = null;
    private ServiceApi service;
    Filter listFilter;

    private int nlistCnt = 0;

    public LostSearchAdapter(ArrayList<LostSearchData> _data) {
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
        TextView oTextDate = (TextView) convertView.findViewById(R.id.textDate);
        TextView oTextUrl = (TextView) convertView.findViewById(R.id.url);

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetext = transFormat.format(data.get(position).date);

        oTextCampus.setText(data.get(position).campus);
        oTextTitle.setText(data.get(position).title);
        oTextId.setText(data.get(position).nickname);
        oTextNumber.setText(String.valueOf(data.get(position).number));
        oTextContent.setText(data.get(position).content);
        oTextType.setText(data.get(position).type);
        oTextUrl.setText(data.get(position).url);
        oTextDate.setText(datetext);

        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = 170;
        convertView.setLayoutParams(layoutParams);

        return convertView;

    }
}
