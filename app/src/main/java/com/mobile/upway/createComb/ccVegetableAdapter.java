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
import com.mobile.upway.dto.Cheese;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;

public class ccVegetableAdapter extends RecyclerView.Adapter<ccVegetableAdapter.ViewHolder> {

    private ArrayList<Vegetable> vegetableList;
    private Context context;

    public interface onItemClickListener{
        void onItemClicked(String data);
    }

    private ccVegetableAdapter.onItemClickListener itemClickListener;

    public void setOnItemClickListener(ccVegetableAdapter.onItemClickListener listener){
        itemClickListener = listener;
    }

    public ccVegetableAdapter(){
        vegetableList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.create_comb_item, parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vegetable vegetable = vegetableList.get(position);
        Glide.with(holder.itemView)
                .load(vegetable.getImgUrl())
                .into(holder.itemimage);
        holder.itemname.setText(vegetable.getName());
        holder.kcalnprice.setText(vegetable.getKcal()+" kcal");
    }

    @Override
    public int getItemCount() {
        return vegetableList.size();
    }

    public void setList(ArrayList<Vegetable> list){
        this.vegetableList = list;
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

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //int pos = getAdapterPosition();
                    String data = ((TextView) view.findViewById(R.id.itemname)).getText().toString();
                    itemClickListener.onItemClicked(data);
                }
            });
        }
    }
}
