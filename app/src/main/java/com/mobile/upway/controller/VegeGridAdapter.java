package com.mobile.upway.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.upway.R;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;

public class VegeGridAdapter extends RecyclerView.Adapter<VegeGridAdapter.VegeVIewHolder> {
    public static String TAG = "VegeListAdapter";

    private Context context;
    private ArrayList<Vegetable> vegetablesList;

    public VegeGridAdapter(){
        vegetablesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public VegeVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = inflater.inflate(R.layout.detail_vege_item,parent,false);
        VegeVIewHolder vIewHolder = new VegeVIewHolder(context,view);
        return vIewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VegeVIewHolder holder, int position) {
        Vegetable vegetable = vegetablesList.get(position);
        holder.name.setText(vegetable.getName());
        Glide.with(holder.itemView)
                .load(vegetable.getImgUrl())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return vegetablesList.size();
    }
    public void setList(ArrayList<Vegetable> list){
        this.vegetablesList = list;
        notifyDataSetChanged();
    }

    class VegeVIewHolder extends RecyclerView.ViewHolder{
        public TextView name = null;
        public ImageView img = null;

        public VegeVIewHolder(Context context,@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vgName);
            img = itemView.findViewById(R.id.vgImg);
        }
    }


}
