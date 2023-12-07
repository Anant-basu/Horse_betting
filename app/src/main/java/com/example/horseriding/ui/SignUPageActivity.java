package com.example.horseriding.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import java.util.Objects;

public class SignUPageActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDatabase userDatabase;
    private UserDao dao;
    private TextView btnContinue, tvLogin;
    private EditText etFirstName, etLastName, etMobileNumber, etPassword, etLocation, etUserID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        initComponent();

    }

    private void initComponent() {
        this.btnContinue = findViewById(R.id.btn_continue);
        this.etFirstName = findViewById(R.id.et_first_name);
        this.etLastName = findViewById(R.id.et_last_name);
        this.etMobileNumber = findViewById(R.id.et_number);
        this.etPassword = findViewById(R.id.et_password);
        this.etLocation = findViewById(R.id.et_location);
        this.etUserID = findViewById(R.id.et_signup_id);
        this.tvLogin = findViewById(R.id.tv_login);

        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();

        this.tvLogin.setOnClickListener(this);
        this.btnContinue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_continue) {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String mobileNumber = etMobileNumber.getText().toString();
            String password = etPassword.getText().toString();
            String location = etLocation.getText().toString();
            String userID = etUserID.getText().toString();
            if (checkAllFields() && isUserIDPrevious(userID)) {
                User user = new User(firstName, lastName, userID, mobileNumber, password, location);
                dao.insertUser(user);
                Toast.makeText(this, "User is Successfully Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginPageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user_value", user);
                startActivity(intent);
            }
        } else if (v.getId() == R.id.tv_login) {
            startActivity(new Intent(this, LoginPageActivity.class));
            finish();
        }
    }

    private boolean checkAllFields() {
        if (etFirstName.getText().toString().length() == 0) {
            etFirstName.setError("This field is required");
            return false;
        } else if (etLastName.getText().toString().length() == 0) {
            etLastName.setError("This field is required");
            return false;
        } else if (etMobileNumber.getText().toString().length() != 10) {
            etMobileNumber.setError("Invalid Phone Number");
            return false;
        } else if (etUserID.getText().toString().length() == 0 && Patterns.EMAIL_ADDRESS.matcher(etUserID.getText().toString()).matches()) {
            etUserID.setError("Invalid Email");
            return false;
        } else if (etPassword.getText().toString().length() < 6) {
            etPassword.setError("Password is too short");
            return false;
        } else if (etLocation.getText().toString().length() == 0) {
            etLocation.setError("Password is too short");
            return false;
        }
        return true;
    }

    private boolean isUserIDPrevious(String userID){

        List<User> userList=dao.getAllData();
        for (int i=0;i<userList.size();i++){
            if (Objects.equals(userList.get(i).getUserID(), userID)){
                Toast.makeText(this, "This user id is already exist.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
