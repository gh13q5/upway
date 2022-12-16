package com.mobile.upway.controller;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dto.Combination;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CombListAdapter extends RecyclerView.Adapter<CombListAdapter.ViewHolder> {
    public static final String TAG = "CombListAdapter";

    private ArrayList<Combination> combList;

    public CombListAdapter(){
        combList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.list_item_standard, parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Combination comb = combList.get(position);

        holder.title.setText(comb.getTitle());
        holder.kcalAndPrice.setText(comb.getKcal() + "kcal / " + comb.getPrice() + "원");
        holder.bread.setText(comb.getBread().getBread());
        holder.cheese.setText(comb.getCheese().getCheese());
        holder.vege.setText(comb.getVegetableList().get(0).getName());
        holder.sauce.setText(comb.getSauceList().get(0).getSauce());
        holder.options.setText(comb.getOptionsList().get(0).getOptions());
    }

    @Override
    public int getItemCount() {
        return combList.size();
    }

    /*
     ****************************************************
     * 리스트 생성 관련 (Viewholder)
     ****************************************************
     */

    public void setList(ArrayList<Combination> list) {
        this.combList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title = null;
        public TextView kcalAndPrice = null;
        public TextView bread = null;
        public TextView cheese = null;
        public TextView vege = null;
        public TextView sauce = null;
        public TextView options = null;

        ViewHolder(Context context, View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.main_title);
            kcalAndPrice = itemView.findViewById(R.id.main_kcal_price);
            bread = itemView.findViewById(R.id.bread_item);
            cheese = itemView.findViewById(R.id.cheese_item);
            vege = itemView.findViewById(R.id.vege_item);
            sauce = itemView.findViewById(R.id.sauce_item);
            options = itemView.findViewById(R.id.option_item);
        }
    }
}
