package com.mobile.upway.createComb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.upway.R;

import java.util.ArrayList;

public class ccListAdapter extends RecyclerView.Adapter<ccListAdapter.ViewHolder> {

    static final String TAG = "ccListAdapter";

    private ArrayList<String> arrayList;
    private Context context;

    public ccListAdapter(){
        arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.create_comb_list_item, parent, false);
        ViewHolder viewholder = new ViewHolder(context,view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = arrayList.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setList(String list){
        arrayList.add(list);
        Log.d(TAG, list+" 어댑터 추가");
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(@NonNull Context context, View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.listitemname);
        }
    }
}
