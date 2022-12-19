package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobile.upway.R;
import com.mobile.upway.controller.MyCombListActivity;
import com.mobile.upway.controller.MyScrapCombListActivity;
import com.mobile.upway.dao.BreadDAO;
import com.mobile.upway.dao.CheeseDAO;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dao.MenuDAO;
import com.mobile.upway.dao.OptionsDAO;
import com.mobile.upway.dao.SauceDAO;
import com.mobile.upway.dao.VegetableDAO;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Menu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ccListActivity extends Activity{

    static final String TAG = "ccListActivity";

    FirebaseAuth firebaseAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    RecyclerView recyclerView;
    ccListAdapter listAdapter;
    Intent intent;
    Context context;

    String stitle;
    String scomment;
    String str;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_list);
        intent = getIntent();
        String sandwich = intent.getStringExtra("sandwich");
        String bread = intent.getStringExtra("bread");
        String cheese = intent.getStringExtra("cheese");
        ArrayList<String> vegetable = (ArrayList<String>) intent.getSerializableExtra("vegetable");
        ArrayList<String> sauce = (ArrayList<String>) intent.getSerializableExtra("sauce");
        ArrayList<String> add = (ArrayList<String>) intent.getSerializableExtra("add");
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
        double kcal = intent.getDoubleExtra("kcal", 0.0);
        int price = intent.getIntExtra("price", 0);
        String url = intent.getStringExtra("url");

        String[] arrayVegetable = new String[vegetable.size()];
        for (int i=0; i<vegetable.size(); i++){
            arrayVegetable[i] = vegetable.get(i);
        }
        String[] arraySauce = new String[sauce.size()];
        for (int i=0; i<sauce.size(); i++){
            arraySauce[i] = sauce.get(i);
        }
        String[] arrayAdd = new String[add.size()];
        for (int i=0; i<add.size(); i++){
            arrayAdd[i] = add.get(i);
        }

        Button endlast = (Button)findViewById(R.id.ad_btn_upload);

        EditText title = (EditText)findViewById(R.id.list_title);
        EditText comment = (EditText)findViewById(R.id.list_comment);

        TextView textView = (TextView)findViewById(R.id.sandwich_price);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        //String email = "winflok943@gmail.com";

        listAdapter = new ccListAdapter();
        context = getApplicationContext();

        DecimalFormat df = new DecimalFormat("0.0");
        String skcal = df.format(kcal);
        textView.setText(skcal + "kcal / " + price + "원");

        recyclerView = findViewById(R.id.choice_all_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        for(int i=0; i<list.size(); i++){
            str = String.valueOf(list.get(i));
            listAdapter.setList(str);
        }

        endlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endIntent = new Intent(getApplicationContext(), MyCombListActivity.class);
                stitle = String.valueOf(title.getText());
                scomment = String.valueOf(comment.getText());

                Map<String, Object> comb = new HashMap<>();
                comb.put("bread", bread);
                comb.put("cheese", cheese);
                comb.put("description", scomment);
                comb.put("imgUrl", url);
                comb.put("kcal", kcal);
                comb.put("menu", sandwich);
                comb.put("optionList", Arrays.asList(arrayAdd));
                comb.put("price", price);
                comb.put("sauceList", Arrays.asList(arraySauce));
                comb.put("scraps", 0);
                comb.put("title", stitle);
                comb.put("user", email);
                comb.put("vegetableList", Arrays.asList(arrayVegetable));

                db.collection("combination").document()
                                .set(comb)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

                startActivity(endIntent);
            }
        });

        recyclerView.setAdapter(listAdapter);

    }


}
