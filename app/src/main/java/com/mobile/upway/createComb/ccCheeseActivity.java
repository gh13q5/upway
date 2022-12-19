package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    String cheese = "";
    double ckcal=0.0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_cheese);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
        final double[] kcal = {intent.getDoubleExtra("kcal", 0.0)};
        int price = intent.getIntExtra("price", 0);
        String url = intent.getStringExtra("url");

        View btn3 = (View)findViewById(R.id.cheese_bottom_square);
        Button cheeseToVege = (Button)btn3.findViewById(R.id.cm_btn_next);
        TextView textView = (TextView)btn3.findViewById(R.id.chosen1);

        recyclerView = findViewById(R.id.choice_cheese_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        cheeseAdapter.setOnItemClickListener(
                new ccCheeseAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(String data, double kcaldata) {
                        cheese = data;
                        ckcal = kcaldata;
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(cheese);
                    }
                });

        cheeseToVege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cheese.length()>1){
                    Intent vegeIntent = new Intent(getApplicationContext(),ccVegetableActivity.class);
                    list.add(cheese);
                    kcal[0]+=ckcal;
                    Log.d("현재 칼로리 : " , String.valueOf(kcal[0]));
                    vegeIntent.putExtra("sandwich", sandwich);
                    vegeIntent.putExtra("bread", bread);
                    vegeIntent.putExtra("cheese", cheese);
                    vegeIntent.putExtra("list", list);
                    vegeIntent.putExtra("kcal", kcal[0]);
                    vegeIntent.putExtra("price", price);
                    vegeIntent.putExtra("url", url);
                    startActivity(vegeIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "치즈를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
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
