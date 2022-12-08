package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.dto.Cheese;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Menu;
import com.mobile.upway.dto.Options;
import com.mobile.upway.dto.Sauce;
import com.mobile.upway.dto.User;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class combinationDAO {
    public static String TAG = "COMBDAO";
    private FirebaseFirestore db;
    private CollectionReference combColl;


    public combinationDAO() {
        db = FirebaseFirestore.getInstance();
        combColl = db.collection("combination");
    }
    //Read
    public List<Combination> getAllComb(){
        List<Combination> combinationList = new ArrayList<>();
        combColl.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();
                                combination = document.toObject(Combination.class);
                                combinationList.add(combination);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return combinationList;
    }
    //ReadBytitle
    public Combination getCombByTitle(String title){
        Combination[] combination = {new Combination()};
        combColl.document(title)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        combination[0] = documentSnapshot.toObject(Combination.class);
                    }
                });
        return combination[0];
    }
    //write
    public void writeCombination(Combination combination){
        combColl.document(combination.getTitle()).set(combination)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
