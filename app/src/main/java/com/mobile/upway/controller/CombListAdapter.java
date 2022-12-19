package com.mobile.upway.controller;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dao.MenuDAO;
import com.mobile.upway.dao.UserScrapDAO;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Menu;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CombListAdapter extends RecyclerView.Adapter<CombListAdapter.ViewHolder> {
    public static final String TAG = "CombListAdapter";

    // ADAPTER
    private Context context;
    private ArrayList<Combination> combList;
    LayoutInflater inflater;

    // DAO
    private UserScrapDAO userScrapDAO = new UserScrapDAO();
    private MenuDAO menuDAO = new MenuDAO();
    private CombinationDAO combinationDAO = new CombinationDAO();

    // FIREBASE AUTH
    private FirebaseAuth firebaseAuth;

    // CLICK EVENT INTERFACE
    public interface OnItemClickListener{
        void onItemClicked(int position, Combination comb);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    // VIEWHOLDER
    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton scrapBtn = null;
        public ImageView sandwichImage = null;
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

            scrapBtn = itemView.findViewById(R.id.scrap_button);
            sandwichImage = itemView.findViewById(R.id.main_image);
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
                            Log.d(TAG, ((Combination)object).getTitle());
                            Intent intent = new Intent(context, MyDetailActivity.class);
                            intent.putExtra("combination", (Combination) object);
                            context.startActivity(intent);
                        }
                    });
                }
            });
        }
    }

    public CombListAdapter() {
        combList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.list_item_standard, parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Combination comb = combList.get(position);
        double kcal = comb.getKcal();
        DecimalFormat df = new DecimalFormat("0.0");
        String skcal = df.format(kcal);

        holder.hiddenCombId.setText(comb.getId());

        Glide.with(holder.itemView.getContext())
                .load(comb.getImgUrl())
                .into(holder.sandwichImage);

        holder.title.setText(comb.getTitle());
        holder.kcalAndPrice.setText(skcal + "kcal / " + comb.getPrice() + "원");
        holder.bread.setText(comb.getBread().getName());
        holder.cheese.setText(comb.getCheese().getName());

        // 반복문으로 생성하는 재료들
        if(holder.vegeLayout.getChildCount() > 1){
            for(int i = holder.vegeLayout.getChildCount() - 1; i >= 1; i--){
                View child = holder.vegeLayout.getChildAt(i);
                Log.d(TAG, (String) ((TextView)child).getText());
                ((ViewGroup) child.getParent()).removeView(child);
            }
        }
        for (int i = 0; i < comb.getVegetableList().size(); i++) {
            View view = inflater.inflate(R.layout.list_item_ingredient, null);
            TextView child = (TextView) view.findViewById(R.id.ingredient);
            child.setText("-" + comb.getVegetableList().get(i).getName());

            ((ViewGroup) child.getParent()).removeView(child);
            holder.vegeLayout.addView(child);
        }

        if(holder.sauceLayout.getChildCount() > 1){
            for(int i = holder.sauceLayout.getChildCount() - 1; i >= 1; i--){
                View child = holder.sauceLayout.getChildAt(i);
                ((ViewGroup) child.getParent()).removeView(child);
            }
        }
        for (int i = 0; i < comb.getSauceList().size(); i++) {
            View view = inflater.inflate(R.layout.list_item_ingredient_yellow, null);
            TextView child = (TextView) view.findViewById(R.id.ingredient);
            child.setText(comb.getSauceList().get(i).getName());

            ((ViewGroup) child.getParent()).removeView(child);
            holder.sauceLayout.addView(child);
        }

        if(holder.optionsLayout.getChildCount() > 1){
            for(int i = holder.optionsLayout.getChildCount() - 1; i >= 1; i--){
                View child = holder.optionsLayout.getChildAt(i);
                ((ViewGroup) child.getParent()).removeView(child);
            }
        }
        for (int i = 0; i < comb.getOptionsList().size(); i++) {
            View view = inflater.inflate(R.layout.list_item_ingredient, null);
            TextView child = (TextView) view.findViewById(R.id.ingredient);
            child.setText(comb.getOptionsList().get(i).getName());

            ((ViewGroup) child.getParent()).removeView(child);
            holder.optionsLayout.addView(child);
        }

        // 북마크되어 있으면 그냥 star, 안 되어 있으면 blank_star
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            userScrapDAO.existUserScrapByUserAndComb(currentUser.getEmail(), comb.getId(), new FireStoreCallback() {
                @Override
                public void onCallback(Object object) {
                    if (object != null) {
                        holder.scrapBtn.setImageResource(R.drawable.icon_star);
                        holder.scrapBtn.setOnClickListener(view -> {
                            Log.d(TAG, "북마크 버튼(not null) 클릭됨!");
                            userScrapDAO.deleteUserScrap(currentUser.getEmail(), comb.getId(), object1 -> notifyDataSetChanged());
                        });
                    } else {
                        holder.scrapBtn.setImageResource(R.drawable.icon_blank_star);
                        holder.scrapBtn.setOnClickListener(view -> {
                            Log.d(TAG, "북마크 버튼(null) 클릭됨!");
                            userScrapDAO.createUserScrap(currentUser.getEmail(), comb.getId(), object1 -> notifyDataSetChanged());
                        });
                    }
                }
            });
        } else {
            holder.scrapBtn.setOnClickListener(view -> {
                Log.d(TAG, "북마크 버튼(null) 클릭됨!");
                Toast.makeText(context.getApplicationContext(), "로그인이 필요한 기능입니다.", Toast.LENGTH_SHORT).show();
            });
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
}
