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
import com.mobile.upway.dao.BreadDAO;
import com.mobile.upway.dto.Bread;

import java.util.ArrayList;

public class ccBreadActivity extends Activity{

    static final String TAG = "ccBreadActivity";

    RecyclerView recyclerView;
    ccBreadAdapter breadAdapter;
    BreadDAO breadDAO;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_bread);
        intent = getIntent();

        View btn2 = (View)findViewById(R.id.bread_bottom_square);
        Button breadToCheese = (Button)btn2.findViewById(R.id.cm_btn_next);
        Button breadToSand = (Button)btn2.findViewById(R.id.cm_btn_before);

        recyclerView = findViewById(R.id.choice_bread_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

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

    public void setRecyclerView(RecyclerView recyclerView) {
        breadDAO = new BreadDAO();
        breadAdapter = new ccBreadAdapter();

        breadDAO.getAllBread(breadList -> {
            Log.d(TAG, "breadList 크기 : " + breadList.size());
            breadAdapter.setList((ArrayList<Bread>) breadList);
            recyclerView.setAdapter(breadAdapter);
        });
    }

}
