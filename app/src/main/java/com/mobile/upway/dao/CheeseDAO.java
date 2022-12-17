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
import com.mobile.upway.dto.Cheese;

import java.util.Map;

public class CheeseDAO {

    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference cheesecol;

    public CheeseDAO(){
        db = FirebaseFirestore.getInstance();
        cheesecol = db.collection("cheese");
    }

    public Cheese findCheeseByName(String cheesename){
        Cheese[] cheese = {null};
        cheesecol.whereEqualTo("name", cheesename)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                cheese[0] = new Cheese();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            cheese[0].setName(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            cheese[0].setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
        return cheese[0];
    }

    public Query getCheese(){
        return databaseReference;
    }
}