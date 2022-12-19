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

    ArrayList<String> vegetable = new ArrayList<>();
    double[] ckcal = new double[3];
    String cs="";
    int n=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_vegetable);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
        final double[] kcal = {intent.getDoubleExtra("kcal", 0.0)};
        int price = intent.getIntExtra("price", 0);
        String url = intent.getStringExtra("url");

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
                    public void onItemClicked(String data, double kcaldata) {
                        if(n==0){
                            vegetable.add(data);
                            ckcal[0] = kcaldata;
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText("-"+data);
                            n++;
                        } else if(n==1){
                            if(vegetable.contains(data)){
                                Toast.makeText(getApplicationContext(), "이미 선택한 야채입니다.", Toast.LENGTH_SHORT).show();
                            } else{
                                vegetable.add(data);
                                ckcal[1] = kcaldata;
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText("-"+data);
                                n++;
                            }
                        } else if(n==2){
                            if(vegetable.contains(data)){
                                Toast.makeText(getApplicationContext(), "이미 선택한 야채입니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                vegetable.add(data);
                                ckcal[2] = kcaldata;
                                textView3.setVisibility(View.VISIBLE);
                                textView3.setText("-"+data);
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
                        vegetable.add(0, vegetable.get(1));
                        vegetable.add(1, vegetable.get(2));
                        vegetable.remove(2);
                        ckcal[0] = ckcal[1];
                        ckcal[1] = ckcal[2];
                        ckcal[2] = 0.0;
                        textView1.setText(vegetable.get(0));
                        textView2.setText(vegetable.get(1));
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.VISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        vegetable.add(0, vegetable.get(1));
                        vegetable.remove(1);
                        ckcal[0] = ckcal[1];
                        ckcal[1] = 0.0;
                        textView1.setText(vegetable.get(0));
                        textView2.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.INVISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        vegetable.remove(0);
                        ckcal[0] = 0.0;
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
                        vegetable.add(1, vegetable.get(2));
                        vegetable.remove(2);
                        ckcal[1] = ckcal[2];
                        ckcal[2] = 0.0;
                        textView2.setText(vegetable.get(1));
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView3.getVisibility() == View.INVISIBLE){
                        vegetable.remove(1);
                        ckcal[1] = 0.0;
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
                    vegetable.remove(2);
                    ckcal[2] = 0.0;
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
                    list.add("-"+vegetable.get(i));
                    kcal[0]+=ckcal[i];
                }
                sauceIntent.putExtra("sandwich", sandwich);
                sauceIntent.putExtra("bread", bread);
                sauceIntent.putExtra("cheese", cheese);
                sauceIntent.putExtra("vegetable", vegetable);
                sauceIntent.putExtra("list", list);
                sauceIntent.putExtra("kcal", kcal[0]);
                sauceIntent.putExtra("price", price);
                sauceIntent.putExtra("url", url);
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
