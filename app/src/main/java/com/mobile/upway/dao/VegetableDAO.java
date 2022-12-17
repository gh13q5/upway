package com.mobile.upway.dao;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.dto.Bread;
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

    public Vegetable findVegetableById(String vegeId){
        Vegetable[] vegetable = {null};
        vegetablecol.document(vegeId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            vegetable[0] = document.toObject(Vegetable.class);
                        }
                    }
                });
        return vegetable[0];
    }

    public Query getVegetable(){
        return databaseReference;
    }
}