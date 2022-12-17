package com.mobile.upway.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dto.Combination;

import java.util.ArrayList;

public class BestSubActivity extends AppCompatActivity {

    static final String TAG = "BestSubActivity";

    // LIST
    RecyclerView recyclerView;
    CombListAdapter adapter;

    // DAO
    CombinationDAO combDAO;

    // INTENT
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comb_list);

        intent = getIntent();

        // 리스트 연결
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        displayCombList(recyclerView);
    }

    public void displayCombList(RecyclerView recyclerView){
        combDAO = new CombinationDAO();
        adapter = new CombListAdapter();

        combDAO.getAllComb(combList -> {
            Log.d(TAG, "combinationList 크기 : " + combList.size());
            adapter.setList((ArrayList<Combination>) combList);
            recyclerView.setAdapter(adapter);
        });
    }
}