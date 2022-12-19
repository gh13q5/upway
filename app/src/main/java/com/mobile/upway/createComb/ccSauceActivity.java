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
import com.mobile.upway.dao.SauceDAO;
import com.mobile.upway.dto.Sauce;

import java.util.ArrayList;

public class ccSauceActivity extends Activity{

    static final String TAG = "ccSauceActivity";

    RecyclerView recyclerView;
    ccSauceAdapter sauceAdapter;
    SauceDAO sauceDAO;
    Intent intent;

    ArrayList<String> sauce = new ArrayList<>();
    double[] ckcal = new double[3];
    int n=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_sauce);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        ArrayList<String> vegetable = (ArrayList<String>) intent.getSerializableExtra("vegetable");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
        final double[] kcal = {intent.getDoubleExtra("kcal", 0.0)};
        int price = intent.getIntExtra("price", 0);
        String url = intent.getStringExtra("url");

        View btn5 = (View)findViewById(R.id.sauce_bottom_square);
        Button sauceToAdd = (Button)btn5.findViewById(R.id.cm_btn_next);
        TextView textView1 = (TextView)btn5.findViewById(R.id.chosen1);
        TextView textView2 = (TextView)btn5.findViewById(R.id.chosen2);
        TextView textView3 = (TextView)btn5.findViewById(R.id.chosen3);

        recyclerView = findViewById(R.id.choice_sauce_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        setRecyclerView(recyclerView);

        sauceAdapter.setOnItemClickListener(
                new ccSauceAdapter.onItemClickListener(){
                    @Override
                    public void onItemClicked(String data, double kcaldata) {
                        if(n==0){
                            sauce.add(data);
                            ckcal[0] = kcaldata;
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText(data);
                            n++;
                        } else if(n==1){
                            if(sauce.contains(data)){
                                Toast.makeText(getApplicationContext(), "이미 선택한 소스입니다.", Toast.LENGTH_SHORT).show();
                            } else{
                                sauce.add(data);
                                ckcal[1] = kcaldata;
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText(data);
                                n++;
                            }
                        } else if(n==2){
                            if(sauce.contains(data)){
                                Toast.makeText(getApplicationContext(), "이미 선택한 소스입니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                sauce.add(data);
                                ckcal[2] = kcaldata;
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
                        sauce.add(0, sauce.get(1));
                        sauce.add(1, sauce.get(2));
                        sauce.remove(2);
                        ckcal[0] = ckcal[1];
                        ckcal[1] = ckcal[2];
                        ckcal[2] = 0.0;
                        textView1.setText(sauce.get(0));
                        textView2.setText(sauce.get(1));
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.VISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        sauce.add(0, sauce.get(1));
                        sauce.remove(1);
                        ckcal[0] = ckcal[1];
                        ckcal[1] = 0.0;
                        textView1.setText(sauce.get(0));
                        textView2.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView2.getVisibility() == View.INVISIBLE && textView3.getVisibility() == View.INVISIBLE){
                        sauce.remove(0);
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
                        sauce.add(1, sauce.get(2));
                        sauce.remove(2);
                        ckcal[1] = ckcal[2];
                        ckcal[2] = 0.0;
                        textView2.setText(sauce.get(1));
                        textView3.setVisibility(View.INVISIBLE);
                        n--;
                    } else if(textView3.getVisibility() == View.INVISIBLE){
                        sauce.remove(1);
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
                    sauce.remove(2);
                    ckcal[2] = 0.0;
                    textView3.setVisibility(View.INVISIBLE);
                    n--;
                }
            }
        });

        sauceToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(getApplicationContext(),ccAddActivity.class);
                for(int i=0; i<n; i++){
                    list.add(sauce.get(i));
                    kcal[0]+=ckcal[i];
                }
                addIntent.putExtra("sandwich", sandwich);
                addIntent.putExtra("bread", bread);
                addIntent.putExtra("cheese", cheese);
                addIntent.putExtra("vegetable", vegetable);
                addIntent.putExtra("sauce", sauce);
                addIntent.putExtra("list", list);
                addIntent.putExtra("kcal", kcal[0]);
                addIntent.putExtra("price", price);
                addIntent.putExtra("url", url);
                startActivity(addIntent);
            }
        });

    }

    public void setRecyclerView(RecyclerView recyclerView) {
        sauceDAO = new SauceDAO();
        sauceAdapter = new ccSauceAdapter();

        sauceDAO.getAllSauce(sauceList -> {
            Log.d(TAG, "sauceList 크기 : " + sauceList.size());
            sauceAdapter.setList((ArrayList<Sauce>) sauceList);
            recyclerView.setAdapter(sauceAdapter);
        });
    }
}
