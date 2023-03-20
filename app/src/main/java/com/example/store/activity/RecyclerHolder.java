package com.example.store.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;

public class RecyclerHolder extends RecyclerView.ViewHolder {
    TextView productName;
    TextView productPrice;
    TextView productManufacturer;
    TextView productDescription;
    TextView productProducer;
    TextView productGender;
    //TextView productQuantity;
    ImageView productImage;
    public RecyclerHolder(@NonNull View itemView){
        super(itemView);
        productName = itemView.findViewById(R.id.productName);
        productPrice = itemView.findViewById(R.id.productPrice);
        productManufacturer = itemView.findViewById(R.id.productManufacturer);
        productDescription = itemView.findViewById(R.id.productDescription);
//        productImage = itemView.findViewById(R.id.productImage);
    }
}
