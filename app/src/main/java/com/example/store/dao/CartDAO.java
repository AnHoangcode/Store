package com.example.store.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.store.model.Cart;

import java.util.List;

@Dao
public interface CartDAO {
    @Insert
    void insert(Cart cart);

    @Update
    void update(Cart cart);

    @Delete
    void delete(Cart cart);

    @Query("DELETE FROM Cart")
    void deleteAll();

    @Query("SELECT * from Cart where userId=(:userId) and productId=(:productId)")
    Cart getCart(int userId, int productId);

    @Query("SELECT * from Cart where userId=(:userId)")
    List<Cart> getListCart(int userId);

}
