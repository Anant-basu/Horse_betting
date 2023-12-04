package com.example.horseriding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KYCPage extends AppCompatActivity implements View.OnClickListener {

    private TextView btnContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyc_page);

        initComponents();
    }

    private void initComponents() {
        this.btnContinue=this.findViewById(R.id.btn_continue);

        this.btnContinue.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_continue){
            Intent intent=new Intent(KYCPage.this, HomePage.class);
            startActivity(intent);
        }
    }
}
