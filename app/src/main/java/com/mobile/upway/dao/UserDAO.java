package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Cheese;
import com.mobile.upway.dto.Comment;
import com.mobile.upway.dto.Menu;
import com.mobile.upway.dto.User;

import java.util.Map;

public class UserDAO {
    public static String TAG = "UserDAO";

    private FirebaseFirestore db;
    public CollectionReference userColl;

    public UserDAO() {
        db = FirebaseFirestore.getInstance();
        userColl = db.collection("user");
    }
    public void findUserById(String userId, FireStoreCallback fireStoreCallback){
        userColl.whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = new User();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "userId":
                                            user.setUserId(entry.getValue().toString());
                                            break;
                                        case "nickname":
                                            user.setNickname(entry.getValue().toString());
                                            break;
                                    }
                                }
                                fireStoreCallback.onCallback(user);
                            }
                        }
                    }
                });
    }

    public User findUserById(String userId) {
        User[] user = {null};
        userColl.whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                user[0] = new User();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "userId":
                                            user[0].setUserId(entry.getValue().toString());
                                            break;
                                        case "password":
                                            user[0].setPassword(entry.getValue().toString());
                                            break;
                                        case "nickname":
                                            user[0].setNickname(entry.getValue().toString());
                                            break;
                                    }
                                }
                            }
                        } else {
                            Log.d(TAG, "No such user");
                        }
                    }
                });
        return user[0];
    }

    public void findUserByUid(String uid, FireStoreCallback fireStoreCallback) {
        userColl.document(uid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        User user = document.toObject(User.class);
                        Log.d(TAG, user.getUserId());
                        fireStoreCallback.onCallback(user);
                    }
                });
    }

    public void createUser(User user, String uid) {
        db.collection("user").document(uid).set(user);
    }

    public void deleteUser(String uid, FireStoreCallback fireStoreCallback) { db.collection("user").document(uid).delete(); }
}
