package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SeekBarProgressChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_annotation)
public class AnnotationActivity extends AppCompatActivity {
    @ViewById EditText etMessage;
    @ViewById TextInputLayout inputLayout;
    @ViewById
    TextInputEditText etAddress;
    @AfterViews
    void onCreate(){
        etMessage.setText("Hello Android");
    }

    @Click(R.id.btnHello)
    void sayHello(){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        String address=etAddress.getText().toString();
        if(address.equals("")){
            inputLayout.setError("Required");
        }
    }

    @SeekBarProgressChange(R.id.seekBar)
    void onSeekBarChange(int progress){
        etMessage.setText("Progress : "+progress);
    }
}
