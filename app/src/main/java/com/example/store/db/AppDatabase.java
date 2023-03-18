package com.example.store.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.store.dao.UserDAO;
import com.example.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"WATCHIES.db")
                    .setJournalMode(JournalMode.TRUNCATE).allowMainThreadQueries().build();
            UserDAO userDAO = INSTANCE.userDAO();
            List<User> userList = userDAO.getAll();
            if (userList.size() <= 0){
                defaulListUser().forEach(userDAO::insert);
            }
        }
        return INSTANCE;
    }

    private static List<User> defaulListUser(){
        List<User> list = new ArrayList<>();
        list.add(new User(1, "admin", "0000","Admin","male","admin@gmail.com"));
        list.add(new User(2, "minhan", "0000","Minh An","male","minhan@gmail.com"));
        list.add(new User(3, "hoanghuy", "0000","Hoang Huy","male","hoanghuy@gmail.com"));
        return list;
    }
    public abstract UserDAO userDAO();
}
