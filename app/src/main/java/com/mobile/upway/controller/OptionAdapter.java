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
import com.mobile.upway.dto.Options;

import java.util.ArrayList;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder>{
    public static String TAG = "OptAdapter";

    private Context context;
    private ArrayList<Options> optionList;

    public OptionAdapter() {optionList = new ArrayList<>();}

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = inflater.inflate(R.layout.detail_ingridient_item,parent,false);
        OptionViewHolder viewHolder = new OptionViewHolder(context,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        Options options = optionList.get(position);
        holder.name.setText(options.getName());
        Glide.with(holder.itemView)
                .load(options.getImgUrl())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }
    public void setList(ArrayList<Options> list){
        this.optionList = list;
        notifyDataSetChanged();
    }

    class OptionViewHolder extends RecyclerView.ViewHolder{
        public TextView name = null;
        public ImageView img = null;
        public OptionViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ingridientImg);
            name = itemView.findViewById(R.id.ingridientName);
        }
    }
}
