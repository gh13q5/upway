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

    public Sauce findSauceById(String sauceId){
        Sauce[] sauce = {null};
        saucecol.document(sauceId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            sauce[0] = document.toObject(Sauce.class);
                        }
                    }
                });
        return sauce[0];
    }

    public Query getSauce(){
        return databaseReference;
    }
}