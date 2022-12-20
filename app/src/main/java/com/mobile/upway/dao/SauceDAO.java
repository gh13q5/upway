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
import com.mobile.upway.controller.FireStoreListCallback;
import com.mobile.upway.createComb.FireStoreSauceListCallback;
import com.mobile.upway.dto.Sauce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SauceDAO {
    public static String TAG = "SauceDAO";
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference saucecol;

    public SauceDAO(){
        db = FirebaseFirestore.getInstance();
        saucecol = db.collection("sauce");
    }

    public void getAllSauce(FireStoreSauceListCallback fireStoreSauceListCallback) {
        List<Sauce> sauceList = new ArrayList<>();

        saucecol.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Sauce sauce = new Sauce();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            sauce.setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            sauce.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "imgUrl":
                                            sauce.setImgUrl(entry.getValue().toString());
                                            break;
                                    }
                                }
                                sauceList.add(sauce);
                                Log.d(TAG, sauce.getName());
                            }
                            fireStoreSauceListCallback.onCallbackSauceList(sauceList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void findSauceById(String sauceId, FireStoreCallback fireStoreCallback) {
        saucecol.document(sauceId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Sauce sauce = document.toObject(Sauce.class);
                        Log.d(TAG, sauce.getName());
                        fireStoreCallback.onCallback(sauce);
                    }
                });
    }

    public void findSauceByArrayList(FireStoreListCallback fireStoreListCallback, List<Sauce> sauceList) {
        List<String> nameList = sauceList.stream().map(Sauce::getName).collect(Collectors.toList());
        List<Sauce> scList = new ArrayList<>();
        saucecol.whereIn("name",nameList)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Sauce sauce = document.toObject(Sauce.class);
                                scList.add(sauce);
                                Log.d(TAG,sauce.getName());

                            }
                            fireStoreListCallback.onCallbackList(scList);
                        }else{
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    public Query getSauce(){
        return databaseReference;
    }
}