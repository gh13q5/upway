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
import com.mobile.upway.controller.FireStoreListCallback;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.User;
import com.mobile.upway.dto.User_scrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserScrapDAO {
    public static String TAG = "UserScrapDAO";

    private FirebaseFirestore db;
    private CollectionReference userScrapColl;

    public UserScrapDAO() {
        db = FirebaseFirestore.getInstance();
        userScrapColl = db.collection("userScrap");
    }

    public void findUserScrapByUser(String userId, FireStoreListCallback fireStoreListCallback) {
        List<User_scrap> scrapList = new ArrayList<>();
        userScrapColl.whereEqualTo("user", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User_scrap scrap = new User_scrap();
                            Map<String, Object> data = document.getData();
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                switch (entry.getKey()) {
                                    case "user":
                                        User user = new User();
                                        user.setUserId(entry.getValue().toString());
                                        scrap.setUser(user);
                                        break;
                                    case "comb":
                                        Combination comb = new Combination();
                                        comb.setId(entry.getValue().toString());
                                        scrap.setComb(comb);
                                        break;
                                }
                            }
                            Log.d(TAG, scrap.getUser().getUserId());
                            Log.d(TAG, scrap.getComb().getId());
                            scrapList.add(scrap);
                        }
                        fireStoreListCallback.onCallbackList(scrapList);
                    }
                });
    }

    public void existUserScrapByUserAndComb(String userId, String combId, FireStoreCallback fireStoreCallback) {
        userScrapColl.whereEqualTo("user", userId)
                .whereEqualTo("comb", combId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if(task.getResult().size() != 0){
                            Log.d(TAG, "userScrap 발견");
                            User_scrap scrap = new User_scrap();
                            fireStoreCallback.onCallback(scrap);
                        }
                        else{
                            Log.d(TAG, "userScrap 발견 못 함 ... ");
                            fireStoreCallback.onCallback(null);
                        }
                    }
                });
    }

    public void createUserScrap(String user, String comb, FireStoreCallback fireStoreCallback) {
        Map<String, String> data = new HashMap<>();
        data.put("user", user);
        data.put("comb", comb);
        db.collection("userScrap").document(user + comb).set(data);
        fireStoreCallback.onCallback(null);
    }

    public void deleteUserScrap(String user, String comb, FireStoreCallback fireStoreCallback) {
        db.collection("userScrap").document(user + comb).delete();
        fireStoreCallback.onCallback(null);
    }

}
