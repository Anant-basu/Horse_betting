package com.example.horseriding.ui;

import android.os.Bundle;
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
    RecyclerView recyclerView;
    RupeeAdapter rupeeAdapter;
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
        String enteredAmount = etEnteredAmount.getText().toString();
        /*if (Integer.parseInt(enteredAmount) > 100 &&!enteredAmount.equals("")) {
            tvEnteredAmount.setAlpha(.9f);
        } else {
            tvEnteredAmount.setAlpha(.1f);
        }*/

        tvEnteredAmount.setAlpha(.1f);

        rupeeAdapter = new RupeeAdapter(getList(), this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rupeeAdapter);
        rupeeAdapter.setClickListener(this);

    }

    private List<RupeeModel> getList() {
        List<RupeeModel> rupeeModelList = new ArrayList<>();
        rupeeModelList.add(new RupeeModel("500"));
        rupeeModelList.add(new RupeeModel("1000"));
        rupeeModelList.add(new RupeeModel("2000"));
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
            String amount = getList().get(position).getRupeeText();
            etEnteredAmount.setText(amount);
            int enteredAmount = Integer.parseInt(amount);
            if (enteredAmount > 100) {
                tvEnteredAmount.setAlpha(.9f);
            } else {
                tvEnteredAmount.setAlpha(.1f);
            }
            Toast.makeText(this, "Your amount is " + amount, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
