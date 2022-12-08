package com.mobile.upway.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.R;
import com.mobile.upway.dto.User;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.my_sub_btn:
                // 임시로 login 페이지랑 연결시켜놓음
                Intent myIntent = new Intent(this, LoginActivity.class);
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
}