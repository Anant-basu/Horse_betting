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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {

    private ImageView ivSend, ivReceive, ivSwap;
    private RecyclerView recyclerView;
    private RupeeAdapter rupeeAdapter;
    private TextView tvEnteredAmount;
    private EditText etEnteredAmount;
    private BarChart barChart;

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
        barChart=findViewById(R.id.lc_bar_chart);
        showBarChart(barChart);
        rupeeAdapter = new RupeeAdapter(getRupeeList(), this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rupeeAdapter);
        rupeeAdapter.setClickListener(this);

        tvEnteredAmount.setAlpha(0.1f);
        tvEnteredAmount.setClickable(false);

        etEnteredAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 3) {
                    tvEnteredAmount.setAlpha(0.9f);
                    tvEnteredAmount.setClickable(true);
                } else {
                    tvEnteredAmount.setAlpha(0.1f);
                    tvEnteredAmount.setClickable(false);
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

    private void showBarChart(BarChart barChart){

        BarDataSet barDataSet1, barDataSet2;

        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};
        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Set");
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.red_color));
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Second Set");
        barDataSet2.setColor(getApplicationContext().getResources().getColor(R.color.orange_color));

        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);

        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);
        float barSpace = 0.1f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.15f);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.animate();
        barChart.groupBars(0, groupSpace, barSpace);

        barChart.invalidate();

    }

    private ArrayList<BarEntry> getBarEntriesOne() {
        ArrayList<BarEntry> barEntries= new ArrayList<>();

        barEntries.add(new BarEntry(1f, 4));
        barEntries.add(new BarEntry(2f, 6));
        barEntries.add(new BarEntry(3f, 8));
        barEntries.add(new BarEntry(4f, 2));
        barEntries.add(new BarEntry(5f, 4));
        barEntries.add(new BarEntry(6f, 1));
        return barEntries;
    }

    private ArrayList<BarEntry> getBarEntriesTwo() {

        ArrayList<BarEntry> barEntries= new ArrayList<>();
        barEntries.add(new BarEntry(1f, 8));
        barEntries.add(new BarEntry(2f, 12));
        barEntries.add(new BarEntry(3f, 4));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(5f, 7));
        barEntries.add(new BarEntry(6f, 3));
        return barEntries;
    }

}