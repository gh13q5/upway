package com.mobile.upway.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.R;
import com.mobile.upway.dao.CommentDAO;
import com.mobile.upway.dao.MenuDAO;
import com.mobile.upway.dao.UserDAO;
import com.mobile.upway.dto.Comment;
import com.mobile.upway.dto.Menu;
import com.mobile.upway.dto.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

public class MyDetailActivity extends AppCompatActivity {
    static final String TAG = "MyDetailActivity";

    RecyclerView reviewListView;
    ReviewListAdapter reviewAdapter;
    View commentView;
    View recipe;

    CommentDAO commentDAO;
    UserDAO userDAO;
    MenuDAO menuDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydetail);

        recipe = (View)findViewById(R.id.recipeTbl);
        View ingridList = (View)findViewById(R.id.ingridientsTbl);
        commentView = (View)findViewById(R.id.commentTbl);
        View reviewList = (View)findViewById(R.id.reviewTbl);
        //reviewList
        reviewListView = findViewById(R.id.rListView);
        reviewListView.setLayoutManager(new LinearLayoutManager(this.reviewListView.getContext(), RecyclerView.VERTICAL, false));
        displayReviewList(reviewListView);
        //comment
        TextView userName = (TextView)findViewById(R.id.commentUser);
        displayUserName("gh13q5@gmail.com",userName);
        //recipe
        ImageView menuImage = (ImageView)findViewById(R.id.menuimage);
        displayMenu("로티세리 바비큐 치킨",menuImage,this);
        TextView title = (TextView)findViewById(R.id.recipetitle);
        TextView kcalAndprice = (TextView)findViewById(R.id.kcalAndprice);
        title.setText("combination title");
        kcalAndprice.setText("combKcal"+"kcal / "+"combPrice"+"won");
        //ingredients

    }
    public void displayMenu(String menuName, ImageView menuImage, Context context){

        menuDAO = new MenuDAO();
        menuDAO.findMenuByName(menuName,(m->{
            Menu menu =(Menu) m;
                Glide.with(context)
                        .load(menu.getImgUrl())
                        .into(menuImage);

        }));


    }
    public void displayUserName(String userId, TextView userName){
        userDAO = new UserDAO();
        userDAO.findUserById(userId,(u -> {
            User user = (User) u;
            userName.setText(user.getNickname() + " 님의 코멘트");
        }));
    }

    public void displayReviewList(RecyclerView recyclerView){
        commentDAO = new CommentDAO();
        reviewAdapter = new ReviewListAdapter();
        commentDAO.findCommentsByComb(commentList ->{
            Log.d(TAG,"CommentListSize:"+commentList.size());
            reviewAdapter.setList((ArrayList<Comment>) commentList);
            reviewListView.setAdapter(reviewAdapter);

        },"W72zKqm90VITWs6FTL4t");
    }
}
