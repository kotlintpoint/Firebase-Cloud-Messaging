package com.example.android1to3.php_employee;

import android.os.AsyncTask;
import android.os.Build;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class EmployeeAsyncTask extends AsyncTask<String,Void,String> {

    // interface to jump from asynctask to activity/fragment
    public interface AsyncCallBack{
        void onResponse(String data);
    }
    private AsyncCallBack callBack;
    private HashMap<String, String> hashMap;

    public EmployeeAsyncTask(AsyncCallBack callBack, HashMap<String, String> hashMap){
        this.callBack=callBack;
        this.hashMap=hashMap;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client=new OkHttpClient();
        Request.Builder requestBuilder=new Request.Builder();
        requestBuilder.url(strings[0]);
        if(hashMap!=null){
            FormBody.Builder bodyBuilder=new FormBody.Builder();
            Set<String> keySet=hashMap.keySet();
            for(String key:keySet){
                String value=hashMap.get(key);
                bodyBuilder.add(key, value);
            }
            FormBody body=bodyBuilder.build();
            requestBuilder.post(body);
        }
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBack.onResponse(s);
    }
}
