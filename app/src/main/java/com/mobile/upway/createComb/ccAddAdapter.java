package com.mobile.upway.createComb;

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

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ccAddAdapter extends RecyclerView.Adapter<ccAddAdapter.ViewHolder> {

    private ArrayList<Options> optionsList;
    private Context context;

    public ccAddAdapter(){
        optionsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.create_comb_item, parent, false);
        ViewHolder viewholder = new ViewHolder(context,view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Options options = optionsList.get(position);
        Glide.with(holder.itemView)
                .load(options.getImgUrl())
                .into(holder.itemimage);
        holder.itemname.setText(options.getName());
        holder.kcalnprice.setText(options.getKcal()+" kcal / "+options.getPrice()+" 원");
    }

    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    public void setList(ArrayList<Options> list){
        this.optionsList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemimage;
        public TextView itemname;
        public TextView kcalnprice;

        public ViewHolder(@NonNull Context context, View itemView) {
            super(itemView);

            this.itemimage = itemView.findViewById(R.id.itemimage);
            this.itemname = itemView.findViewById(R.id.itemname);
            this.kcalnprice = itemView.findViewById(R.id.itemkcalnprice);
        }
    }
}

