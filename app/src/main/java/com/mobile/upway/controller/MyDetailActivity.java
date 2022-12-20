package com.mobile.upway.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.BreadDAO;
import com.mobile.upway.dao.CheeseDAO;
import com.mobile.upway.dao.CommentDAO;
import com.mobile.upway.dao.MenuDAO;
import com.mobile.upway.dao.OptionsDAO;
import com.mobile.upway.dao.SauceDAO;
import com.mobile.upway.dao.UserDAO;
import com.mobile.upway.dao.VegetableDAO;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Cheese;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Comment;
import com.mobile.upway.dto.Options;
import com.mobile.upway.dto.Sauce;
import com.mobile.upway.dto.User;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;
import java.util.List;

public class MyDetailActivity extends AppCompatActivity {
    static final String TAG = "MyDetailActivity";

    RecyclerView reviewListView;
    RecyclerView vegeGridView;
    RecyclerView sauceListView;
    RecyclerView optionListView;
    ReviewListAdapter reviewAdapter;
    VegeGridAdapter vegeGridAdapter;
    SauceAdapter sauceAdapter;
    OptionAdapter optionAdapter;

    View commentView;
    View recipe;
    View ingridients;

    CommentDAO commentDAO;
    UserDAO userDAO;
    MenuDAO menuDAO;
    BreadDAO breadDAO;
    CheeseDAO cheeseDAO;
    VegetableDAO vegetableDAO;
    SauceDAO sauceDAO;
    OptionsDAO optionsDAO;

