package com.example.horseriding.ui;

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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.horseriding.R;
import com.example.horseriding.modal.User;
import com.example.horseriding.dao.UserDao;
import com.example.horseriding.database.UserDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GALLERY_REQUEST = 101;
    private UserDatabase userDatabase;
    private UserDao dao;
    private List<User> userList;
    SharedPreferences preferences;
    SharedPreferences.Editor myEdit;
    String logInUserID;
    private TextView tvFirstName, tvLastName, tvUserID, tvUserMobileNumber, tvUserLocation, tvProfileEdit, tvLogout;
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
        this.tvLogout = findViewById(R.id.tv_logout_button);

        this.tvProfileEdit.setOnClickListener(this);
        this.ivSelectImage.setOnClickListener(this);
        this.tvLogout.setOnClickListener(this);

        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        logInUserID = preferences.getString("user_id", "");
        myEdit = preferences.edit();

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

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                    }
                }
            }
    );

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_user_image) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            galleryLauncher.launch(photoPickerIntent);
        } else if (v.getId() == R.id.tv_profile_edit_button) {
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_logout_button) {
            myEdit.putString("user_ID", "");
            myEdit = preferences.edit();
            startActivity(new Intent(this, LoginPageActivity.class));
            Toast.makeText(this, "You are successfully logged out.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == GALLERY_REQUEST) {
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    ivSelectImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }
            }
    }
}
