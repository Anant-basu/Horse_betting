package com.example.horseriding;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "detail_table")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_firstName")
    private String userFirstName;
    @ColumnInfo(name = "user_lastName")
    private String userLastName;
    @ColumnInfo(name = "user_phoneNumber")
    private String userPhoneNumber;
    @ColumnInfo(name = "user_location")
    private String userLocation;
    @ColumnInfo(name = "user_password")
    private String userPassword;
    @ColumnInfo(name = "user_ID")
    private String userID;

    public User(String userFirstName, String userLastName, String userID, String userPhoneNumber, String userPassword, String userLocation) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userID = userID;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userLocation = userLocation;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
