package com.mobile.upway.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mobile.upway.dto.Vegetable;

public class VegetableDAO {

    private DatabaseReference databaseReference;

    VegetableDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Vegetable.class.getSimpleName());
    }

    public Query getVegetable(){
        return databaseReference;
    }
}