package com.mobile.upway.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.upway.R;

public class BestSubActivity extends AppCompatActivity {

    static final String TAG = "BestSubActivity";

    // INTENT
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comb_list);

        intent = getIntent();
    }
}