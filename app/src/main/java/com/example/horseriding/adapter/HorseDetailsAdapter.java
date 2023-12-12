package com.example.horseriding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horseriding.R;
import com.example.horseriding.modal.HorseDetails;

import java.util.List;

public class HorseDetailsAdapter extends RecyclerView.Adapter<HorseDetailsAdapter.ViewHolder>{

    private final Context context;
    private final List<HorseDetails> horseDetailsList;
    public HorseDetailsAdapter(Context context, List<HorseDetails> horseDetailsList) {
        this.context = context;
        this.horseDetailsList = horseDetailsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HorseDetails horseDetails = horseDetailsList.get(position);
        holder.bind(horseDetails);
    }
    @Override
    public int getItemCount() {
        return horseDetailsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView horseImage;
        private final TextView horseName, horseHeight, horseWeight, horseDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            horseImage = itemView.findViewById(R.id.iv_horse);
            horseName = itemView.findViewById(R.id.tv_horse_name);
            horseHeight = itemView.findViewById(R.id.tv_horse_height);
            horseWeight = itemView.findViewById(R.id.tv_horse_weight);
            horseDescription = itemView.findViewById(R.id.tv_description);
        }
        public void bind(HorseDetails horseDetails) {
            horseImage.setImageResource(horseDetails.getHorseImage());
            horseName.setText(horseDetails.getHorseName());
            horseHeight.setText(horseDetails.getHorseHeight());
            horseWeight.setText(horseDetails.getHorseWeight());
            horseDescription.setText(horseDetails.getHorseDescription());
        }
    }
}
