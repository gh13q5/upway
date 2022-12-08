package com.mobile.upway.dao;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.dto.Vegetable;

import java.util.Map;

public class VegetableDAO {

    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference vegetablecol;

    public VegetableDAO(){
        db = FirebaseFirestore.getInstance();
        vegetablecol = db.collection("vegetable");
    }

    public Vegetable findVegetableByName(String vegetablename){
        Vegetable[] vegetable = {null};
        vegetablecol.whereEqualTo("name", vegetablename)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                vegetable[0] = new Vegetable();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            vegetable[0].setVegetable(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            vegetable[0].setVegetableKcal(entry.getValue().toString());
                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
        return vegetable[0];
    }

    public Query getVegetable(){
        return databaseReference;
    }
}