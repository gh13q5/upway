package com.mobile.upway.createComb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mobile.upway.R;

public class createCombActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_sandwich);

        //sandwich - bread - cheese - vegetable - sauce - add
        View btn1 = (View)findViewById(R.id.sandwich_bottom_square);
        Button sandToBread = (Button)btn1.findViewById(R.id.sw_btn_next);
        View btn2 = (View)findViewById(R.id.bread_bottom_square);
        Button breadToCheese = (Button)btn2.findViewById(R.id.cm_btn_next);
        Button breadToSand = (Button)btn2.findViewById(R.id.cm_btn_before);
        View btn3 = (View)findViewById(R.id.cheese_bottom_square);
        Button cheeseToVege = (Button)btn3.findViewById(R.id.cm_btn_next);
        Button cheeseToBread = (Button)btn3.findViewById(R.id.cm_btn_before);
        View btn4 = (View)findViewById(R.id.vegetable_bottom_square);
        Button vegeToSauce = (Button)btn4.findViewById(R.id.cm_btn_next);
        Button vegeToCheese = (Button)btn4.findViewById(R.id.cm_btn_before);
        View btn5 = (View)findViewById(R.id.sauce_bottom_square);
        Button sauceToAdd = (Button)btn5.findViewById(R.id.cm_btn_next);
        Button sauceToVege = (Button)btn5.findViewById(R.id.cm_btn_before);
        View btn6 = (View)findViewById(R.id.add_bottom_square);
        Button addToSauce = (Button)btn6.findViewById(R.id.ad_btn_before);
        Button endSave = (Button)btn6.findViewById(R.id.ad_btn_end);

        sandToBread.setOnClickListener(this);
        breadToCheese.setOnClickListener(this);
        breadToSand.setOnClickListener(this);
        cheeseToVege.setOnClickListener(this);
        cheeseToBread.setOnClickListener(this);
        vegeToSauce.setOnClickListener(this);
        vegeToCheese.setOnClickListener(this);
        sauceToAdd.setOnClickListener(this);
        sauceToVege.setOnClickListener(this);
        addToSauce.setOnClickListener(this);
        endSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.sw_btn_next:
                break;
            case R.id.cm_btn_next:
                break;
            case R.id.cm_btn_before:
                break;
            case R.id.ad_btn_before:
                break;
            case R.id.ad_btn_end:
                break;


        }
    }
}
