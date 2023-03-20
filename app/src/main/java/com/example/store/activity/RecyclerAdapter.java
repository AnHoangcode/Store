package com.example.store.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;
import com.example.store.model.Product;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
    List<Product> listProduct;

    public RecyclerAdapter(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        int index = holder.getAdapterPosition();
        holder.productName.setText(listProduct.get(index).getName());
        holder.productPrice.setText(listProduct.get(index).getPrice().toString()+"$");
        holder.productManufacturer.setText(listProduct.get(index).getManufacturer());
        holder.productName.setText(listProduct.get(index).getName());
        //holder.productQuantity.setText(listProduct.get(index).getQuantity().toString());
        holder.productDescription.setText(listProduct.get(index).getDescription());
        //holder.productImage.setImageBitmap(listProduct.get(index).getDescription());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }
}
