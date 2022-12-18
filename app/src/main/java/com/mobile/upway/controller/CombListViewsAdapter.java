package com.mobile.upway.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dao.UserScrapDAO;
import com.mobile.upway.dto.Combination;

import java.util.ArrayList;

public class CombListViewsAdapter extends RecyclerView.Adapter<CombListViewsAdapter.ViewHolder> {
    public static final String TAG = "CombListAdapter";

    // ADAPTER
    private Context context;
    private ArrayList<Combination> combList;

    // DAO
    private UserScrapDAO userScrapDAO = new UserScrapDAO();
    private CombinationDAO combinationDAO = new CombinationDAO();

    // FIREBASE AUTH
    private FirebaseAuth firebaseAuth;

    public CombListViewsAdapter() {
        combList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.list_item_views, parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Combination comb = combList.get(position);

        holder.scrapCount.setText(String.valueOf(comb.getScraps()));

        holder.title.setText(comb.getTitle());
        holder.kcalAndPrice.setText(comb.getKcal() + "kcal / " + comb.getPrice() + "원");
        holder.bread.setText(comb.getBread().getName());
        holder.cheese.setText(comb.getCheese().getName());

        // 반복문으로 생성하는 재료들
        for (int i = 0; i < comb.getVegetableList().size(); i++) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item_ingredient, null);
            TextView child = (TextView) view.findViewById(R.id.ingredient);
            child.setText("-" + comb.getVegetableList().get(i).getName());

            ((ViewGroup) child.getParent()).removeView(child);
            holder.vegeLayout.addView(child);
        }

        for (int i = 0; i < comb.getSauceList().size(); i++) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item_ingredient_yellow, null);
            TextView child = (TextView) view.findViewById(R.id.ingredient);
            child.setText(comb.getSauceList().get(i).getName());

            ((ViewGroup) child.getParent()).removeView(child);
            holder.sauceLayout.addView(child);
        }

        for (int i = 0; i < comb.getOptionsList().size(); i++) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item_ingredient, null);
            TextView child = (TextView) view.findViewById(R.id.ingredient);
            child.setText(comb.getOptionsList().get(i).getName());

            ((ViewGroup) child.getParent()).removeView(child);
            holder.optionsLayout.addView(child);
        }
    }

    @Override
    public int getItemCount() {
        return combList.size();
    }

    public void setList(ArrayList<Combination> list) {
        this.combList = list;
        notifyDataSetChanged();
    }


    // VIEWHOLDER
    class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatButton scrapCount = null;
        public TextView title = null;
        public TextView kcalAndPrice = null;
        public TextView bread = null;
        public TextView cheese = null;
        public LinearLayout vegeLayout = null;
        public LinearLayout sauceLayout = null;
        public LinearLayout optionsLayout = null;
        public TextView hiddenCombId = null;

        ViewHolder(Context context, View itemView) {
            super(itemView);

            scrapCount = itemView.findViewById(R.id.scrap_count);
            title = itemView.findViewById(R.id.main_title);
            kcalAndPrice = itemView.findViewById(R.id.main_kcal_price);
            bread = itemView.findViewById(R.id.bread_item);
            cheese = itemView.findViewById(R.id.cheese_item);
            vegeLayout = itemView.findViewById(R.id.vege_type);
            sauceLayout = itemView.findViewById(R.id.sauce_type);
            optionsLayout = itemView.findViewById(R.id.option_type);
            hiddenCombId = itemView.findViewById(R.id.hidden_comb_id);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String combId = ((TextView) view.findViewById(R.id.hidden_comb_id)).getText().toString();
                    combinationDAO.getCombById(combId, new FireStoreCallback() {
                        @Override
                        public void onCallback(Object object) {
                            Intent intent = new Intent(context, MyDetailActivity.class);
                            intent.putExtra("combination", (Combination) object);
                            context.startActivity(intent);
                        }
                    });
                }
            });
        }
    }
}
