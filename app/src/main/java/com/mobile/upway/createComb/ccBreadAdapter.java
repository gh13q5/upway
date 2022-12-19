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
import com.mobile.upway.dto.Bread;

import java.util.ArrayList;

public class ccBreadAdapter extends RecyclerView.Adapter<ccBreadAdapter.ViewHolder> {

    private ArrayList<Bread> breadList;
    private Context context;

    public interface onItemClickListener{
        void onItemClicked(String data);
    }

    private ccBreadAdapter.onItemClickListener itemClickListener;

    public void setOnItemClickListener(ccBreadAdapter.onItemClickListener listener){
        itemClickListener = listener;
    }

    public ccBreadAdapter(){
        breadList = new ArrayList<>();
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
        Bread bread = breadList.get(position);
        Glide.with(holder.itemView)
                .load(bread.getImgUrl())
                .into(holder.itemimage);
        holder.itemname.setText(bread.getName());
        holder.kcalnprice.setText(bread.getKcal()+" kcal");
    }

    @Override
    public int getItemCount() {
        return breadList.size();
    }

    public void setList(ArrayList<Bread> list){
        this.breadList = list;
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
