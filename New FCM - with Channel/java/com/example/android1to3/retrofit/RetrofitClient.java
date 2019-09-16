package com.example.android1to3.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static RetrofitService getService(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/flipkart/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service=retrofit.create(RetrofitService.class);
        return service;
    }
}
