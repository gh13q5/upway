package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;

public class ccListActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_add);

        View btn7 = (View)findViewById(R.id.list_bottom_square);
        Button endlast = (Button)btn7.findViewById(R.id.ad_btn_upload);


        endlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endIntent = new Intent(getApplicationContext(),ccVegetableActivity.class);
                startActivity(endIntent);
            }
        });

    }

}
