package com.example.horseriding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUPPage extends AppCompatActivity implements View.OnClickListener {

    private UserDatabase userDatabase;
    private UserDao dao;
    private TextView btnContinue;
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

        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();
        this.btnContinue.setOnClickListener(this::onClick);
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
            if (checkAllFields()) {
                User user = new User(firstName, lastName,userID, mobileNumber, password, location);
                dao.insertUser(user);
                Toast.makeText(this, "User is Successfully Registered", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, LoginPage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user_value", user);
                startActivity(intent);
            }
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
}
