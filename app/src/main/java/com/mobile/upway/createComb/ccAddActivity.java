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
import com.mobile.upway.dao.OptionsDAO;
import com.mobile.upway.dto.Options;

import java.util.ArrayList;

public class ccAddActivity extends Activity{

    static final String TAG = "ccAddActivity";

    RecyclerView recyclerView;
    ccAddAdapter addAdapter;
    OptionsDAO optionsDAO;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_add);
        intent = getIntent();

        View btn6 = (View)findViewById(R.id.add_bottom_square);
        Button addToSauce = (Button)btn6.findViewById(R.id.ad_btn_before);
        Button endSave = (Button)btn6.findViewById(R.id.ad_btn_end);

        recyclerView = findViewById(R.id.choice_add_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

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

    public void setRecyclerView(RecyclerView recyclerView) {
        optionsDAO = new OptionsDAO();
        addAdapter = new ccAddAdapter();

        optionsDAO.getAllOptions(optionsList -> {
            Log.d(TAG, "optionsList 크기 : " + optionsList.size());
            addAdapter.setList((ArrayList<Options>) optionsList);
            recyclerView.setAdapter(addAdapter);
        });
    }
}
