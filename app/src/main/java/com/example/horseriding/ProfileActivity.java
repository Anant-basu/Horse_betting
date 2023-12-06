package com.example.horseriding;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GALLERY_REQUEST = 101;
    private UserDatabase userDatabase;
    private UserDao dao;
    private List<User> userList;
    SharedPreferences sh;
    String logInUserID;
    private TextView tvFirstName, tvLastName, tvUserID, tvUserMobileNumber, tvUserLocation, tvProfileEdit;
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
        this.tvFirstName = findViewById(R.id.tv_first_name);
        this.tvLastName = findViewById(R.id.tv_last_name);
        this.tvUserID = findViewById(R.id.tv_user_id);
        this.tvUserMobileNumber = findViewById(R.id.tv_mobile_number);
        this.tvUserLocation = findViewById(R.id.tv_user_location);
        this.ivSelectImage = findViewById(R.id.iv_user_image);
        this.tvProfileEdit = findViewById(R.id.tv_profile_edit_button);

        this.tvProfileEdit.setOnClickListener(this::onClick);
        this.ivSelectImage.setOnClickListener(this::onClick);

        sh = getSharedPreferences("prefs", MODE_PRIVATE);
        logInUserID = sh.getString("user_id", "");
        userList = new ArrayList<>();
        userList = dao.getUserById(logInUserID);
        if (userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserID().equals(logInUserID)) {
                    tvFirstName.setText(userList.get(i).getUserFirstName().toUpperCase());
                    tvLastName.setText(userList.get(i).getUserLastName().toUpperCase());
                    tvUserID.setText(userList.get(i).getUserID());
                    tvUserMobileNumber.setText(userList.get(i).getUserPhoneNumber());
                    tvUserLocation.setText(userList.get(i).getUserLocation().toUpperCase());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_user_image) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        } else if (v.getId()==R.id.tv_profile_edit_button) {
            Intent intent=new Intent(this, ProfileEdit.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        ivSelectImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
}
