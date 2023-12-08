package com.example.horseriding.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horseriding.R;
import com.example.horseriding.adapter.RupeeAdapter;
import com.example.horseriding.dao.OnClickListener;
import com.example.horseriding.modal.RupeeModel;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity implements OnClickListener {

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

        rupeeAdapter = new RupeeAdapter(getList(), this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rupeeAdapter);

    }

    private List<RupeeModel> getList() {
        List<RupeeModel> rupeeModelList = new ArrayList<>();
        rupeeModelList.add(new RupeeModel("$500"));
        rupeeModelList.add(new RupeeModel("$1000"));
        rupeeModelList.add(new RupeeModel("$2000"));
        rupeeModelList.add(new RupeeModel("$4000"));
        rupeeModelList.add(new RupeeModel("$5000"));
        rupeeModelList.add(new RupeeModel("$6000"));
        rupeeModelList.add(new RupeeModel("$7000"));
        rupeeModelList.add(new RupeeModel("$8000"));

        return rupeeModelList;
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}
