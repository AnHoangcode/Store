package com.example.store.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.store.dao.UserDAO;
import com.example.store.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
}
