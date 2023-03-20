package com.example.store.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.store.dao.ProductDAO;
import com.example.store.dao.UserDAO;
import com.example.store.model.Product;
import com.example.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {User.class, Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"WATCHIES.db")
                    .setJournalMode(JournalMode.TRUNCATE)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
            UserDAO userDAO = INSTANCE.userDAO();
            ProductDAO productDAO = INSTANCE.productDAO();
            userDAO.deleteAll();
            productDAO.deleteAll();
            List<User> userList = userDAO.getAll();
            List<Product> productList = productDAO.getAll();
            if (userList.size() <= 0){
                defaultListUser().forEach(userDAO::insert);
            }
            if (productList.size() <= 0){
                defaultListProduct().forEach(productDAO::insert);
            }
        }
        return INSTANCE;
    }

    private static List<User> defaultListUser(){
        List<User> list = new ArrayList<>();
        list.add(new User(1, "admin", "0000","Admin","male","admin@gmail.com"));
        list.add(new User(2, "minhan", "0000","Minh An","male","minhan@gmail.com"));
        list.add(new User(3, "hoanghuy", "0000","Huy","male","hoanghuy@gmail.com"));
        return list;
    }
    private static List<Product> defaultListProduct(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Colosseum Guandao", 200, 10, "Male", "Curnon", "42mm"));
        list.add(new Product(2, "MT20", 200, 10, "Male", "Casio", "38mm"));
        return list;
    }
    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();
}
