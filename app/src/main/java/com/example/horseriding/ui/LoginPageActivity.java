package com.example.horseriding.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PREFS_NAME = "prefs";
    private static final String USER_ID_KEY = "user_id";

    private TextView tvSignUp, btnLogin, tvForgetPass;
    private EditText userID, userPassword;
    private String userId, password;
    private UserDatabase userDatabase;
    private UserDao dao;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        initializeComponents();
    }
    private void initializeComponents() {
        tvSignUp = findViewById(R.id.tv_sign_up);
        btnLogin = findViewById(R.id.btn_login);
        userID = findViewById(R.id.et_mobile_number);
        userPassword = findViewById(R.id.et_password_login);
        tvForgetPass = findViewById(R.id.tv_forget_pass);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();
        tvSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvForgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_sign_up) {
            navigateToSignUpPage();
        } else if (v.getId() == R.id.btn_login) {
            handleLoginButtonClick();
        } else if (v.getId() == R.id.tv_forget_pass) {
            navigateToForgetPasswordPage();
        }
    }
    private void navigateToSignUpPage() {
        Intent intent = new Intent(LoginPageActivity.this, SignUPageActivity.class);
        startActivity(intent);
    }
    private void handleLoginButtonClick() {
        userId = userID.getText().toString();
        password = userPassword.getText().toString();
        User user = dao.login(userId, password);

        if (checkFields()) {
            if (isValidUser(user)) {
                startKYCPageActivity();
            } else {
                Toast.makeText(this, "Wrong credentials.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter valid credentials", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isValidUser(User user) {
        return user != null && Objects.equals(user.getUserID(), userId) && Objects.equals(user.getUserPassword(), password);
    }
    private void startKYCPageActivity() {
        Intent intent = new Intent(LoginPageActivity.this, KYCPageActivity.class);
        myEdit.putString(USER_ID_KEY, userId);
        myEdit.apply();
        startActivity(intent);
    }
    private void navigateToForgetPasswordPage() {
        Intent intent = new Intent(LoginPageActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
    private boolean checkFields() {
        if (userID.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}