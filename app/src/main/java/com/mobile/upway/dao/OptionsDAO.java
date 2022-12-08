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

    public Options findOptionsByName(String optionsname){
        Options[] options = {null};
        optionscol.whereEqualTo("name", optionsname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                options[0] = new Options();
                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "name":
                                            options[0].setOptions(entry.getValue().toString());
                                            break;
                                        case "kcal":
                                            options[0].setOptionsKcal(entry.getValue().toString());
                                            break;
                                        case "price":
                                            options[0].setOptionsPrice(entry.getValue().toString());
                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
        return options[0];
    }


    public Query getOptions(){
        return databaseReference;
    }
}
