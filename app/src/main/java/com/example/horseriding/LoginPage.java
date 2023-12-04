package com.example.horseriding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSignUP,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        initComponents();

    }

    private void initComponents(){
        this.tvSignUP =findViewById(R.id.tv_sign_up);
        this.btnLogin=findViewById(R.id.btn_login);

        this.tvSignUP.setOnClickListener(this::onClick);
        this.btnLogin.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tv_sign_up){
            Intent intent=new Intent(LoginPage.this, SignUPPage.class);
            startActivity(intent);
        } else if (v.getId()==R.id.btn_login) {
            Intent intent=new Intent(LoginPage.this, KYCPage.class);
            startActivity(intent);
        }
    }
}
