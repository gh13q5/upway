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
import com.mobile.upway.dto.Options;

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
