package com.example.horseriding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private ViewFlipper flipper;
    private RecyclerView recyclerView;
    private HorseDetailsAdapter adapter;
    private List<HorseDetails> horseDetailsList;
    int[] imageArray = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        initComponent();
    }
    private void initComponent() {

        this.recyclerView=findViewById(R.id.rv_horse_details);
        flipper = findViewById(R.id.view_flipper);
        for (int i = 0; i < imageArray.length; i++) {
            showImage(imageArray[i]);
        }
        setupBottomNavigationView();
        adapter=new HorseDetailsAdapter(this,getHorseListDetails());
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void showImage(int img) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(2000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.slide_out_right);
    }
    private List<HorseDetails> getHorseListDetails() {
        List<HorseDetails> horseDetails=new ArrayList<>();
        horseDetails.add(new HorseDetails("Spartan Sprinters",R.drawable.jockey1,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Horse Able",R.drawable.jockey2,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Horse Exquisite",R.drawable.jockey3,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Riding Collaborate",R.drawable.jockey4,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Riding Beryl",R.drawable.jockey5,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Horse Jungle",R.drawable.jockey6,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        return horseDetails;
    }
    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id= item.getItemId();

            if(id==R.id.home){
                item.setIcon(R.drawable.home_white);
                item.setTitle("home");
            } else if (id==R.id.upcoming_race) {
                item.setIcon(R.drawable.upcoming_white);
                item.setTitle("Upcoming Race");
            } else if (id==R.id.finish) {
                item.setIcon(R.drawable.racing_white);
                item.setTitle("Finish");
            } else if (id==R.id.profile) {
                item.setIcon(R.drawable.user_white);
                item.setTitle("Profile");
                startActivity(new Intent(this, ProfileActivity.class));
            }
            return true;
        });
    }
}