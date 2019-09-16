package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListCitiesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvcities;
    private String states[];
    private String gujaratCities[] = {"G1City", "G2City"};
    private String rajasthanCities[] = {"R1City", "R2City"};
    private String mahaCities[] = {"MH1City", "MH2City"};
    private String madhyaCities[] = {"MP1City", "MP2City"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);

        lvcities=findViewById(R.id.lvcities);
        lvcities.setOnItemClickListener(this);
        Intent intent = getIntent();
        String state = intent.getStringExtra("krunal");
        setCities(state);

    }

    private void setCities(String state) {
        String cities[] = null;
        switch (state) {
            case "Gujarat":
                cities = gujaratCities;
                break;
            case "Rajasthan":
                cities = rajasthanCities;
                break;
            case "Madhya Pradesh":
                cities = madhyaCities;
                break;
            case "Maharastra":
                cities = mahaCities;
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        lvcities.setAdapter(adapter);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }
}
