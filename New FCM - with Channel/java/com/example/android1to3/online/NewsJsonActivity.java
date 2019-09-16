package com.example.android1to3.online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android1to3.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsJsonActivity extends AppCompatActivity {
    //String url="https://newsapi.org/v2/everything?q=bitcoin&from=2019-07-19&sortBy=publishedAt&apiKey=0c2367225d2440619d16b78342b56ab6";
    String url="https://restcountries.eu/rest/v2/all";

    ProgressDialog pd;
    private Context context;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_json);

        context=this;
        listView=findViewById(R.id.listView);

        MyAsyncTask asyncTask=new MyAsyncTask();
        asyncTask.execute();

    }

    //Param, Progress, Result
    class MyAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... voids) {
            OkHttpClient client=new OkHttpClient();
            Request.Builder requestBuilder=new Request.Builder();
            requestBuilder.url(url);
            Request request=requestBuilder.build();
            try {
                Response response=client.newCall(request).execute();
                String data=response.body().string();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=ProgressDialog.show(context,"Wait","Loading News!!!");
            //tvData.setText("Loading...");

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray=new JSONArray(s);
                //ArrayList<Country> countries=new ArrayList<>();
                ArrayList<CountryRoot> countries=new ArrayList<>();
                Gson gson=new Gson();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    /*String name=jsonObject.getString("name");
                    String capital=jsonObject.getString("capital");
                    Country country=new Country(name, capital);
                    countries.add(country);*/
                    CountryRoot root=gson.fromJson(jsonObject.toString(),CountryRoot.class);
                    countries.add(root);
                }
                //ArrayAdapter<Country> adapter=new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, countries);
                ArrayAdapter<CountryRoot> adapter=new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, countries);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //tvData.setText(s);
            pd.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
