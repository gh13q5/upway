package com.mobile.upway.createComb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobile.upway.R;
import com.mobile.upway.dto.Bread;

import java.util.ArrayList;

public class ccBreadActivity extends Activity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Bread> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private View view;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_bread);

        View btn2 = (View)findViewById(R.id.bread_bottom_square);
        Button breadToCheese = (Button)btn2.findViewById(R.id.cm_btn_next);
        Button breadToSand = (Button)btn2.findViewById(R.id.cm_btn_before);

        recyclerView = (RecyclerView) view.findViewById(R.id.choice_bread_list);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        breadToCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cheeseIntent = new Intent(getApplicationContext(),ccCheeseActivity.class);
                startActivity(cheeseIntent);
            }
        });

        breadToSand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sandIntent = new Intent(getApplicationContext(),ccSandwichActivity.class);
                startActivity(sandIntent);
            }
        });

    }

}
