package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dao.MenuDAO;
import com.mobile.upway.dto.Menu;

import java.util.ArrayList;

public class ccSandwichActivity extends Activity {

    static final String TAG = "ccBreadActivity";

    RecyclerView recyclerView;
    ccSandwichAdapter sandAdapter;
    MenuDAO menuDAO;
    Intent intent;
    ImageView imageView;

    boolean choice = false;
    String sandwich = "";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_sandwich);
        intent = getIntent();

        View btn1 = (View)findViewById(R.id.sandwich_bottom_square);
        Button sandToBread = (Button)btn1.findViewById(R.id.sw_btn_next);

        recyclerView = findViewById(R.id.choice_sand_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        imageView = findViewById(R.id.checkimage);

        sandAdapter.setOnItemClickListener(
                new ccSandwichAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(int pos, String data){
                        if(choice==false){
                            sandwich = data;
                            imageView.setVisibility(View.VISIBLE);
                            choice = true;
                        } else {
                            imageView.setVisibility(View.INVISIBLE);
                            choice = false;
                        }
                    }
                });

        sandToBread.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(choice==true){
                    Intent breadIntent = new Intent(getApplicationContext(),ccBreadActivity.class);
                    breadIntent.putExtra("sandwich", sandwich);
                    startActivity(breadIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "샌드위치를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        menuDAO = new MenuDAO();
        sandAdapter = new ccSandwichAdapter();

        menuDAO.getAllMenu(menuList -> {
            Log.d(TAG, "menuList 크기 : " + menuList.size());
            sandAdapter.setList((ArrayList<Menu>) menuList);
            recyclerView.setAdapter(sandAdapter);
        });
    }

}
