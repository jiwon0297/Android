package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myapplication.R;
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
 * Use the {@link SendListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ServiceApi service;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SendListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendListFragment newInstance(String param1, String param2) {
        SendListFragment fragment = new SendListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout layout = (FrameLayout)inflater.inflate(R.layout.fragment_send_list, container, false);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        ImageButton refreshbutton = (ImageButton) layout.findViewById(R.id.refreshbtn);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        String nickname = getArguments().getString("nickname");
        List<MailSendListData> oData = new ArrayList<>();
        service.mailsendlist(new MailSendListData(nickname)).enqueue(new Callback<MailSendListResponse>() {
            @Override
            public void onResponse(Call<MailSendListResponse> call, Response<MailSendListResponse> response) {
                MailSendListResponse result = response.body();
                if (result.getCode() == 200) {
                    MailSendListResponse sample = result;
                    for (MailSendListResponse a :sample.getResult() ){
                        MailSendListData oItem = new MailSendListData();
                        oItem.recipient = a.getRecipient();
                        oItem.sender = a.getSender();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.number = a.getNumber();
                        oData.add(oItem);
                    }
                    ListView listView = (ListView)layout.findViewById(R.id.listView1);
                    SendMailAdapter oAdapter = new SendMailAdapter((ArrayList<MailSendListData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String datetext = transFormat.format(oData.get(position).date);
                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            intent.putExtra("NICKNAME_EXTRA", oData.get(position).sender);
                            intent.putExtra("NUMBER_EXTRA", oData.get(position).number);
                            intent.putExtra("RECIPIENT", oData.get(position).recipient);
                            intent.putExtra("DATE_EXTRA", datetext);
                            intent.putExtra("CONTENT_EXTRA", oData.get(position).content);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MailSendListResponse> call, Throwable t) {
                Log.e("에러 발생", t.getMessage());
            }
        });
        return layout;
    }
}