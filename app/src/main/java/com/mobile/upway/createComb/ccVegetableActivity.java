package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;

public class ccVegetableActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_vegetable);

        View btn4 = (View)findViewById(R.id.vegetable_bottom_square);
        Button vegeToSauce = (Button)btn4.findViewById(R.id.cm_btn_next);
        Button vegeToCheese = (Button)btn4.findViewById(R.id.cm_btn_before);

        vegeToSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sauceIntent = new Intent(getApplicationContext(),ccSauceActivity.class);
                startActivity(sauceIntent);
            }
        });

        vegeToCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cheeseIntent = new Intent(getApplicationContext(),ccCheeseActivity.class);
                startActivity(cheeseIntent);
            }
        });

    }

}
