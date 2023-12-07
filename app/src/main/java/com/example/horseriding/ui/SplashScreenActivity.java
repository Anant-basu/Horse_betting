package com.example.horseriding.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.horseriding.R;
import com.example.horseriding.dao.UserDao;
import com.example.horseriding.database.UserDatabase;
import com.example.horseriding.modal.User;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    private UserDatabase userDatabase;
    private List<User> userList;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();
        userList = new ArrayList<>();
        userList = dao.getAllData();
        String userId = sharedPreferences.getString("user_id", "");
        if (userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userId.equals(userList.get(i).getUserID())) {

                    new Handler().postDelayed(() -> {
                        startActivity(new Intent(SplashScreenActivity.this, HomePageActivity.class));

                        finish();
                    }, 2000);

                } else if (userList.get(i).getUserID().equals("")) {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginPageActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        } else {
            new Handler().postDelayed(() -> {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginPageActivity.class);
                    startActivity(intent);

                    finish();

            }, 2000);


        }
    }
}