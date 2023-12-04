package com.example.horseriding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorseDetailsAdapter extends RecyclerView.Adapter<HorseDetailsAdapter.ViewHolder>{

    private Context context;
    private List<HorseDetails> horseDetailsList;

    public HorseDetailsAdapter(Context context, List<HorseDetails> horseDetailsList) {
        this.context = context;
        this.horseDetailsList = horseDetailsList;
    }
    @NonNull
    @Override
    public HorseDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull HorseDetailsAdapter.ViewHolder holder, int position) {

        final HorseDetails horseDetails = horseDetailsList.get(position);
        holder.horseImage.setImageResource(horseDetails.getHorseImage());
        holder.horseName.setText(horseDetails.getHorseName());
        holder.horseHeight.setText(horseDetails.getHorseHeight());
        holder.horseWeight.setText(horseDetails.getHorseWeight());
        holder.horseDescription.setText(horseDetails.getHorseDescription());
    }
    @Override
    public int getItemCount() {
        return horseDetailsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView horseImage;
        private TextView horseName,horseHeight,horseWeight,horseDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            horseImage=itemView.findViewById(R.id.iv_horse);
            horseName=itemView.findViewById(R.id.tv_horse_name);
            horseHeight=itemView.findViewById(R.id.tv_horse_height);
            horseWeight=itemView.findViewById(R.id.tv_horse_weight);
            horseDescription=itemView.findViewById(R.id.tv_description);
        }
    }
}
