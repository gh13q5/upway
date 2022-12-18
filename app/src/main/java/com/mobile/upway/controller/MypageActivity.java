package com.mobile.upway.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.UserDAO;
import com.mobile.upway.dto.User;

import org.w3c.dom.Text;

public class MypageActivity extends AppCompatActivity {
    static final String TAG = "MypageActivity";

    // FIREBASE
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    // USER
    User user;
    UserDAO userDAO;

    // UI
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        header = (TextView) findViewById(R.id.header_title);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            UserDAO userDAO = new UserDAO();
            userDAO.findUserByUid(currentUser.getUid(), object -> {
                user = (User) object;
                header.setText("안녕하세요! " + user.getNickname() + "님!");
            });
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_subway_btn:
                Intent myIntent = new Intent(this, MyCombListActivity.class);
                startActivity(myIntent);
                break;
            case R.id.my_scrap_btn:
                Intent scrapIntent = new Intent(this, MyScrapCombListActivity.class);
                startActivity(scrapIntent);
                break;
            case R.id.help_btn:
                Intent linkIntent = new Intent(Intent.ACTION_VIEW);
                linkIntent.setData(Uri.parse("https://www.subway.co.kr/faq"));
                startActivity(linkIntent);
                break;
            case R.id.withdraw_btn:
                if(currentUser != null){
                    userDAO = new UserDAO();
                    userDAO.deleteUser(currentUser.getUid(), new FireStoreCallback() {
                        @Override
                        public void onCallback(Object object) {
                            currentUser.delete();
                            Intent backIntent = new Intent(MypageActivity.this, MainActivity.class);
                            startActivity(backIntent);
                            finish();
                        }
                    });
                }
                break;
            case R.id.logout_btn:
                if(currentUser != null){
                    firebaseAuth.signOut();
                    Intent backIntent = new Intent(this, MainActivity.class);
                    startActivity(backIntent);
                    finish();
                }
                break;
        }
    }

}
