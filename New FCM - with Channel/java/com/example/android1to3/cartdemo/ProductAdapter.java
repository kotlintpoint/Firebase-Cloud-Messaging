package com.example.android1to3.cartdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android1to3.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final ArrayList<Product> products;
    public interface ProductClickListener{
        void onQtyChange();
    }
    ProductClickListener listener;

    public ProductAdapter(ArrayList<Product> products, ProductClickListener listener) {
        this.products=products;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row_item, parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        final Product product=products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvQty.setText(""+product.getQty());
        holder.tvTotal.setText(""+(product.getQty()*product.getUnitPrice()));
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty=product.getQty()+1;
                product.setQty(qty);
                products.set(position, product);
                notifyDataSetChanged();
                listener.onQtyChange();
            }
        });
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getQty()>0) {
                    int qty = product.getQty() - 1;
                    product.setQty(qty);
                    products.set(position, product);
                    notifyDataSetChanged();
                    listener.onQtyChange();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQty, tvTotal;
        ImageView imgAdd, imgRemove;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvQty=itemView.findViewById(R.id.tvQty);
            tvTotal=itemView.findViewById(R.id.tvTotal);
            imgAdd=itemView.findViewById(R.id.imgAdd);
            imgRemove=itemView.findViewById(R.id.imgRemove);
        }
    }
}
