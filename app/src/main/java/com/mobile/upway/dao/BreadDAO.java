package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Menu;
import com.mobile.upway.dto.User;

import java.util.Map;

public class BreadDAO {
    public static String TAG = "BreadDAO";
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference breadcol;

    public BreadDAO() {
        db = FirebaseFirestore.getInstance();
        breadcol = db.collection("bread");
    }

    public void findBreadById(String breadId, FireStoreCallback fireStoreCallback) {
        breadcol.document(breadId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Bread bread = document.toObject(Bread.class);
                        Log.d(TAG, bread.getName());
                        fireStoreCallback.onCallback(bread);
                    }
                });
    }

    public void findBreadByName(String name, FireStoreCallback fireStoreCallback) {
        breadcol.whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Bread bread = new Bread();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            bread.setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            bread.setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "imgUrl":
                                            bread.setImgUrl(entry.getValue().toString());
                                            break;
                                    }
                                }
                                fireStoreCallback.onCallback(bread);
                            }
                        }
                    }
                });
    }


    public Query getBread() {
        return databaseReference;
    }
}
