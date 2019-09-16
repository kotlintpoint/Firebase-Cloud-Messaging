package com.example.android1to3.imageupload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android1to3.R;
import com.example.android1to3.retrofit.RetrofitClient;
import com.example.android1to3.retrofit.RetrofitService;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUploadActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1;
    private Button btnSelectImage, btnUpload;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        btnSelectImage=findViewById(R.id.btnSelectImage);
        btnUpload=findViewById(R.id.btnUpload);
        imageView=findViewById(R.id.imageView);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                Log.i("IMAGE", encodedImage);
                uploadImage(encodedImage);
            }
        });
    }

    private void uploadImage(String encodedImage) {
        RetrofitService service= RetrofitClient.getService();
        Call<String> call=service.uploadImage(encodedImage, "test");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(ImageUploadActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                Log.i("Q", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ImageUploadActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CAMERA && resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            bitmap= (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
