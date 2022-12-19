package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.upway.R;
import com.mobile.upway.dao.OptionsDAO;
import com.mobile.upway.dto.Options;

import java.util.ArrayList;

public class ccListActivity extends Activity{

    static final String TAG = "ccListActivity";

    RecyclerView recyclerView;
    ccListAdapter listAdapter;
    Intent intent;
    Context context;

    ArrayList<String> list = new ArrayList<>();
    int n=0;

    String stitle;
    String scomment;
    String str;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_add);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        if (intent.hasExtra("vegetable")) {
            String[] vegetable = intent.getStringArrayExtra("vegetable");
        }
        if (intent.hasExtra("sauce")) {
            String[] sauce = intent.getStringArrayExtra("sauce");
        }
        if (intent.hasExtra("add")) {
            String[] add = intent.getStringArrayExtra("add");
        }
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");

        listAdapter = new ccListAdapter();
        context = getApplicationContext();

        Log.d(TAG, String.valueOf(list.size()));

        for(int i=0; i<list.size(); i++){
            str = String.valueOf(list.get(i));
            listAdapter.setList(str);
        }

        //View btn7 = (View)findViewById(R.id.list_bottom_square);

    }

    public void onStart(){

        Button endlast = (Button)findViewById(R.id.ad_btn_upload);

        EditText title = (EditText)findViewById(R.id.list_title);
        EditText comment = (EditText)findViewById(R.id.list_comment);

        recyclerView = findViewById(R.id.choice_all_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(listAdapter);

        endlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endIntent = new Intent(getApplicationContext(),ccVegetableActivity.class);
                stitle = String.valueOf(title.getText());
                scomment = String.valueOf(comment.getText());
                startActivity(endIntent);
            }
        });

        super.onStart();
    }


}
