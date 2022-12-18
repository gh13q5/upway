package com.mobile.upway.controller;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dao.UserScrapDAO;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.User_scrap;

import java.util.ArrayList;
import java.util.List;

public class MyCombListActivity extends AppCompatActivity {
    static final String TAG = "MyCombListActivity";

    // LIST
    RecyclerView recyclerView;
    CombListViewsAdapter adapter;

    // DAO
    CombinationDAO combDAO;

    // FIREBASE AUTH
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        // 리스트 연결
        recyclerView = findViewById(R.id.my_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        displayCombList(recyclerView);
    }

    public void displayCombList(RecyclerView recyclerView){
        combDAO = new CombinationDAO();
        adapter = new CombListViewsAdapter();

        if (currentUser != null) {
            combDAO.getCombListByUser(currentUser.getEmail(), combList -> {
                Log.d(TAG, "combinationList 크기 : " + combList.size());
                adapter.setList((ArrayList<Combination>) combList);
                recyclerView.setAdapter(adapter);
            });
        }
    }
}
