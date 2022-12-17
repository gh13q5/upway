package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.CombListAdapter;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Options;
import com.mobile.upway.dto.Sauce;
import com.mobile.upway.dto.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombinationDAO {
    public static String TAG = "COMBDAO";
    private FirebaseFirestore db;
    private CollectionReference combColl;


    public CombinationDAO() {
        db = FirebaseFirestore.getInstance();
        combColl = db.collection("combination");
    }

    //Read
    public void getAllComb(RecyclerView recyclerView) {
        FireStoreCallback fireStoreCallback = new FireStoreCallback();
        List<Combination> combinationList = new ArrayList<>();

        combColl.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "title":
                                            combination.setTitle(entry.getValue().toString());
                                            break;
                                        case "description":
                                            combination.setDescription(entry.getValue().toString());
                                            break;
                                        case "scraps":
                                            combination.setScraps(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "kcal":
                                            combination.setKcal(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "user":
                                            UserDAO userDAO = new UserDAO();
                                            combination.setUser(userDAO.findUserById(entry.getValue().toString()));
                                            break;
                                        case "menu":
                                            MenuDAO menuDAO = new MenuDAO();
                                            combination.setMenu(menuDAO.findMenuById(entry.getValue().toString()));

                                            break;
                                        case "bread":
                                            // 수정 중 ...
                                            BreadDAO breadDAO = new BreadDAO();
                                            combination.setBread(breadDAO.findBreadById(entry.getValue().toString()));

                                            break;
                                        case "cheese":
                                            CheeseDAO cheeseDAO = new CheeseDAO();
                                            combination.setCheese(cheeseDAO.findCheeseById(entry.getValue().toString()));

                                            break;
                                        case "vegetableList":
                                            List<Vegetable> vegeList = new ArrayList<>();
                                            List<DocumentReference> vegeRefList = (ArrayList<DocumentReference>) entry.getValue();
                                            int vegeCount = vegeRefList.size();
                                            for (int i = 0; i < vegeCount; i++) {
                                                Log.d(TAG, vegeRefList.get(i).getPath());
                                                db.document(vegeRefList.get(i).getPath()).get()
                                                        .addOnCompleteListener(task1 -> {
                                                            Vegetable vege = task1.getResult().toObject(Vegetable.class);
                                                            vegeList.add(vege);
                                                            if (vegeList.size() == vegeCount) {
                                                                combination.setVegetableList(vegeList);
                                                            }
                                                        });
                                            }
                                            break;
                                        case "sauceList":
                                            List<Sauce> sauceList = new ArrayList<>();
                                            List<DocumentReference> sauceRefList = (ArrayList<DocumentReference>) entry.getValue();
                                            int sauceCount = sauceRefList.size();
                                            for (int i = 0; i < sauceCount; i++) {
                                                db.document(sauceRefList.get(i).getPath()).get()
                                                        .addOnCompleteListener(task1 -> {
                                                            Sauce sauce = task1.getResult().toObject(Sauce.class);
                                                            sauceList.add(sauce);
                                                            if (sauceList.size() == sauceCount) {
                                                                combination.setSauceList(sauceList);
                                                            }
                                                        });
                                            }
                                            break;
                                        case "optionsList":
                                            List<Options> optionsList = new ArrayList<>();
                                            List<DocumentReference> opRefList = (ArrayList<DocumentReference>) entry.getValue();
                                            int opCount = opRefList.size();
                                            for (int i = 0; i < opCount; i++) {
                                                db.document(opRefList.get(i).getPath()).get()
                                                        .addOnCompleteListener(task1 -> {
                                                            Options options = task1.getResult().toObject(Options.class);
                                                            optionsList.add(options);
                                                            if (optionsList.size() == opCount) {
                                                                combination.setOptionsList(optionsList);
                                                            }
                                                        });
                                            }
                                            break;
                                    }
                                }
                                combination.setId(document.getId());
                                fireStoreCallback.setCombinationCallback(combination, combinationList);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        fireStoreCallback.setAdapter(combinationList, recyclerView);
                    }
                });
    }

    //ReadBytitle
    public Combination getCombByTitle(String title) {
        Combination[] combination = {new Combination()};
        combColl.document(title)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        combination[0] = documentSnapshot.toObject(Combination.class);
                    }
                });
        return combination[0];
    }

    //write
    public void writeCombination(Combination combination) {
        combColl.document(combination.getTitle()).set(combination)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public class FireStoreCallback {
        void setCombinationCallback(Combination combination, List<Combination> combinationList){
            combinationList.add(combination);
        }
        void setAdapter(List<Combination> combinationList, RecyclerView recyclerView){
            Log.d(TAG, "combinationList 크기 : " + combinationList.size());
            CombListAdapter adapter = new CombListAdapter();
            adapter.setList((ArrayList<Combination>) combinationList);
            recyclerView.setAdapter(adapter);
        }
    }
}
