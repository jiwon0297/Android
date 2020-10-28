package com.example.myapplication.mate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    Context context;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MateData> sample = new ArrayList<MateData>();
    private ServiceApi service;
    private ArrayList<MateData> listItem = sample;
    Filter listFilter;

    private int nlistCnt = 0;

    public MyAdapter(ArrayList<MateData> _data) {
        sample = _data;
        nlistCnt = sample.size();
    }

    @Override
    public int getCount() {
        Log.i("TAG", "getCount");
        return nlistCnt;
    }

    public void setSearchData(String s){
        if(s.length() > 0 ) {
            for (int i = 0; i < sample.size(); i++) {
                if (!sample.get(i).title.contains(s)) {
                    sample.remove(i);
                    i--;
                }
            }
            notifyDataSetChanged();
        }
    }
    public void resetData(ArrayList<MateData> m){
        this.sample = m;
        notifyDataSetChanged();
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
        if (convertView == null) {
            final Context context = parent.getContext();
            if (mLayoutInflater == null) {
                mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = mLayoutInflater.inflate(R.layout.matelist_item, parent, false);
        }

        TextView oTextCate = (TextView) convertView.findViewById(R.id.textCate);
        TextView oTextNumber = (TextView) convertView.findViewById(R.id.textNumber);
        TextView oTextCampus = (TextView) convertView.findViewById(R.id.textCampus);
        TextView oTextTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView oTextNickname = (TextView) convertView.findViewById(R.id.textNickname);
        TextView oTextDate = (TextView) convertView.findViewById(R.id.textDate);
        TextView oTextContent = (TextView) convertView.findViewById(R.id.textContent);

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetext = transFormat.format(sample.get(position).date);

        oTextCate.setText(sample.get(position).cate);
        oTextNumber.setText(String.valueOf(sample.get(position).number));
        oTextCampus.setText(sample.get(position).campus);
        oTextTitle.setText(sample.get(position).title);
        oTextNickname.setText(sample.get(position).nickname);
        oTextDate.setText(datetext);
        oTextContent.setText(sample.get(position).content);

        return convertView;

    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = listItem;
                results.count = listItem.size();
            } else {
                ArrayList<MateData> itemList = new ArrayList<>();
                for (MateData item : listItem) {
                    if (item.title.toUpperCase().contains(constraint.toString().toUpperCase()))
                        itemList.add(item);
                }
                results.values = itemList;
                results.count = itemList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            sample = (ArrayList<MateData>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}

