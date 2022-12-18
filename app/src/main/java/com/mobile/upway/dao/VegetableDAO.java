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
import com.mobile.upway.dto.Vegetable;

import java.util.Map;

public class VegetableDAO {
    public static String TAG = "VegetableDAO";
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference vegetablecol;

    public VegetableDAO(){
        db = FirebaseFirestore.getInstance();
        vegetablecol = db.collection("vegetable");
    }

    public void findVegetableById(String vegeId, FireStoreCallback fireStoreCallback) {
        vegetablecol.document(vegeId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Vegetable vegetable = document.toObject(Vegetable.class);
                        Log.d(TAG, vegetable.getName());
                        fireStoreCallback.onCallback(vegetable);
                    }
                });
    }

    public Query getVegetable(){
        return databaseReference;
    }
}