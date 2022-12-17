package com.mobile.upway.createComb;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.upway.R;
import com.mobile.upway.dto.BrChSaVe;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ccAdapter extends RecyclerView.Adapter<ccViewHolder> {

    private ArrayList<BrChSaVe> arrayList;
    private Context context;

    public ccAdapter(ArrayList<BrChSaVe> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ccViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_comb_item, parent, false);
        ccViewHolder viewholder = new ccViewHolder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ccViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImgUrl())
                .into(holder.itemimage);
        holder.itemname.setText(arrayList.get(position).getName());
        holder.kcalnprice.setText(arrayList.get(position).getKcal()+" kcal");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
