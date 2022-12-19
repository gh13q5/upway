package com.mobile.upway.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.mobile.upway.R;
import com.mobile.upway.createComb.ccCheeseActivity;
import com.mobile.upway.createComb.ccSandwichActivity;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dto.Combination;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    // LIST
    RecyclerView recyclerView;
    CombListAdapter adapter;

    // DAO
    CombinationDAO combDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리스트 연결
        recyclerView = findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        displayCombList(recyclerView);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_sub_btn:
                Intent myIntent = new Intent(this, ccSandwichActivity.class);
                startActivity(myIntent);
                break;
            case R.id.best_sub_btn:
                Intent bestIntent = new Intent(this, BestSubActivity.class);
                startActivity(bestIntent);
                break;
            case R.id.subway_link_btn:
                Intent linkIntent = new Intent(Intent.ACTION_VIEW);
                linkIntent.setData(Uri.parse("https://www.subway.co.kr/"));
                startActivity(linkIntent);
                break;
        }
    }

    public void displayCombList(RecyclerView recyclerView){
        combDAO = new CombinationDAO();
        adapter = new CombListAdapter();

        combDAO.get5CombListOrderByScraps(combList -> {
            Log.d(TAG, "combinationList 크기 : " + combList.size());
            adapter.setList((ArrayList<Combination>) combList);
            recyclerView.setAdapter(adapter);
        });
    }
}