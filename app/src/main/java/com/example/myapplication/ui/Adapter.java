package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;

public class Adapter extends PagerAdapter {

    private int[] images = {R.drawable.bg, R.drawable.seuldan1, R.drawable.seuldan2, R.drawable.seuldan3, R.drawable.seuldan4, R.drawable.seuldan5};
    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView textView = (TextView) v.findViewById(R.id.textView);

        imageView.setImageResource(images[position]);

        if(position==0){
            imageView.setColorFilter(null);
        } else if(position == 1){
            imageView.setColorFilter(Color.parseColor("#77000000"), PorterDuff.Mode.DARKEN);
            textView.setText("슬기로운 단국생활 앱에서 \n 마음에 맞는 메이트를 구해보세요.");
        } else if(position == 2){
            imageView.setColorFilter(Color.parseColor("#77000000"), PorterDuff.Mode.DARKEN);
            textView.setText("공모전, 스터디 등 학업부문부터 \n 하우스, 푸드 메이트의 생활부문까지 \n 다양한 메이트를 구하실 수 있습니다.");
        } else if(position == 3){
            imageView.setColorFilter(Color.parseColor("#77000000"), PorterDuff.Mode.DARKEN);
            textView.setText("오늘은 어떤 것을 먹을지 고민되시나요? \n 슬기로운 단국생활 어플에서 \n 식당 및 카페 추천기능을 사용해보세요!");
        } else if(position == 4){
            imageView.setColorFilter(Color.parseColor("#77000000"), PorterDuff.Mode.DARKEN);
            textView.setText("학교 주변 원하시는 카페와 식당의 정보, \n 어플에 한 눈에 보기 쉽게 정리되어 있습니다. \n 오늘은 평소와는 다르게, \n 새로운 장소를 찾아가보시는 건 어떠세요?");
        } else if(position == 5){
            imageView.setColorFilter(Color.parseColor("#8A000000"), PorterDuff.Mode.DARKEN);
            textView.setText("혹시 물건을 잃어버리거나, \n 분실물을 주워보신 적 있으세요? \n 슬기로운 단국생활앱에서 주인을 찾아주세요!");
        }
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }

}
