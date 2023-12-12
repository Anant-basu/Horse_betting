package com.example.horseriding.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horseriding.R;
import com.example.horseriding.adapter.RupeeAdapter;
import com.example.horseriding.dao.OnItemClickListener;
import com.example.horseriding.modal.RupeeModel;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {

    private ImageView ivSend, ivReceive, ivSwap;
    private RecyclerView recyclerView;
    private RupeeAdapter rupeeAdapter;
    private TextView tvEnteredAmount;
    private EditText etEnteredAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity);
        initComponent();
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.rv_rupee);
        etEnteredAmount = findViewById(R.id.et_entered_amount);
        tvEnteredAmount = findViewById(R.id.tv_entered_amount);
        rupeeAdapter = new RupeeAdapter(getRupeeList(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rupeeAdapter);
        rupeeAdapter.setClickListener(this);
        tvEnteredAmount.setAlpha(0.1f);

        etEnteredAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 3) {
                    tvEnteredAmount.setAlpha(0.9f);
                } else {
                    tvEnteredAmount.setAlpha(0.1f);
                }
            }
        });
    }
    private List<RupeeModel> getRupeeList() {
        List<RupeeModel> rupeeModelList = new ArrayList<>();
        rupeeModelList.add(new RupeeModel("1000"));
        rupeeModelList.add(new RupeeModel("2000"));
        rupeeModelList.add(new RupeeModel("3000"));
        rupeeModelList.add(new RupeeModel("4000"));
        rupeeModelList.add(new RupeeModel("5000"));
        rupeeModelList.add(new RupeeModel("6000"));
        rupeeModelList.add(new RupeeModel("7000"));
        rupeeModelList.add(new RupeeModel("8000"));
        return rupeeModelList;
    }
    @Override
    public void onItemClick(View view, int position) {
        if (view != null) {
            RupeeModel selectedRupee = getRupeeList().get(position);
            String amount = selectedRupee.getRupeeText();
            etEnteredAmount.setText(amount);
            int enteredAmount = Integer.parseInt(amount);
            tvEnteredAmount.setAlpha(enteredAmount > 100 ? 0.9f : 0.1f);
            Toast.makeText(this, "Your amount is " + amount, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
    }
}