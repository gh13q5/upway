package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dao.VegetableDAO;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;

public class ccVegetableActivity extends Activity{

    static final String TAG = "ccVegetableActivity";

    RecyclerView recyclerView;
    ccVegetableAdapter vegetableAdapter;
    VegetableDAO vegetableDAO;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_vegetable);
        intent = getIntent();

        View btn4 = (View)findViewById(R.id.vegetable_bottom_square);
        Button vegeToSauce = (Button)btn4.findViewById(R.id.cm_btn_next);
        Button vegeToCheese = (Button)btn4.findViewById(R.id.cm_btn_before);

        recyclerView = findViewById(R.id.choice_vegetable_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

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

    public void setRecyclerView(RecyclerView recyclerView) {
        vegetableDAO = new VegetableDAO();
        vegetableAdapter = new ccVegetableAdapter();

        vegetableDAO.getAllVegetable(vegetableList -> {
            Log.d(TAG, "vegetableList 크기 : " + vegetableList.size());
            vegetableAdapter.setList((ArrayList<Vegetable>) vegetableList);
            recyclerView.setAdapter(vegetableAdapter);
        });
    }
}
