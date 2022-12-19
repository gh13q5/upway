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
import com.mobile.upway.dto.Sauce;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ccSauceAdapter extends RecyclerView.Adapter<ccSauceAdapter.ViewHolder> {

    private ArrayList<Sauce> sauceList;
    private Context context;
    double kcal;

    public interface onItemClickListener{
        void onItemClicked(String data, double kcaldata);
    }

    private ccSauceAdapter.onItemClickListener itemClickListener;

    public void setOnItemClickListener(ccSauceAdapter.onItemClickListener listener){
        itemClickListener = listener;
    }

    public ccSauceAdapter(){
        sauceList = new ArrayList<>();
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
        Sauce sauce = sauceList.get(position);
        kcal = sauce.getKcal();
        DecimalFormat df = new DecimalFormat("0.0");
        String skcal = df.format(kcal);
        Glide.with(holder.itemView)
                .load(sauce.getImgUrl())
                .into(holder.itemimage);
        holder.itemname.setText(sauce.getName());
        holder.kcalnprice.setText(skcal+" kcal");
    }

    @Override
    public int getItemCount() {
        return sauceList.size();
    }

    public void setList(ArrayList<Sauce> list){
        this.sauceList = list;
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
                    double kcaldata = kcal;
                    itemClickListener.onItemClicked(data, kcaldata);
                }
            });
        }
    }
}
