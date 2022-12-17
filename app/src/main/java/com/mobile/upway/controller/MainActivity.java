package com.mobile.upway.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mobile.upway.R;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dto.Combination;

import java.util.ArrayList;

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
                // 임시로 login 페이지랑 연결시켜놓음
                Intent myIntent = new Intent(this, SelectLoginActivity.class);
                startActivity(myIntent);
                break;
            case R.id.best_sub_btn:
                Intent bestIntent = new Intent(this, BestSubActivity.class);
                startActivity(bestIntent);
                break;
            case R.id.subway_link_btn:
                break;
        }
    }

    public void displayCombList(RecyclerView recyclerView){
/*
        combDAO = new CombinationDAO();
        combDAO.getAllComb(recyclerView);


 */
    }
}