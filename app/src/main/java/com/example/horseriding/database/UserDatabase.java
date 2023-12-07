package com.example.horseriding.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.horseriding.dao.UserDao;
import com.example.horseriding.modal.User;

@Database(entities = {User.class}, version = 7, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static volatile UserDatabase INSTANCE;
    public abstract UserDao getDao();

    public static UserDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }
    private static UserDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "UserDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
