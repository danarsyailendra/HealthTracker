package com.mercubuana.healthtracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.WaterDetailActivity;
import com.mercubuana.healthtracker.konstanta.Konstanta;
import com.mercubuana.healthtracker.model.WaterModel;

import java.util.ArrayList;
import java.util.List;

public class WaterAdapter extends RecyclerView.Adapter<WaterAdapter.MyViewHolder> {
    private Context context;
    private List<WaterModel> data = new ArrayList<>();

    public WaterAdapter(Context context, List<WaterModel> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public WaterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_water,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterAdapter.MyViewHolder holder, final int position) {
        holder.ivGambarStatus.setImageResource(data.get(position).getIntGambarStatus());
        holder.tvLiter.setText(data.get(position).getStrLiter()+" mililiter");
        holder.tvTanggal.setText(data.get(position).getStrTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WaterDetailActivity.class);
                intent.putExtra(Konstanta.KEY_ID,data.get(position).getIntId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGambarStatus;
        TextView tvLiter,tvTanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivGambarStatus = itemView.findViewById(R.id.ivGambarStatus);
            tvLiter = itemView.findViewById(R.id.tvLiter);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }
}
