package com.example.horseriding;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private List<HorseDetails> getHorseListDetails(){
        List<HorseDetails> horseDetails=new ArrayList<>();
        horseDetails.add(new HorseDetails("Spartan Sprinters",R.drawable.horse_image,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Horse Able",R.drawable.horse_image,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Horse Exquisite",R.drawable.horse_image,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Riding Collaborate",R.drawable.horse_image,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Riding Beryl",R.drawable.horse_image,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        horseDetails.add(new HorseDetails("Horse Jungle",R.drawable.horse_image,"8.3ft","300Kg",("There is something about outside of a horse that is good" +
                " for the Inside of a Man")));
        return horseDetails;

    }

}