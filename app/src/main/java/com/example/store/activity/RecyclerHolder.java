package com.example.store.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;
import com.example.store.dao.RecyclerViewOnClickListener;
import com.example.store.model.Product;

public class RecyclerHolder extends RecyclerView.ViewHolder {
    TextView productName;
    TextView productPrice;
    TextView productManufacturer;
    TextView productDescription;
    CardView addCart;
    //TextView productProducer;
    //TextView productGender;
    //TextView productQuantity;
    //ImageView productImage;
    public RecyclerHolder(@NonNull View itemView){
        super(itemView);
        productName = itemView.findViewById(R.id.productName);
        productPrice = itemView.findViewById(R.id.productPrice);
        productManufacturer = itemView.findViewById(R.id.productManufacturer);
        productDescription = itemView.findViewById(R.id.productDescription);
        addCart = itemView.findViewById(R.id.addCartButton);
//        productQuantity = itemView.findViewById(R.id.quantity);
//        productImage = itemView.findViewById(R.id.productImage);
    }
    public void bindOnCliclListener(Product product, RecyclerViewOnClickListener<Product> listener){
        addCart.setOnClickListener(view -> listener.onRecyclerViewItemClick(product));
    }
}
