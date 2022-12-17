package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

    public Menu findMenuById(String menuId) {
        Menu[] menu = {null};
        menuColl.document(menuId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            menu[0] = document.toObject(Menu.class);
                        } else {
                            Log.d(TAG, "No such menu");
                        }
                    }
                });
        return menu[0];
    }
}
