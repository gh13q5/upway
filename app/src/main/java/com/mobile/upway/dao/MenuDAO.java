package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

    public Menu findMenuByName(String name) {
        Menu[] menu = {null};
        menuColl.whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                menu[0] = new Menu();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            menu[0].setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            menu[0].setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            menu[0].setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                    }
                                }
                            }
                        } else {
                            Log.d(TAG, "No such menu");
                        }
                    }
                });
        return menu[0];
    }
}