    // FIREBASE AUTH
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydetail);

        Intent intent = getIntent();
        Combination combination = (Combination)intent.getSerializableExtra("combination");

        recipe = (View)findViewById(R.id.recipeTbl);
        ingridients = (View)findViewById(R.id.vegeTbl);
        commentView = (View)findViewById(R.id.commentTbl);
        View reviewList = (View)findViewById(R.id.reviewTbl);
        //reviewList
        reviewListView = findViewById(R.id.rListView);
        reviewListView.setLayoutManager(new LinearLayoutManager(this.reviewListView.getContext(), RecyclerView.VERTICAL, false));
        displayReviewList(reviewListView,combination.getId());
        //comment
        TextView userName = (TextView)findViewById(R.id.commentUser);
        displayUserName(combination.getUser().getUserId(),userName);
        TextView commentContent = (TextView)findViewById(R.id.commentbox);
        commentContent.setText(combination.getDescription());
        //recipe
        Button scraps = (Button)findViewById(R.id.scrapButton);
        scraps.setText("+"+combination.getScraps());
        ImageView menuImage = (ImageView)findViewById(R.id.menuimage);
        Glide.with(this)
                .load(combination.getImgUrl())
                .into(menuImage);
        TextView title = (TextView)findViewById(R.id.recipetitle);
        TextView menuName = (TextView)findViewById(R.id.menu_name);
        menuName.setText(combination.getMenu().getName());
        TextView kcalAndprice = (TextView)findViewById(R.id.kcalAndprice);
        title.setText(combination.getTitle());
        kcalAndprice.setText((int)combination.getKcal()+"kcal / "+ combination.getPrice()+"won");
        //ingredients
        //bread
        ImageView breadImageView = (ImageView) findViewById(R.id.breadImg);
        TextView breadNameView = (TextView)findViewById(R.id.breadName);
        breadNameView.setText(combination.getBread().getName());
        displayBread(combination.getBread().getName(),breadImageView,this);
        //cheese
        ImageView cheeseImageView = (ImageView) findViewById(R.id.cheeseImg);
        TextView cheeseNameView = (TextView) findViewById(R.id.cheeseName);
        cheeseNameView.setText(combination.getCheese().getName());
        displayCheese(combination.getCheese().getName(),cheeseImageView,this);

        //vege
        vegeGridView = findViewById(R.id.vegeList);
        vegeGridView.setLayoutManager(new GridLayoutManager(this.vegeGridView.getContext(),3));
        displayVege(combination.getVegetableList(),vegeGridView);
        //sauce
        sauceListView = findViewById(R.id.sauceList);
        sauceListView.setLayoutManager(new LinearLayoutManager(this.sauceListView.getContext(),RecyclerView.HORIZONTAL,false));
        displaySauce(combination.getSauceList(),sauceListView);
        //options
        optionListView = findViewById(R.id.optionList);
        optionListView.setLayoutManager(new LinearLayoutManager(this.optionListView.getContext(),RecyclerView.HORIZONTAL,false));
        displayOption(combination.getOptionsList(),optionListView);

        //writeComment
        Button submit = findViewById(R.id.reviewWritebtn);
        EditText editText = findViewById(R.id.reviewTextForm);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                String comb = combination.getId();
                writeComment(comb,text);
                editText.setText(null);
                editText.clearFocus();
            }
        });




    }
    public void writeComment(String comb, String editText){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser!=null){
            userDAO = new UserDAO();
            commentDAO = new CommentDAO();
            Log.d(TAG, currentUser.getEmail());
            userDAO.findUserByUid(currentUser.getUid(),u->{
                User user = (User) u;
                commentDAO.writeComment(new Comment(user.getNickname(),comb,editText),(Object o) ->displayReviewList(reviewListView,comb));
            });
        }
        else{
            Toast.makeText(this.getApplicationContext(), "로그인이 필요한 기능입니다.", Toast.LENGTH_SHORT).show();
        }
    }
    public void displayBread(String breadMenu, ImageView breadImage, Context context){

        breadDAO = new BreadDAO();
        breadDAO.findBreadByName(breadMenu,(b->{
            Bread bread =(Bread) b;
            Glide.with(context)
                    .load(bread.getImgUrl())
                    .into(breadImage);
        }));
    }
    public void displayCheese(String cheeseMenu, ImageView cheeseImage, Context context){

        cheeseDAO = new CheeseDAO();
        cheeseDAO.findCheeseByName(cheeseMenu,(c->{
            Cheese cheese =(Cheese) c;
            Glide.with(context)
                    .load(cheese.getImgUrl())
                    .into(cheeseImage);
        }));
    }
    public void displayVege(List<Vegetable> vegetableList, RecyclerView recyclerView){
        if(vegetableList.size()>0){
            vegetableDAO = new VegetableDAO();
            vegeGridAdapter = new VegeGridAdapter();
            vegetableDAO.findVegetableByArrayList(vList->{
                Log.d(TAG,"CommentListSize:"+vList.size());
                vegeGridAdapter.setList((ArrayList<Vegetable>) vList);
                vegeGridView.setAdapter(vegeGridAdapter);
            },vegetableList);
        }
    }
    public void displaySauce(List<Sauce> sauceList, RecyclerView recyclerView){
        sauceDAO = new SauceDAO();
        sauceAdapter = new SauceAdapter();
        sauceDAO.findSauceByArrayList(sList->{
            Log.d(TAG,"sauceListSize:"+sList.size());
            sauceAdapter.setList((ArrayList<Sauce>) sList);
            sauceListView.setAdapter(sauceAdapter);
        },sauceList);
    }
    public void displayOption(List<Options> optionList, RecyclerView recyclerView){
        optionsDAO = new OptionsDAO();
        optionAdapter = new OptionAdapter();
        if(optionList.size()>0){
            optionsDAO.findOptionsByArrayList(oList->{
                Log.d(TAG,"sauceListSize:"+oList.size());
                optionAdapter.setList((ArrayList<Options>) oList);
                optionListView.setAdapter(optionAdapter);
            },optionList);
        }
    }
    public void displayUserName(String userId, TextView userName){
        userDAO = new UserDAO();
        userDAO.findUserById(userId,(u -> {
            User user = (User) u;
            userName.setText(user.getNickname() + " 님의 코멘트");
        }));
    }

    public void displayReviewList(RecyclerView recyclerView, String combinationId){
        commentDAO = new CommentDAO();
        reviewAdapter = new ReviewListAdapter();
        commentDAO.findCommentsByComb(commentList ->{
            Log.d(TAG,"CommentListSize:"+commentList.size());
            reviewAdapter.setList((ArrayList<Comment>) commentList);
            reviewListView.setAdapter(reviewAdapter);

        },combinationId);
    }
}
