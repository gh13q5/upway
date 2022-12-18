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
import com.mobile.upway.createComb.FireStoreOptionsListCallback;
import com.mobile.upway.dto.Options;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OptionsDAO {
    public static String TAG = "OptionsDAO";
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference optionscol;

    public OptionsDAO(){
        db = FirebaseFirestore.getInstance();
        optionscol = db.collection("options");
    }

    public void getAllOptions(FireStoreOptionsListCallback fireStoreOptionsListCallback) {
        List<Options> optionsList = new ArrayList<>();

        optionscol.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Options options = new Options();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            options.setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            options.setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            options.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "imgUrl":
                                            options.setImgUrl(entry.getValue().toString());
                                            break;
                                    }
                                }
                                optionsList.add(options);
                                Log.d(TAG, options.getName());
                            }
                            fireStoreOptionsListCallback.onCallbackOptionsList(optionsList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void findOptionsById(String optionsId, FireStoreCallback fireStoreCallback) {
        optionscol.document(optionsId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Options options = document.toObject(Options.class);
                        Log.d(TAG, options.getName());
                        fireStoreCallback.onCallback(options);
                    }
                });
    }


    public Query getOptions(){
        return databaseReference;
    }
}
