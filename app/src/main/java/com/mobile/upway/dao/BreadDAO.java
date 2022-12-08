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
import com.mobile.upway.dto.Bread;

import java.util.Map;

public class BreadDAO {

    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference breadcol;

    public BreadDAO(){
        db = FirebaseFirestore.getInstance();
        breadcol = db.collection("bread");
    }

    public Bread findBreadByName(String breadname){
        Bread[] bread = {null};
        breadcol.whereEqualTo("name", breadname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bread[0] = new Bread();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            bread[0].setBread(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            bread[0].setBreadKcal(entry.getValue().toString());
                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
        return bread[0];
    }


    public Query getBread(){
        return databaseReference;
    }
}
