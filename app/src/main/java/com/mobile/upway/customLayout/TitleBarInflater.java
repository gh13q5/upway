package com.mobile.upway.customLayout;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.controller.MainActivity;
import com.mobile.upway.controller.MypageActivity;
import com.mobile.upway.controller.SelectLoginActivity;
import com.mobile.upway.dao.UserDAO;
import com.mobile.upway.dto.User;

public class TitleBarInflater extends LinearLayout {

    // FIREBASE
    FirebaseAuth firebaseAuth;

    Context context;

    public TitleBarInflater(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TitleBarInflater(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_title_bar, this, false);

        ImageView menu = view.findViewById(R.id.title_menu_btn);
        ImageView logo = view.findViewById(R.id.title_logo);
        ImageView profile = view.findViewById(R.id.title_user_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                Intent myIntent;
                if (currentUser != null) {
                    myIntent = new Intent(context, MypageActivity.class);
                    context.startActivity(myIntent);
                } else {
                    myIntent = new Intent(context, SelectLoginActivity.class);
                    context.startActivity(myIntent);
                }
            }
        });

        logo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(context, MainActivity.class);
                context.startActivity(mainIntent);
            }
        });

        this.addView(view);
    }
}
