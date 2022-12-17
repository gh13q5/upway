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

    public Cheese findCheeseById(String cheeseId){
        Cheese[] cheese = {null};
        cheesecol.document(cheeseId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            cheese[0] = document.toObject(Cheese.class);
                        }
                    }
                });
        return cheese[0];
    }

    public Query getCheese(){
        return databaseReference;
    }
}