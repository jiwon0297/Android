package com.example.myapplication.mate;

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

public class MateSearchAdapter extends BaseAdapter {
    Context context;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MateSearchData> sample = null;
    private ServiceApi service;
    Filter listFilter;

    private int nlistCnt = 0;

    public MateSearchAdapter(ArrayList<MateSearchData> _data) {
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
}
