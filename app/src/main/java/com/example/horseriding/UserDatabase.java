package com.example.horseriding;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 7, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getDao();
    public static UserDatabase INSTANCE;
    static synchronized UserDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,"UserDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
