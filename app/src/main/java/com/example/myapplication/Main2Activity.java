package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    String myJSON;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        list=(ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.35.34/matePHP_Connector.php");
    }

    protected void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray("result");

            for(int i=0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                String title = c.getString("title");
                String writer = c.getString("writer");
                String content = c.getString("content");
                String date = c.getString("date");
                String cate = c.getString("cate");

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put("title", title);
                persons.put("writer", writer);
                persons.put("content",content);
                persons.put("date",date);
                persons.put("cate",cate);

                boolean add = personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Main2Activity.this, personList,R.layout.list_item,
                    new String[]{"title","writer","content","date","cate"},
                    new int[]{R.id.email, R.id.name, R.id.nickname, R.id.password, R.id.gender}
            );

            list.setAdapter(adapter);
        } catch (JSONException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json=bufferedReader.readLine()) != null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                super.onPostExecute(result);
                myJSON = result;
                showList();

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
