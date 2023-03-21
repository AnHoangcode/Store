package com.example.store.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;
import com.example.store.dao.CartDAO;
import com.example.store.dao.ProductDAO;
import com.example.store.dao.UserDAO;
import com.example.store.db.AppDatabase;
import com.example.store.model.Cart;
import com.example.store.model.Product;

import java.util.List;

public class RecyclerAdapterVertical extends RecyclerView.Adapter<RecyclerAdapterVertical.RecyclerHolderVertical> {
    List<Cart> cartList;
    ProductDAO productDAO;

    public RecyclerAdapterVertical(List<Cart> listCart, ProductDAO productDAO) {
        this.cartList = listCart;
        this.productDAO = productDAO;
    }

    @NonNull
    @Override
    public RecyclerHolderVertical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_tab, parent, false);
        return new RecyclerHolderVertical(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolderVertical holder, int position) {
        int index = holder.getAdapterPosition();
        Product item = productDAO.getProduct(cartList.get(index).getProductId());
        holder.productName.setText(item.getName());
        holder.productQuantity.setText("Quantity: " + cartList.get(index).getQuantity().toString());
        holder.productTotalPrice.setText(String.valueOf(cartList.get(index).getQuantity()*item.getPrice()) + "$");
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class RecyclerHolderVertical extends RecyclerHolder{
//        image
        TextView productName;
        TextView productTotalPrice;
        TextView productQuantity;
        public RecyclerHolderVertical(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productTotalPrice = itemView.findViewById(R.id.totalPrice);
            productQuantity = itemView.findViewById(R.id.quantity);
        }
    }
}
