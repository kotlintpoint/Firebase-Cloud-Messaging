package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tvState;
    private Spinner spStates, spCities;
    private String states[];
    private String gujaratCities[]={"G1City","G2City"};
    private String rajasthanCities[]={"R1City", "R2City"};
    private String mahaCities[]={"MH1City","MH2City"};
    private String madhyaCities[]={"MP1City","MP2City"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvState=findViewById(R.id.tvState);
        spStates=findViewById(R.id.spStates);
        spCities=findViewById(R.id.spCities);
        states=getResources().getStringArray(R.array.states);
        spStates.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String state=states[position];
        tvState.setText("Selected State : "+state);
        setCities(state);
    }

    private void setCities(String state) {
        String cities[]=null;
        switch(state){
            case "Gujarat":
                cities=gujaratCities;
                break;
            case "Rajasthan":
                cities=rajasthanCities;
                break;
            case "Madhya Pradesh":
                cities=madhyaCities;
                break;
            case "Maharastra":
                cities=mahaCities;
                break;
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cities);
        spCities.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
