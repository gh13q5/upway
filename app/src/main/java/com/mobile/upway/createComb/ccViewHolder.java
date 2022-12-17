package com.mobile.upway.createComb;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;

public class ccViewHolder extends RecyclerView.ViewHolder {
    public ImageView itemimage;
    public TextView itemname;
    public TextView kcalnprice;

    public ccViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemimage = itemView.findViewById(R.id.itemimage);
        this.itemname = itemView.findViewById(R.id.itemname);
        this.kcalnprice = itemView.findViewById(R.id.itemkcalnprice);
    }
}
