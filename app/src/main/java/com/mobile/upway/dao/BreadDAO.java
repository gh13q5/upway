package com.mobile.upway.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mobile.upway.dto.Bread;

public class BreadDAO {

    private DatabaseReference databaseReference;

    BreadDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Bread.class.getSimpleName());
    }

    public Query getBread(){
        return databaseReference;
    }
}
