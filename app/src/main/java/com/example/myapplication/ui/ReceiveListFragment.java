package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.mate.MyAdapter;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceiveListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiveListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ServiceApi service;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceiveListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceiveListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceiveListFragment newInstance(String param1, String param2) {
        ReceiveListFragment fragment = new ReceiveListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout layout = (FrameLayout)inflater.inflate(R.layout.fragment_receive_list, container, false);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        ImageButton refreshbutton = (ImageButton) layout.findViewById(R.id.refreshbtn);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        String nickname = getArguments().getString("nickname");
        List<MailReceiveListData> oData = new ArrayList<>();
        service.mailreceivelist(new MailReceiveListData(nickname)).enqueue(new Callback<MailReceiveListResponse>() {
            @Override
            public void onResponse(Call<MailReceiveListResponse> call, Response<MailReceiveListResponse> response) {
                MailReceiveListResponse result = response.body();
                if (result.getCode() == 200) {
                    MailReceiveListResponse sample = result;
                    for (MailReceiveListResponse a :sample.getResult() ){
                        MailReceiveListData oItem = new MailReceiveListData();
                        oItem.recipient = a.getRecipient();
                        oItem.sender = a.getSender();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.number = a.getNumber();
                        oData.add(oItem);
                    }
                    ListView listView = (ListView)layout.findViewById(R.id.listView1);
                    ReceiveMailAdapter oAdapter = new ReceiveMailAdapter((ArrayList<MailReceiveListData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String datetext = transFormat.format(oData.get(position).date);
                            Intent intent = new Intent(getContext(), MailViewActivity.class);
                            intent.putExtra("SENDER", oData.get(position).sender);
                            intent.putExtra("NUMBER_EXTRA", oData.get(position).number);
                            intent.putExtra("RECIPIENT", oData.get(position).recipient);
                            intent.putExtra("NICKNAME_EXTRA", oData.get(position).recipient);
                            intent.putExtra("DATE_EXTRA", datetext);
                            intent.putExtra("CONTENT_EXTRA", oData.get(position).content);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MailReceiveListResponse> call, Throwable t) {
                Log.e("에러 발생", t.getMessage());
            }
        });
        return layout;
    }

}