package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;

public class ccAddActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_add);

        View btn6 = (View)findViewById(R.id.add_bottom_square);
        Button addToSauce = (Button)btn6.findViewById(R.id.ad_btn_before);
        Button endSave = (Button)btn6.findViewById(R.id.ad_btn_end);

        addToSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sauceIntent = new Intent(getApplicationContext(),ccSauceActivity.class);
                startActivity(sauceIntent);
            }
        });

        endSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getApplicationContext(),ccListActivity.class);
                startActivity(listIntent);
            }
        });

    }

}
