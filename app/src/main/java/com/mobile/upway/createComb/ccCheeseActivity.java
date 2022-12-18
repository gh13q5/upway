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
import com.mobile.upway.dao.CheeseDAO;
import com.mobile.upway.dto.Cheese;

import java.util.ArrayList;

public class ccCheeseActivity extends Activity{

    static final String TAG = "ccCheeseActivity";

    RecyclerView recyclerView;
    ccCheeseAdapter cheeseAdapter;
    CheeseDAO cheeseDAO;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_cheese);
        intent = getIntent();

        View btn3 = (View)findViewById(R.id.cheese_bottom_square);
        Button cheeseToVege = (Button)btn3.findViewById(R.id.cm_btn_next);
        Button cheeseToBread = (Button)btn3.findViewById(R.id.cm_btn_before);

        recyclerView = findViewById(R.id.choice_cheese_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

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

    public void setRecyclerView(RecyclerView recyclerView) {
        cheeseDAO = new CheeseDAO();
        cheeseAdapter = new ccCheeseAdapter();

        cheeseDAO.getAllCheese(cheeseList -> {
            Log.d(TAG, "cheeseList 크기 : " + cheeseList.size());
            cheeseAdapter.setList((ArrayList<Cheese>) cheeseList);
            recyclerView.setAdapter(cheeseAdapter);
        });
    }

}
