package com.example.store.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.store.R;
import com.example.store.dao.CartDAO;
import com.example.store.dao.ProductDAO;
import com.example.store.dao.UserDAO;
import com.example.store.db.AppDatabase;
import com.example.store.model.Cart;
import com.example.store.model.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class YourCart extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TextView userName, total;
    RecyclerAdapterVertical rAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);
        userName = findViewById(R.id.usernameText);
        recyclerView = findViewById(R.id.product_recycler);
        total = findViewById(R.id.total);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UserDAO userDAO = db.userDAO();
        CartDAO cartDAO = db.cartDAO();
        ProductDAO productDAO = db.productDAO();

        linearLayoutManager = new LinearLayoutManager(YourCart.this, LinearLayoutManager.VERTICAL, false);
        SharedPreferences userPre = getApplicationContext().getSharedPreferences("userPre", Context.MODE_PRIVATE);
        User currentUser = userDAO.getUser(userPre.getInt("USER_ID",2));
        userName.setText(currentUser.getName());

        List<Cart> cartList = cartDAO.getListCart(currentUser.getId());
        AtomicInteger sum = new AtomicInteger();
        cartList.forEach((cart) -> {
            sum.set(sum.get() + productDAO.getProduct(cart.getProductId()).getPrice() * cart.getQuantity());
        });
        total.setText("Total: " + sum.get() + "$");
        rAdapter = new RecyclerAdapterVertical(cartList, productDAO);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rAdapter);
    }
}