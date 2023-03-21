package com.example.store.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.store.model.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert
    void insert (Order order);

    @Update
    void update (Order order);

    @Delete
    void delete (Order order);

    @Query("DELETE FROM Order")
    void deleteAll();

    @Query("SELECT * from Order where id=(:id) and userId=(:userId)")
    List<Order> getListOrder(int id, int userId);
}
