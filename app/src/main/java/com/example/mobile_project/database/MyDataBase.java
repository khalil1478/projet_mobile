package com.example.mobile_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobile_project.dao.UserDao;
import com.example.mobile_project.entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public  abstract  class MyDataBase extends RoomDatabase {

    private static MyDataBase instance;
    public abstract UserDao userDao();
    public static MyDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,"db_project")
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}