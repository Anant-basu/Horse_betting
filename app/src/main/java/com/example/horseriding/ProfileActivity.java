package com.example.horseriding;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    private UserDatabase userDatabase;
    private UserDao dao;
    private List<User> userList;
    SharedPreferences sh;
    private TextView tvFirstName, tvLastName, tvUserID, tvUserMobileNumber, tvUserLocation;
    private ImageView ivSelectImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        userDatabase = UserDatabase.getInstance(this);
        dao = userDatabase.getDao();
        initComponent();

    }
    private void initComponent() {
        this.tvFirstName=findViewById(R.id.tv_first_name);
        this.tvLastName=findViewById(R.id.tv_last_name);
        this.tvUserID=findViewById(R.id.tv_user_id);
        this.tvUserMobileNumber=findViewById(R.id.tv_mobile_number);
        this.tvUserLocation=findViewById(R.id.tv_user_location);

        sh = getSharedPreferences("prefs", MODE_PRIVATE);
        String s1 = sh.getString("user_id", "");
        userList = new ArrayList<>();
        userList = dao.getUserById(s1);
        if (userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserID().equals(s1)) {
                    tvFirstName.setText(userList.get(i).getUserFirstName().toUpperCase());
                    tvLastName.setText(userList.get(i).getUserLastName().toUpperCase());
                    tvUserID.setText(userList.get(i).getUserID());
                    tvUserMobileNumber.setText(userList.get(i).getUserPhoneNumber());
                    tvUserLocation.setText(userList.get(i).getUserLocation().toUpperCase());
                }
            }
        }
    }

}
