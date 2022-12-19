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

    String[] add = new String[3];
    int n=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_add);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        String[] vegetable = intent.getStringArrayExtra("vegetable");
        String[] sauce = intent.getStringArrayExtra("sauce");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");

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
                    public void onItemClicked(String data) {
                        if(n==0){
                            add[0] = data;
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText(add[0]);
                            n++;
                        } else if(n==1){
                            add[1] = data;
                            if(add[0]==add[1]){
                                Toast.makeText(getApplicationContext(), "이미 선택한 야채입니다.", Toast.LENGTH_SHORT).show();
                                add[1]=null;
                            } else{
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText(add[1]);
                                n++;
                            }
                        } else if(n==2){
                            add[2] = data;
                            if(add[0]==add[2] || add[1]==add[2] ){
                                Toast.makeText(getApplicationContext(), "이미 선택한 야채입니다.", Toast.LENGTH_SHORT).show();
                                add[2]=null;
                            } else {
                                textView3.setVisibility(View.VISIBLE);
                                textView3.setText(add[2]);
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
                        add[0] = add[1];
                        add[1] = add[2];
                        add[2] = null;
                        textView1.setText(add[0]);
                        textView2.setText(add[1]);
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.VISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        add[0] = add[1];
                        add[1] = null;
                        textView1.setText(add[0]);
                        textView2.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.INVISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        add[0] = null;
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
                        add[1] = add[2];
                        add[2] = null;
                        textView2.setText(add[1]);
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView3.getVisibility() == View.INVISIBLE){
                        add[1] = null;
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
                    add[2] = null;
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
                    list.add(add[i]);
                }
                listIntent.putExtra("sandwich", sandwich);
                listIntent.putExtra("bread", bread);
                listIntent.putExtra("cheese", cheese);
                listIntent.putExtra("vegetable", vegetable);
                listIntent.putExtra("sauce", sauce);
                listIntent.putExtra("add", add);
                listIntent.putExtra("list", list);
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
