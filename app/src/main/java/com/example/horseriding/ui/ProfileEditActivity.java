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
import com.example.horseriding.modal.User;
import com.example.horseriding.dao.UserDao;
import com.example.horseriding.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfileEditActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sh;
    String logInUserID;
    private List<User> userList;
    private UserDatabase userDatabase;
    private TextView btnSaveChanges;
    private UserDao dao;
    String password;
    private EditText etFirstName, etLastName, etUserID, etMobileNumber, etUserLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        initComponent();
    }

    private void initComponent() {
        this.etFirstName=findViewById(R.id.et_user_first_name);
        this.etLastName=findViewById(R.id.et_user_last_name);
        this.etUserID=findViewById(R.id.et_user_id);
        this.etMobileNumber=findViewById(R.id.et_user_mobile_number);
        this.etUserLocation=findViewById(R.id.et_user_location);
        this.btnSaveChanges=findViewById(R.id.btn_save_changes);

        this.btnSaveChanges.setOnClickListener(this::onClick);

        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();

        sh = getSharedPreferences("prefs", MODE_PRIVATE);
        logInUserID = sh.getString("user_id", "");
        userList = new ArrayList<>();
        userList = dao.getUserById(logInUserID);
        userList = dao.getUserById(logInUserID);
        if (userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserID().equals(logInUserID)) {
                    etFirstName.setText(userList.get(i).getUserFirstName().toUpperCase());
                    etLastName.setText(userList.get(i).getUserLastName().toUpperCase());
                    etUserID.setText(userList.get(i).getUserID());
                    etMobileNumber.setText(userList.get(i).getUserPhoneNumber());
                    password=userList.get(i).getUserPassword();
                    etUserLocation.setText(userList.get(i).getUserLocation().toUpperCase());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_save_changes){
            String firstName=etFirstName.getText().toString();
            String lastName=etLastName.getText().toString();
            String userID=etUserID.getText().toString();
            String userMobileNumber=etMobileNumber.getText().toString();
            String userLocation=etUserLocation.getText().toString();
            User user=new User(firstName,lastName,userMobileNumber,userLocation,password,userID);
            dao.insertUser(user);
            startActivity(new Intent(this, ProfileActivity.class));

        }
    }

}
