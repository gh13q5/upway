package com.mobile.upway.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.upway.R;

public class SelectLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_form_btn:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.regist_btn:
                Intent registIntent = new Intent(this, RegistActivity.class);
                startActivity(registIntent);
                break;
        }
    }
}
