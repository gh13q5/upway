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

import java.util.ArrayList;

public class ccCheeseAdapter extends RecyclerView.Adapter<ccCheeseAdapter.ViewHolder> {

    private ArrayList<Cheese> cheeseList;
    private Context context;

    public interface onItemClickListener{
        void onItemClicked(String data);
    }

    private ccCheeseAdapter.onItemClickListener itemClickListener;

    public void setOnItemClickListener(ccCheeseAdapter.onItemClickListener listener){
        itemClickListener = listener;
    }

    public ccCheeseAdapter(){
        cheeseList = new ArrayList<>();
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
        Cheese cheese = cheeseList.get(position);
        Glide.with(holder.itemView)
                .load(cheese.getImgUrl())
                .into(holder.itemimage);
        holder.itemname.setText(cheese.getName());
        holder.kcalnprice.setText(cheese.getKcal()+" kcal");
    }

    @Override
    public int getItemCount() {
        return cheeseList.size();
    }

    public void setList(ArrayList<Cheese> list){
        this.cheeseList = list;
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
