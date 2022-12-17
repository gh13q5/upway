package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Menu;
import com.mobile.upway.dto.User;

import java.util.Map;

public class MenuDAO {
    public static String TAG = "MenuDAO";

    private FirebaseFirestore db;
    private CollectionReference menuColl;

    public MenuDAO() {
        db = FirebaseFirestore.getInstance();
        menuColl = db.collection("sandwich");
    }

    public void findMenuById(String menuId, FireStoreCallback fireStoreCallback) {
        menuColl.document(menuId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Menu menu = document.toObject(Menu.class);
                        Log.d(TAG, menu.getName());
                        fireStoreCallback.onCallback(menu);
                    }
                });
    }
}
