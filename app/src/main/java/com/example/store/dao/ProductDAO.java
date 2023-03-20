package com.example.store.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.store.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();
}
