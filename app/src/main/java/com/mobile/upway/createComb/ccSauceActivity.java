package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;

public class ccSauceActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_sauce);

        View btn5 = (View)findViewById(R.id.sauce_bottom_square);
        Button sauceToAdd = (Button)btn5.findViewById(R.id.cm_btn_next);
        Button sauceToVege = (Button)btn5.findViewById(R.id.cm_btn_before);

        sauceToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(getApplicationContext(),ccAddActivity.class);
                startActivity(addIntent);
            }
        });

        sauceToVege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vegeIntent = new Intent(getApplicationContext(),ccVegetableActivity.class);
                startActivity(vegeIntent);
            }
        });

    }

}
