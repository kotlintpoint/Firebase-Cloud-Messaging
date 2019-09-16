package com.example.android1to3.preference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android1to3.R;
import com.example.android1to3.filestorage.FileStorageActivity;

public class MyPreferenceActivity extends AppCompatActivity {
    private static final String MY_PREF_FILE = "My Pref File";
    EditText etMessage;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preference);
        etMessage=findViewById(R.id.etMessage);


        sharedPreferences=getSharedPreferences(MY_PREF_FILE, MODE_PRIVATE);
        readPreference(null);
    }

    public void writePreference(View view) {
        String msg=etMessage.getText().toString();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("msg", msg);
        editor.putBoolean("flag", true);
        if(editor.commit()){
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void readPreference(View view) {
        String msg=sharedPreferences.getString("msg",null);
        boolean isLogin=sharedPreferences.getBoolean("flag", false);
        if(msg!=null){
            etMessage.setText(msg);
        }
       /* if(isLogin){
            startActivity(new Intent(this, FileStorageActivity.class));
            finish();
        }*/
    }

    public void settings(View view) {
        startActivity(new Intent(this,SettingsActivity.class));
    }

    public void readSettings(View view) {
        SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
        String signature=sharedPref.getString("signature", "Not Set");
        etMessage.setText(signature);
    }
}
