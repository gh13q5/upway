package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dao.BreadDAO;
import com.mobile.upway.dto.Bread;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ccBreadActivity extends Activity{

    static final String TAG = "ccBreadActivity";

    RecyclerView recyclerView;
    ccBreadAdapter breadAdapter;
    BreadDAO breadDAO;
    Intent intent;

    String bread = "";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_bread);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");

        View btn2 = (View)findViewById(R.id.bread_bottom_square);
        Button breadToCheese = (Button)btn2.findViewById(R.id.cm_btn_next);
        TextView textView = (TextView)btn2.findViewById(R.id.chosen1);

        recyclerView = findViewById(R.id.choice_bread_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        breadAdapter.setOnItemClickListener(
                new ccBreadAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(String data) {
                        bread = data;
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(bread);
                    }
                });


        breadToCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bread.length()>1){
                    Intent cheeseIntent = new Intent(getApplicationContext(),ccCheeseActivity.class);
                    list.add(bread);
                    cheeseIntent.putExtra("sandwich", sandwich);
                    cheeseIntent.putExtra("bread",bread);
                    cheeseIntent.putExtra("list", list);
                    startActivity(cheeseIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "빵을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setRecyclerView(RecyclerView recyclerView) {
        breadDAO = new BreadDAO();
        breadAdapter = new ccBreadAdapter();

        breadDAO.getAllBread(breadList -> {
            Log.d(TAG, "breadList 크기 : " + breadList.size());
            breadAdapter.setList((ArrayList<Bread>) breadList);
            recyclerView.setAdapter(breadAdapter);
        });
    }

}
