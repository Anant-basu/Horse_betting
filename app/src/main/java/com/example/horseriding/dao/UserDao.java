package com.example.horseriding.dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.horseriding.modal.User;

import java.util.List;

@androidx.room.Dao
public interface UserDao {
    @Insert
    void insertUser(User user);
    @Query("SELECT * FROM detail_table WHERE user_ID = :userId AND user_password = :password")
    User login(String userId, String password);
    @Query("UPDATE detail_table SET user_password = :newPassword WHERE user_ID = :userID")
    void resetPassword(String userID, String newPassword);
    @Query("SELECT * FROM detail_table")
    List<User> getAllData();
    @Query("SELECT * FROM detail_table WHERE user_ID = :userId")
    List<User> getUserById(String userId);

}
