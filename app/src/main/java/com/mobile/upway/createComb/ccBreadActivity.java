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
    int ckcal=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_bread);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
        final double[] kcal = {intent.getDoubleExtra("kcal", 0.0)};
        int price = intent.getIntExtra("price", 0);
        String url = intent.getStringExtra("url");

        View btn2 = (View)findViewById(R.id.bread_bottom_square);
        Button breadToCheese = (Button)btn2.findViewById(R.id.cm_btn_next);
        TextView textView = (TextView)btn2.findViewById(R.id.chosen1);

        recyclerView = findViewById(R.id.choice_bread_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        breadAdapter.setOnItemClickListener(
                new ccBreadAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(String data, int kcaldata) {
                        bread = data;
                        ckcal = kcaldata;
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
                    kcal[0]+=Double.valueOf(ckcal);
                    Log.d("현재 칼로리 : " , String.valueOf(kcal[0]));
                    cheeseIntent.putExtra("sandwich", sandwich);
                    cheeseIntent.putExtra("bread",bread);
                    cheeseIntent.putExtra("list", list);
                    cheeseIntent.putExtra("kcal", kcal[0]);
                    cheeseIntent.putExtra("price", price);
                    cheeseIntent.putExtra("url", url);
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
