package com.example.horseriding.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.horseriding.R;
import com.example.horseriding.dao.UserDao;
import com.example.horseriding.database.UserDatabase;
import com.example.horseriding.modal.User;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private String loggedInUserID;
    private List<User> userList;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private String password;
    private EditText etFirstName, etLastName, etUserID, etMobileNumber, etUserLocation;
    private TextView btnSaveChanges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        initComponents();
        loadUserData();
    }

    private void initComponents() {
        etFirstName = findViewById(R.id.et_user_first_name);
        etLastName = findViewById(R.id.et_user_last_name);
        etUserID = findViewById(R.id.et_user_id);
        etMobileNumber = findViewById(R.id.et_user_mobile_number);
        etUserLocation = findViewById(R.id.et_user_location);
        btnSaveChanges = findViewById(R.id.btn_save_changes);
        btnSaveChanges.setOnClickListener(this);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        loggedInUserID = sharedPreferences.getString("user_id", "");
        userList = userDao.getUserById(loggedInUserID);
    }

    private void loadUserData() {
        if (!userList.isEmpty()) {
            for (User user : userList) {
                if (user.getUserID().equals(loggedInUserID)) {
                    etFirstName.setText(user.getUserFirstName().toUpperCase());
                    etLastName.setText(user.getUserLastName().toUpperCase());
                    etUserID.setText(user.getUserID());
                    etMobileNumber.setText(user.getUserPhoneNumber());
                    password = user.getUserPassword();
                    etUserLocation.setText(user.getUserLocation().toUpperCase());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save_changes) {
            updateUserProfile();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    private void updateUserProfile() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String userID = etUserID.getText().toString();
        String userMobileNumber = etMobileNumber.getText().toString();
        String userLocation = etUserLocation.getText().toString();

        User updatedUser = new User(firstName, lastName, userMobileNumber, userLocation, password, userID);
        userDao.insertUser(updatedUser);
    }
}
