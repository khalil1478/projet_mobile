package com.example.mobile_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//import com.example.mobile_project.dao.CommentaireDao;
import com.example.mobile_project.dao.EventDao;
import com.example.mobile_project.dao.UserDao;
//import com.example.mobile_project.entities.Commentaire;
import com.example.mobile_project.entities.Event;
import com.example.mobile_project.entities.User;

@Database(entities = {User.class, Event.class/*, Commentaire.class*/}, version = 2, exportSchema = false)
public  abstract  class MyDataBase extends RoomDatabase {

    private static MyDataBase instance;
    public abstract UserDao userDao();
    public abstract EventDao EventDao();
   // public abstract CommentaireDao commentaireDao();


    public static MyDataBase getAppDatabase(Context context) {



        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,"db_project")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    //.fallbackToDestructiveMigration()


                    .build();

        }
        return instance;
    }
}