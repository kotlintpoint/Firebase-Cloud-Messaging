package com.example.android1to3.cartdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android1to3.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements ProductAdapter.ProductClickListener {
    RecyclerView recyclerView;
    ArrayList<Product> products;
    TextView tvTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvTotalAmount=findViewById(R.id.tvTotalAmount);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        products=new ArrayList<>();
        products.add(new Product("Test",100));
        products.add(new Product("Test1",10));
        ProductAdapter adapter=new ProductAdapter(products, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onQtyChange() {
        int total=0;
        for(Product product:products){
            total+=(product.getQty()*product.getUnitPrice());
        }
        tvTotalAmount.setText(""+total);
    }
}
