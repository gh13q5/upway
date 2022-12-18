package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dao.SauceDAO;
import com.mobile.upway.dao.VegetableDAO;
import com.mobile.upway.dto.Sauce;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;

public class ccSauceActivity extends Activity{

    static final String TAG = "ccSauceActivity";

    RecyclerView recyclerView;
    ccSauceAdapter sauceAdapter;
    SauceDAO sauceDAO;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_sauce);
        intent = getIntent();

        View btn5 = (View)findViewById(R.id.sauce_bottom_square);
        Button sauceToAdd = (Button)btn5.findViewById(R.id.cm_btn_next);
        Button sauceToVege = (Button)btn5.findViewById(R.id.cm_btn_before);

        recyclerView = findViewById(R.id.choice_sauce_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

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

    public void setRecyclerView(RecyclerView recyclerView) {
        sauceDAO = new SauceDAO();
        sauceAdapter = new ccSauceAdapter();

        sauceDAO.getAllSauce(sauceList -> {
            Log.d(TAG, "sauceList 크기 : " + sauceList.size());
            sauceAdapter.setList((ArrayList<Sauce>) sauceList);
            recyclerView.setAdapter(sauceAdapter);
        });
    }
}
