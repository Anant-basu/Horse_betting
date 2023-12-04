package com.example.horseriding;

import androidx.room.Insert;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insertUser(User user);

}
