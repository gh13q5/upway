package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.createComb.FireStoreSandListCallback;
import com.mobile.upway.controller.FireStoreListCallback;
import com.mobile.upway.dto.Bread;
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

public class MenuDAO {
    public static String TAG = "MenuDAO";

    private FirebaseFirestore db;
    private CollectionReference menuColl;

    public MenuDAO() {
        db = FirebaseFirestore.getInstance();
        menuColl = db.collection("sandwich");
    }

    public void getAllMenu(FireStoreSandListCallback fireStoreSandListCallback) {
        List<Menu> menuList = new ArrayList<>();

        menuColl.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Menu menu = new Menu();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            menu.setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            menu.setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            menu.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "imgUrl":
                                            menu.setImgUrl(entry.getValue().toString());
                                            break;
                                    }
                                }
                                //menu.setName(document.getId());
                                menuList.add(menu);
                                Log.d(TAG, menu.getName());
                            }
                            fireStoreSandListCallback.onCallbackMenuList(menuList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
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

    public void findMenuImgByName(String name, FireStoreCallback fireStoreCallback) {
        Log.d(TAG, "findMenuByName start");
        menuColl.whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Menu menu = new Menu();
                            menu.setImgUrl(document.getData().get("imgUrl").toString());
                            fireStoreCallback.onCallback(menu);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public void findAllMenu(FireStoreListCallback fireStoreListCallback) {
        List<Menu> menuList = new ArrayList<>();

        menuColl.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Menu menu = new Menu();

                            Map<String, Object> data = document.getData();
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                switch (entry.getKey()) {
                                    case "name":
                                        menu.setName(entry.getValue().toString());
                                        break;
                                    case "price":
                                        menu.setPrice(Integer.parseInt(entry.getValue().toString()));
                                        break;
                                    case "kcal":
                                        menu.setKcal(Integer.parseInt(entry.getValue().toString()));
                                        break;
                                    case "imgUrl":
                                        menu.setImgUrl(entry.getValue().toString());
                                        break;
                                }
                            }
                            menuList.add(menu);
                        }
                        fireStoreListCallback.onCallbackList(menuList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public void findMenuByName(String menuName, FireStoreCallback fireStoreCallback){
        menuColl.whereEqualTo("name", menuName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Menu menu = document.toObject(Menu.class);
                                Log.d(TAG, menu.getName());
                                fireStoreCallback.onCallback(menu);
                            }
                        }
                    }
                });
    }
}
