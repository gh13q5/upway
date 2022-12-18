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
import com.mobile.upway.createComb.FireStoreCheeseListCallback;
import com.mobile.upway.dto.Cheese;

import java.util.ArrayList;
import java.util.List;
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

    public void getAllCheese(FireStoreCheeseListCallback fireStoreCheeseListCallback) {
        List<Cheese> cheeseList = new ArrayList<>();

        cheesecol.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Cheese cheese = new Cheese();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            cheese.setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            cheese.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "imgUrl":
                                            cheese.setImgUrl(entry.getValue().toString());
                                            break;
                                    }
                                }
                                cheeseList.add(cheese);
                                Log.d(TAG, cheese.getName());
                            }
                            fireStoreCheeseListCallback.onCallbackCheeseList(cheeseList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
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