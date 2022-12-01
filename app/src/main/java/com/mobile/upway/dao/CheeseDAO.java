package com.mobile.upway.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mobile.upway.dto.Cheese;

public class CheeseDAO {

    private DatabaseReference databaseReference;

    CheeseDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Cheese.class.getSimpleName());
    }

    public Query getCheese(){
        return databaseReference;
    }
}