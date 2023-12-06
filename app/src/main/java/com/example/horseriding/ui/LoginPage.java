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

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSignUP, btnLogin, tvForgetPass;
    private UserDatabase userDatabase;
    private UserDao dao;
    private EditText userID, userPassword,etChangePassword,etConfirmPassword;
    String userId, password,changePassword,confirmPassword;
    BottomSheetDialog dialog;
    User userData;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        initComponents();
    }
    private void initComponents() {
        this.tvSignUP = findViewById(R.id.tv_sign_up);
        this.btnLogin = findViewById(R.id.btn_login);
        this.userID = findViewById(R.id.et_mobile_number);
        this.userPassword = findViewById(R.id.et_password_login);
        this.tvForgetPass = findViewById(R.id.tv_forget_pass);

        sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();
        dialog = new BottomSheetDialog(this);

        this.tvSignUP.setOnClickListener(this::onClick);
        this.btnLogin.setOnClickListener(this::onClick);
        this.tvForgetPass.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_sign_up) {
            Intent intent = new Intent(LoginPage.this, SignUPPage.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_login) {
            userId = userID.getText().toString();
            password = userPassword.getText().toString();
            User user = dao.login(userId, password);
            if (checkFields()) {
                if (user != null && Objects.equals(user.getUserID(), userId) && Objects.equals(user.getUserPassword(), password)) {
                    Intent intent = new Intent(LoginPage.this, KYCPage.class);
                    myEdit.putString("user_id",userId);
                    myEdit.commit();
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "wrong credentials.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter valid credential", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.tv_forget_pass) {
            Intent intent=new Intent(LoginPage.this, ForgetPasswordActivity.class);
           /* myEdit.putString("user_id",userId);
            myEdit.commit();*/
            startActivity(intent);
        }
    }

    private boolean checkFields() {
        if (userID.getText().toString().length() == 0 || userPassword.getText().toString().length() == 0) {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
