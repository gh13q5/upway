package com.mobile.upway.createComb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobile.upway.R;

public class ccSandwichActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comb_sandwich);

        View btn1 = (View)findViewById(R.id.sandwich_bottom_square);
        Button sandToBread = (Button)btn1.findViewById(R.id.sw_btn_next);

        sandToBread.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent breadIntent = new Intent(getApplicationContext(),ccBreadActivity.class);
                startActivity(breadIntent);
            }
        });
    }
}
