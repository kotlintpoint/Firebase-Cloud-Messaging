package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleAdapterActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String,String>> data;
    private final String COUNTRY="country";
    private final String CAPITAL="capital";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        listView=findViewById(R.id.listView);
        prepareData();
        String[] from={COUNTRY,CAPITAL};
        //int[] to={android.R.id.text1,android.R.id.text2};
        /*SimpleAdapter adapter=new SimpleAdapter(this,data,
                android.R.layout.simple_list_item_2, from, to);*/

        int[] to={R.id.text1,R.id.text2};
        SimpleAdapter adapter=new SimpleAdapter(this,data,
                R.layout.country_row_item, from, to);

        listView.setAdapter(adapter);
    }

    private void prepareData() {
        data=new ArrayList<>();
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put(COUNTRY,"Country1");
        hashMap.put(CAPITAL,"Capital1");
        data.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put(COUNTRY,"Country2");
        hashMap.put(CAPITAL,"Capital2");
        data.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put(COUNTRY,"Country3");
        hashMap.put(CAPITAL,"Capital3");
        data.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put(COUNTRY,"Country4");
        hashMap.put(CAPITAL,"Capital4");
        data.add(hashMap);
    }
}
