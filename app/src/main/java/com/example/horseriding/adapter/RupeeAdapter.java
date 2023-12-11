package com.example.horseriding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horseriding.R;
import com.example.horseriding.dao.OnItemClickListener;
import com.example.horseriding.modal.RupeeModel;

import java.util.List;

public class RupeeAdapter  extends RecyclerView.Adapter<RupeeAdapter.ViewHolder>{

    private List<RupeeModel> rupeeModelList;
    Context context;
    private static OnItemClickListener clickListener;

    public RupeeAdapter(List<RupeeModel> rupeeModelList, Context context) {
        this.rupeeModelList = rupeeModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RupeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_rupee, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RupeeAdapter.ViewHolder holder, int position) {

        final RupeeModel myListData = rupeeModelList.get(position);
        holder.textView.setText(myListData.getRupeeText());

    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return rupeeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_rupee);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v,getPosition());
        }
    }

}
