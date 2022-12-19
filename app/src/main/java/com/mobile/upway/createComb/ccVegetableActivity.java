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
import com.mobile.upway.dao.VegetableDAO;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;

public class ccVegetableActivity extends Activity{

    static final String TAG = "ccVegetableActivity";

    RecyclerView recyclerView;
    ccVegetableAdapter vegetableAdapter;
    VegetableDAO vegetableDAO;
    Intent intent;

    String[] vegetable = new String[3];
    int n=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_vegetable);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");

        View btn4 = (View)findViewById(R.id.vegetable_bottom_square);
        Button vegeToSauce = (Button)btn4.findViewById(R.id.cm_btn_next);
        TextView textView1 = (TextView)btn4.findViewById(R.id.chosen1);
        TextView textView2 = (TextView)btn4.findViewById(R.id.chosen2);
        TextView textView3 = (TextView)btn4.findViewById(R.id.chosen3);

        recyclerView = findViewById(R.id.choice_vegetable_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        vegetableAdapter.setOnItemClickListener(
                new ccVegetableAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(String data) {
                        if(n==0){
                            vegetable[0] = data;
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText("-"+vegetable[0]);
                            n++;
                        } else if(n==1){
                            vegetable[1] = data;
                            if(vegetable[0]==vegetable[1]){
                                Toast.makeText(getApplicationContext(), "이미 선택한 야채입니다.", Toast.LENGTH_SHORT).show();
                                vegetable[1]=null;
                            } else{
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText("-"+vegetable[1]);
                                n++;
                            }
                        } else if(n==2){
                            vegetable[2] = data;
                            if(vegetable[0]==vegetable[2] || vegetable[1]==vegetable[2] ){
                                Toast.makeText(getApplicationContext(), "이미 선택한 야채입니다.", Toast.LENGTH_SHORT).show();
                                vegetable[2]=null;
                            } else {
                                textView3.setVisibility(View.VISIBLE);
                                textView3.setText("-" + vegetable[2]);
                                n++;
                            }
                        } else if(n==3){
                            Toast.makeText(getApplicationContext(), "최대 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView1.getVisibility() == View.VISIBLE){
                    if(textView2.getVisibility() == View.VISIBLE && textView3.getVisibility() == View.VISIBLE){
                        vegetable[0] = vegetable[1];
                        vegetable[1] = vegetable[2];
                        vegetable[2] = null;
                        textView1.setText("-"+vegetable[0]);
                        textView2.setText("-"+vegetable[1]);
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.VISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        vegetable[0] = vegetable[1];
                        vegetable[1] = null;
                        textView1.setText("-"+vegetable[0]);
                        textView2.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.INVISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        vegetable[0] = null;
                        textView1.setVisibility(View.INVISIBLE);
                        n--;
                    }
                }
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView2.getVisibility() == View.VISIBLE){
                    if(textView3.getVisibility() == View.VISIBLE){
                        vegetable[1] = vegetable[2];
                        vegetable[2] = null;
                        textView2.setText("-"+vegetable[1]);
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView3.getVisibility() == View.INVISIBLE){
                        vegetable[1] = null;
                        textView2.setVisibility(View.INVISIBLE);
                        n--;
                    }
                }
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView3.getVisibility() == View.VISIBLE){
                    vegetable[2] = null;
                    textView3.setVisibility(View.INVISIBLE);
                    n--;
                }
            }
        });

        vegeToSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sauceIntent = new Intent(getApplicationContext(),ccSauceActivity.class);
                for(int i=0; i<n; i++){
                    list.add(vegetable[i]);
                }
                sauceIntent.putExtra("sandwich", sandwich);
                sauceIntent.putExtra("bread", bread);
                sauceIntent.putExtra("cheese", cheese);
                sauceIntent.putExtra("vegetable", vegetable);
                sauceIntent.putExtra("list", list);
                startActivity(sauceIntent);
            }
        });

    }

    public void setRecyclerView(RecyclerView recyclerView) {
        vegetableDAO = new VegetableDAO();
        vegetableAdapter = new ccVegetableAdapter();

        vegetableDAO.getAllVegetable(vegetableList -> {
            Log.d(TAG, "vegetableList 크기 : " + vegetableList.size());
            vegetableAdapter.setList((ArrayList<Vegetable>) vegetableList);
            recyclerView.setAdapter(vegetableAdapter);
        });
    }
}
