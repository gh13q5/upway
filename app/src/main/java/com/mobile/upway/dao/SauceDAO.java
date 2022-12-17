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
import com.mobile.upway.dto.Sauce;

import java.util.Map;

public class SauceDAO {

    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference saucecol;

    public SauceDAO(){
        db = FirebaseFirestore.getInstance();
        saucecol = db.collection("sauce");
    }

    public Sauce findSauceByName(String saucename){
        Sauce[] sauce = {null};
        saucecol.whereEqualTo("name", saucename)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                sauce[0] = new Sauce();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            sauce[0].setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            sauce[0].setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
        return sauce[0];
    }

    public Query getSauce(){
        return databaseReference;
    }
}