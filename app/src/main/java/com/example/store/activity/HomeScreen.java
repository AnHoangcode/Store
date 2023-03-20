package com.example.store.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.store.R;
import com.example.store.dao.ProductDAO;
import com.example.store.dao.UserDAO;
import com.example.store.db.AppDatabase;
import com.example.store.model.Product;
import com.example.store.model.User;

import java.util.List;

public class HomeScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Product> listProduct;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter rAdapter;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.product_recycler_horizontal);
        userName = findViewById(R.id.usernameText);
        SharedPreferences userPre = getApplicationContext().getSharedPreferences("userPre", Context.MODE_PRIVATE);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UserDAO userDAO = db.userDAO();
        User currentUser = userDAO.getUser(userPre.getInt("USER_ID",1));
        userName.setText(currentUser.getName());

        ProductDAO productDAO = db.productDAO();
        listProduct = productDAO.getAll();

        linearLayoutManager = new LinearLayoutManager(HomeScreen.this, LinearLayoutManager.HORIZONTAL, false);
        rAdapter = new RecyclerAdapter(listProduct);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rAdapter);
    }
}