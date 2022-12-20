package com.mobile.upway.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.upway.R;
import com.mobile.upway.dto.Options;
import com.mobile.upway.dto.Sauce;

import java.util.ArrayList;

public class SauceAdapter extends RecyclerView.Adapter<SauceAdapter.InrVIewHolder> {
    public static String TAG = "InrAddapter";

    private Context context;
    private ArrayList<Sauce> sauceArrayList;

    public SauceAdapter(){
        sauceArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public InrVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = inflater.inflate(R.layout.detail_ingridient_item,parent,false);
        InrVIewHolder vIewHolder = new InrVIewHolder(context,view);
        return vIewHolder;
    }

    @Override
    public void onBindViewHolder(InrVIewHolder holder, int position) {
        Sauce options = sauceArrayList.get(position);
        holder.name.setText(options.getName());
        Glide.with(holder.itemView)
                .load(options.getImgUrl())
                .into(holder.img);
        Log.d("sauce"," ");
    }

    @Override
    public int getItemCount() {
        return sauceArrayList.size();
    }
    public void setList(ArrayList<Sauce> list){
        this.sauceArrayList = list;
        notifyDataSetChanged();
    }

    class InrVIewHolder extends RecyclerView.ViewHolder{
        public TextView name = null;
        public ImageView img = null;

        public InrVIewHolder(Context context,@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingridientName);
            img = itemView.findViewById(R.id.ingridientImg);
        }
    }


}