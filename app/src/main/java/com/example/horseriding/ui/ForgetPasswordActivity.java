package com.example.horseriding.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.horseriding.R;
import com.example.horseriding.modal.User;
import com.example.horseriding.dao.UserDao;
import com.example.horseriding.database.UserDatabase;

import java.util.List;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etChangePassword, etConfirmPassword;
    private TextView btnChangePassword;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_KEY = "prefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);

        initComponents();
    }

    private void initComponents() {
        etChangePassword = findViewById(R.id.et_change_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnChangePassword = findViewById(R.id.tv_change_password);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        btnChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_change_password) {
            String changePassword = etChangePassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            sharedPreferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
            String userId = sharedPreferences.getString("user_id", "");

            if (changePassword.isEmpty() || confirmPassword.isEmpty()) {
                showToast("Please enter both passwords");
            } else if (!changePassword.equals(confirmPassword)) {
                showToast("Passwords do not match");
            } else {
                List<User> userList = userDao.getAllData();
                if (!userList.isEmpty()) {
                    for (User user : userList) {
                        if (userId.equals(user.getUserID())) {
                            user.setUserPassword(confirmPassword);
                            userDao.resetPassword(userId, confirmPassword);
                            userDao.insertUser(new User(user.getUserFirstName(), user.getUserLastName(), userId, user.getUserPhoneNumber(),
                                    confirmPassword, user.getUserLocation()));

                            showToast("Your password has been successfully changed");

                            new Handler().postDelayed(() -> {
                                startActivity(new Intent(this, LoginPageActivity.class));
                                finish();
                            }, 1500);
                            break;
                        }
                    }
                }
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}