package com.example.android1to3.filestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android1to3.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageActivity extends AppCompatActivity {
    private static final String FILE_NAME = "My Internal file";
    private static final String MY_DIR = "My Dir";
    private static final String MY_EXT_FILE = "My External file";
    private static final int REQ_WRITE_EXT_STORAGE = 1;
    private EditText etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        etMessage=findViewById(R.id.etMessage);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_WRITE_EXT_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQ_WRITE_EXT_STORAGE){
            if(grantResults.length>0 &&
                grantResults[0]==PackageManager.PERMISSION_GRANTED){
                // granted
            }else{
                // deny
                finish();
            }
        }
    }

    public void writeInternal(View view) {
        FileOutputStream fos;
        String msg=etMessage.getText().toString();
        try {
            fos=openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(msg.getBytes());
            fos.close();
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readInternal(View view) {
        FileInputStream fis;
        try {
            fis=openFileInput(FILE_NAME);
            byte b[]=new byte[fis.available()];
            fis.read(b);
            fis.close();
            String msg=new String(b);
            etMessage.setText(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExternal(View view) {
        File file=Environment.getExternalStorageDirectory();
        file=new File(file, MY_DIR);
        if(!file.exists()){
            if(file.mkdir()){
                Toast.makeText(this, "Dir Created", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Dir Not Created", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        file=new File(file, MY_EXT_FILE);
        try {
            String msg=etMessage.getText().toString();
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(msg.getBytes());
            fos.close();
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExternal(View view) {

    }
}
