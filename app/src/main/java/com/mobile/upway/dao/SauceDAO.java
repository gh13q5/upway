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
import com.mobile.upway.dto.Sauce;

import java.util.Map;

public class SauceDAO {
    public static String TAG = "SauceDAO";
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference saucecol;

    public SauceDAO(){
        db = FirebaseFirestore.getInstance();
        saucecol = db.collection("sauce");
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


    public Query getSauce(){
        return databaseReference;
    }
}