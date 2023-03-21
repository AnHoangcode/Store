package com.example.store.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "userId")
    private String userId;

    @ColumnInfo(name = "listCart")
    private ArrayList<Cart> listCart;

    @ColumnInfo(name = "status")
    private String status;

    public Order(Integer id, String userId, ArrayList<Cart> listCart, String status) {
        this.id = id;
        this.userId = userId;
        this.listCart = listCart;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
