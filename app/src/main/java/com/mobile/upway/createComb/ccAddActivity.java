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
import com.mobile.upway.dao.OptionsDAO;
import com.mobile.upway.dto.Options;

import java.util.ArrayList;

public class ccAddActivity extends Activity{

    static final String TAG = "ccAddActivity";

    RecyclerView recyclerView;
    ccAddAdapter addAdapter;
    OptionsDAO optionsDAO;
    Intent intent;

    ArrayList<String> add = new ArrayList<>();
    int[] ckcal = new int[3];
    int[] cprice = new int[3];
    int n=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_add);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        ArrayList<String> vegetable = (ArrayList<String>) intent.getSerializableExtra("vegetable");
        ArrayList<String> sauce = (ArrayList<String>) intent.getSerializableExtra("sauce");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
        final double[] kcal = {intent.getDoubleExtra("kcal", 0.0)};
        final int[] price = {intent.getIntExtra("price", 0)};
        String url = intent.getStringExtra("url");

        //View btn6 = (View)findViewById(R.id.add_bottom_square);
        Button endSave = findViewById(R.id.ad_btn_end);
        TextView textView1 = (TextView)findViewById(R.id.chosen1);
        TextView textView2 = (TextView)findViewById(R.id.chosen2);
        TextView textView3 = (TextView)findViewById(R.id.chosen3);

        recyclerView = findViewById(R.id.choice_add_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        addAdapter.setOnItemClickListener(
                new ccAddAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(String data, int kcaldata, int pricedata) {
                        if(n==0){
                            add.add(data);
                            ckcal[0] = kcaldata;
                            cprice[0] = pricedata;
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText(data);
                            n++;
                        } else if(n==1){
                            if(add.contains(data)){
                                Toast.makeText(getApplicationContext(), "이미 선택한 추가재료입니다.", Toast.LENGTH_SHORT).show();
                            } else{
                                add.add(data);
                                ckcal[1] = kcaldata;
                                cprice[1] = pricedata;
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText(data);
                                n++;
                            }
                        } else if(n==2){
                            if(add.contains(data)){
                                Toast.makeText(getApplicationContext(), "이미 선택한 추가재료입니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                add.add(data);
                                ckcal[2] = kcaldata;
                                cprice[2] = pricedata;
                                textView3.setVisibility(View.VISIBLE);
                                textView3.setText(data);
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
                        add.add(0, add.get(1));
                        add.add(1, add.get(2));
                        add.remove(2);
                        ckcal[0] = ckcal[1];
                        ckcal[1] = ckcal[2];
                        ckcal[2] = 0;
                        cprice[0] = cprice[1];
                        cprice[1] = cprice[2];
                        cprice[2] = 0;
                        textView1.setText(add.get(0));
                        textView2.setText(add.get(1));
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.VISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        add.add(0, add.get(1));
                        add.remove(1);
                        ckcal[0] = ckcal[1];
                        ckcal[1] = 0;
                        cprice[0] = cprice[1];
                        cprice[1] = 0;
                        textView1.setText(add.get(0));
                        textView2.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.INVISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        add.remove(0);
                        ckcal[0] = 0;
                        cprice[0] = 0;
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
                        add.add(1, add.get(2));
                        add.remove(2);
                        ckcal[1] = ckcal[2];
                        ckcal[2] = 0;
                        cprice[1] = cprice[2];
                        cprice[2] = 0;
                        textView2.setText(add.get(1));
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView3.getVisibility() == View.INVISIBLE){
                        add.remove(1);
                        ckcal[1] = 0;
                        cprice[1] = 0;
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
                    add.remove(2);
                    ckcal[2] = 0;
                    cprice[2] = 0;
                    textView3.setVisibility(View.INVISIBLE);
                    n--;
                }
            }
        });

        endSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getApplicationContext(),ccListActivity.class);
                for(int i=0; i<n; i++){
                    list.add(add.get(i));
                    kcal[0]+=Double.valueOf(ckcal[i]);
                    price[0] +=Double.valueOf(cprice[i]);
                }
                listIntent.putExtra("sandwich", sandwich);
                listIntent.putExtra("bread", bread);
                listIntent.putExtra("cheese", cheese);
                listIntent.putExtra("vegetable", vegetable);
                listIntent.putExtra("sauce", sauce);
                listIntent.putExtra("add", add);
                listIntent.putExtra("list", list);
                listIntent.putExtra("kcal", kcal[0]);
                listIntent.putExtra("price", price[0]);
                listIntent.putExtra("url", url);
                startActivity(listIntent);
            }
        });
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        optionsDAO = new OptionsDAO();
        addAdapter = new ccAddAdapter();

        optionsDAO.getAllOptions(optionsList -> {
            Log.d(TAG, "optionsList 크기 : " + optionsList.size());
            addAdapter.setList((ArrayList<Options>) optionsList);
            recyclerView.setAdapter(addAdapter);
        });
    }
}
