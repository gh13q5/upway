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
import com.mobile.upway.dao.UserDAO;
import com.mobile.upway.dao.UserScrapDAO;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.User;
import com.mobile.upway.dto.User_scrap;

import java.util.ArrayList;
import java.util.List;

public class MyScrapCombListActivity extends AppCompatActivity {

    static final String TAG = "MyScrapCombListActivity";

    // LIST
    RecyclerView recyclerView;
    CombListAdapter adapter;

    // DAO
    CombinationDAO combDAO;
    UserScrapDAO userScrapDAO;

    // FIREBASE AUTH
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        // 리스트 연결
        recyclerView = findViewById(R.id.scrap_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        displayCombList(recyclerView);
    }

    public void displayCombList(RecyclerView recyclerView){
        combDAO = new CombinationDAO();
        userScrapDAO = new UserScrapDAO();
        adapter = new CombListAdapter();

        List<User_scrap> scrapList = new ArrayList<>();
        if(currentUser != null){
            Log.d(TAG, currentUser.getEmail());
            userScrapDAO.findUserScrapByUser(currentUser.getEmail(), combList -> {
                for(int i = 0; i < combList.size(); i++){
                    scrapList.add((User_scrap) combList.get(i));
                    Log.d(TAG, "scrapList 크기 : " + scrapList.size());
                }

                List<Combination> combinationList = new ArrayList<>();
                for(int i = 0; i < scrapList.size(); i++){
                    Log.d(TAG, "combinationList 크기 : " + combinationList.size());
                    combDAO.getCombById(scrapList.get(i).getComb().getId(), object -> {
                        combinationList.add((Combination) object);

                        adapter.setList((ArrayList<Combination>) combinationList);
                        recyclerView.setAdapter(adapter);
                    });
                }
            });
        }
    }
}
