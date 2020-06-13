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

import com.mercubuana.healthtracker.BmiDetailActivity;
import com.android.healthtracker.R;
import com.mercubuana.healthtracker.model.BmiModel;

import java.util.ArrayList;
import java.util.List;

public class BmiAdapter extends RecyclerView.Adapter<BmiAdapter.MyViewHolder> {

    private Context context;
    private List<BmiModel> data = new ArrayList<>();

    public BmiAdapter(Context context, List<BmiModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BmiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_bmi, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BmiAdapter.MyViewHolder holder, final int position) {
        holder.ivGambarStatus.setImageResource(data.get(position).getIntGambarStatus());
        holder.tvTinggi.setText("Tinggi : "+data.get(position).getStrTinggi()+"cm");
        holder.tvBobot.setText("Bobot: "+data.get(position).getStrBobot()+"kg");
        holder.tvTanggal.setText(data.get(position).getStrTanggal());
        holder.tvStatus.setText(data.get(position).getStrStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BmiDetailActivity.class);
                intent.putExtra(BmiDetailActivity.KEY_ID,data.get(position).getIntId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTinggi, tvBobot, tvStatus,tvTanggal;
        ImageView ivGambarStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTinggi = itemView.findViewById(R.id.tvTinggi);
            tvBobot = itemView.findViewById(R.id.tvBobot);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ivGambarStatus = itemView.findViewById(R.id.ivGambarStatus);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }
}
