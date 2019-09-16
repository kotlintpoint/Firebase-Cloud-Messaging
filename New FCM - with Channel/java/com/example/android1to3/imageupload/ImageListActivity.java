package com.example.android1to3.imageupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android1to3.R;
import com.example.android1to3.retrofit.RetrofitClient;
import com.example.android1to3.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        RetrofitService service= RetrofitClient.getService();
        Call<ImageRoot> call=service.getImages("5");
        call.enqueue(new Callback<ImageRoot>() {
            @Override
            public void onResponse(Call<ImageRoot> call, Response<ImageRoot> response) {
                ImageRoot root = response.body();
                List<ImageRoot.Images> images = root.getImages();
                setAdapter(images);
            }

            @Override
            public void onFailure(Call<ImageRoot> call, Throwable t) {
                Toast.makeText(ImageListActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<ImageRoot.Images> images) {
        ImageAdapter adapter=new ImageAdapter(images);
        recyclerView.setAdapter(adapter);
    }


}
