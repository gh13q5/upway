package com.mobile.upway.dao;
import android.util.Log;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Cheese;

import java.util.Map;

public class CheeseDAO {
    public static String TAG = "CheeseDAO";
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference cheesecol;

    public CheeseDAO(){
        db = FirebaseFirestore.getInstance();
        cheesecol = db.collection("cheese");
    }

    public void findCheeseById(String breadId, FireStoreCallback fireStoreCallback) {
        cheesecol.document(breadId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Cheese cheese = document.toObject(Cheese.class);
                        Log.d(TAG, cheese.getName());
                        fireStoreCallback.onCallback(cheese);
                    }
                });
    }

    public Query getCheese(){
        return databaseReference;
    }
}