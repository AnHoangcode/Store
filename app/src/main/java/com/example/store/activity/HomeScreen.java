package com.example.store.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.store.R;
import com.example.store.dao.CartDAO;
import com.example.store.dao.ProductDAO;
import com.example.store.dao.UserDAO;
import com.example.store.db.AppDatabase;
import com.example.store.model.Cart;
import com.example.store.model.Product;
import com.example.store.model.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Product> listProduct;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter rAdapter;
    TextView userName;
    TextView cartCount;
    List<Cart> cartList;
    User currentUser;
    CardView btn_yourcart;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.product_recycler_horizontal);
        userName = findViewById(R.id.usernameText);
        cartCount = findViewById(R.id.cartItemCountText);
        btn_yourcart = findViewById(R.id.yourcartButton);

        btn_yourcart.setOnClickListener(v -> {
            startActivity(new Intent(HomeScreen.this,YourCart.class));
        });

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UserDAO userDAO = db.userDAO();
        CartDAO cartDAO = db.cartDAO();

        SharedPreferences userPre = getApplicationContext().getSharedPreferences("userPre", Context.MODE_PRIVATE);
        currentUser = userDAO.getUser(userPre.getInt("USER_ID",1));
        userName.setText(currentUser.getName());

        ProductDAO productDAO = db.productDAO();
        listProduct = productDAO.getAll();

        cartList = cartDAO.getListCart(currentUser.getId());


        linearLayoutManager = new LinearLayoutManager(HomeScreen.this, LinearLayoutManager.HORIZONTAL, false);
        rAdapter = new RecyclerAdapter(listProduct, product -> {
            AtomicInteger cartItemCount = new AtomicInteger();
            Cart existed = cartDAO.getCart(currentUser.getId(), product.getId());
            if (existed != null){
                existed.setQuantity(existed.getQuantity() + 1);
                cartDAO.update(existed);
            }else{
                Cart item = new Cart(currentUser.getId(), product.getId(), 1);
                cartDAO.insert(item);
            }
            cartList.forEach(item -> {
                cartItemCount.set(cartItemCount.get() + item.getQuantity());
            });
            cartCount.setText(String.valueOf(cartItemCount.get()+1));
            reloadData();
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rAdapter);
        reloadData();
    }
    public void reloadData(){
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        CartDAO cartDAO = db.cartDAO();
        cartList = cartDAO.getListCart(currentUser.getId());
    }
}