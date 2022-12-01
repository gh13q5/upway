package com.mobile.upway.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mobile.upway.dto.Sauce;

public class SauceDAO {

    private DatabaseReference databaseReference;

    SauceDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Sauce.class.getSimpleName());
    }

    public Query getSauce(){
        return databaseReference;
    }
}