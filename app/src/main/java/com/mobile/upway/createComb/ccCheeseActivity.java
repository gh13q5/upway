package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;

public class ccCheeseActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_cheese);

        View btn3 = (View)findViewById(R.id.cheese_bottom_square);
        Button cheeseToVege = (Button)btn3.findViewById(R.id.cm_btn_next);
        Button cheeseToBread = (Button)btn3.findViewById(R.id.cm_btn_before);

        cheeseToVege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vegeIntent = new Intent(getApplicationContext(),ccVegetableActivity.class);
                startActivity(vegeIntent);
            }
        });

        cheeseToBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent breadIntent = new Intent(getApplicationContext(),ccBreadActivity.class);
                startActivity(breadIntent);
            }
        });

    }

}
