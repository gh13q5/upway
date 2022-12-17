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
import com.mobile.upway.dto.Options;

import java.util.Map;

public class OptionsDAO {

    private DatabaseReference databaseReference;

    private FirebaseFirestore db;
    private CollectionReference optionscol;

    public OptionsDAO(){
        db = FirebaseFirestore.getInstance();
        optionscol = db.collection("options");
    }

    public Options findOptionsById(String optionsId){
        Options[] options = {null};
        optionscol.document(optionsId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            options[0] = document.toObject(Options.class);
                        }
                    }
                });
        return options[0];
    }


    public Query getOptions(){
        return databaseReference;
    }
}
