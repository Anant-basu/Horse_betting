package com.example.horseriding.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.horseriding.modal.HorseDetails;
import com.example.horseriding.adapter.HorseDetailsAdapter;
import com.example.horseriding.adapter.ImageSliderAdapter;
import com.example.horseriding.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HorseDetailsAdapter adapter;
    private ViewPager viewPager;
    private ImageSliderAdapter imageSliderAdapter;
    private int currentPosition = 0;
    private Handler handler = new Handler();
    private Runnable runnable;
    private final int[] images = {R.drawable.image4, R.drawable.image2, R.drawable.image3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        initComponent();
    }

    private void initComponent() {

        this.recyclerView = findViewById(R.id.rv_horse_details);

        viewPager = findViewById(R.id.viewPager);
        imageSliderAdapter = new ImageSliderAdapter(this, images);
        viewPager.setAdapter(imageSliderAdapter);

        runnable = () -> {
            if (currentPosition == images.length - 1) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
            viewPager.setCurrentItem(currentPosition, true);
            handler.postDelayed(runnable, 3000);
        };
        handler.postDelayed(runnable, 3000);

        setupBottomNavigationView();
        adapter = new HorseDetailsAdapter(this, getHorseListDetails());
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<HorseDetails> getHorseListDetails() {
        List<HorseDetails> horseDetails = new ArrayList<>();
        String Description = "There is something about outside of a horse that is good for the Inside of a Man";
        horseDetails.add(new HorseDetails("Spartan Sprinters", R.drawable.jockey1, "8.3ft", "300Kg", Description));
        horseDetails.add(new HorseDetails("Horse Able", R.drawable.jockey2, "8.3ft", "300Kg", Description));
        horseDetails.add(new HorseDetails("Horse Exquisite", R.drawable.jockey3, "8.3ft", "300Kg", Description));
        horseDetails.add(new HorseDetails("Riding Collaborate", R.drawable.jockey4, "8.3ft", "300Kg", Description));
        horseDetails.add(new HorseDetails("Riding Beryl", R.drawable.jockey5, "8.3ft", "300Kg", Description));
        horseDetails.add(new HorseDetails("Horse Jungle", R.drawable.jockey6, "8.3ft", "300Kg", Description));
        return horseDetails;
    }
    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                item.setIcon(R.drawable.home_white);
                item.setTitle("home");
            } else if (id == R.id.upcoming_race) {
                item.setIcon(R.drawable.upcoming_white);
                item.setTitle("Upcoming Race");
            } else if (id == R.id.finish) {
                item.setIcon(R.drawable.racing_white);
                item.setTitle("Finish");
            } else if (id == R.id.profile) {
                item.setIcon(R.drawable.user_white);
                item.setTitle("Profile");
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id==R.id.main_wallet) {
                item.setIcon(R.drawable.wallet);
                item.setTitle("Profile");
            }
            return true;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}