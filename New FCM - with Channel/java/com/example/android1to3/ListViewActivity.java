package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String states[];
    private String gujaratCities[]={"G1City","G2City"};
    private String rajasthanCities[]={"R1City", "R2City"};
    private String mahaCities[]={"MH1City","MH2City"};
    private String madhyaCities[]={"MP1City","MP2City"};
    private ListView lvStates, lvCities;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private boolean isChecked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lvStates=findViewById(R.id.lvStates);
        lvCities=findViewById(R.id.lvCities);
        states=getResources().getStringArray(R.array.states);
        lvStates.setOnItemClickListener(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(ListViewActivity.this, "Initialized", Toast.LENGTH_SHORT).show();
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String state=states[position];
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
        lvCities.setAdapter(adapter);

        Intent intent=new Intent(this,ListCitiesActivity.class);
        intent.putExtra("krunal", state);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if(isChecked) {
            super.onBackPressed();
        }else{

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            isChecked=true;
        }
    }
}
