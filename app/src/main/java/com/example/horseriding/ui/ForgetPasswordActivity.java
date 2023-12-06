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

    private EditText etForgetPassword, etConfirmPassword;
    private TextView btnPasswordChange;
    private UserDatabase userDatabase;
    SharedPreferences sh;
    private UserDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);

        initComponent();

    }

    private void initComponent() {
        this.etForgetPassword = findViewById(R.id.et_change_password);
        this.etConfirmPassword = findViewById(R.id.et_confirm_password);
        this.btnPasswordChange = findViewById(R.id.tv_change_password);

        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();

        this.btnPasswordChange.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_change_password) {
            String changePassword = this.etForgetPassword.getText().toString();
            String confirmPassword = this.etConfirmPassword.getText().toString();
            sh = getSharedPreferences("prefs", MODE_PRIVATE);
            String userId = sh.getString("user_id", "");

            if (!changePassword.equals(confirmPassword)) {
                Toast.makeText(ForgetPasswordActivity.this, "check your password", Toast.LENGTH_SHORT).show();
            } else {
                List<User> userList = dao.getAllData();
                if (userList.size() != 0) {
                    for (int i = 0; i < userList.size(); i++) {
                        if (userId.equals(userList.get(i).getUserID())) {
                            userList.get(i).setUserPassword(etConfirmPassword.getText().toString());
                            dao.resetPassword(userId, etConfirmPassword.getText().toString());
                            dao.insertUser(new User(userList.get(i).getUserFirstName(),userList.get(i).getUserLastName(),userId,userList.get(i).getUserPhoneNumber(),
                                    etConfirmPassword.getText().toString(),userList.get(i).getUserLocation()));
                            Toast.makeText(this, "Your Password is successfully changed", Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(() -> {

                                startActivity(new Intent(this, LoginPage.class));
                                finish();
                            }, 1500);


                        }
                    }
                }
            }

        }

    }
}
