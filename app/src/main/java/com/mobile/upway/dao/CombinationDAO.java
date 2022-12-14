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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.CombListAdapter;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.controller.FireStoreListCallback;
import com.mobile.upway.dto.Bread;
import com.mobile.upway.dto.Cheese;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Menu;
import com.mobile.upway.dto.Options;
import com.mobile.upway.dto.Sauce;
import com.mobile.upway.dto.User;
import com.mobile.upway.dto.Vegetable;

import org.w3c.dom.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void getAllCombOrderByScraps(FireStoreListCallback fireStoreListCallback) {
        List<Combination> combinationList = new ArrayList<>();

        combColl.orderBy("scraps", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "imgUrl":
                                            combination.setImgUrl(entry.getValue().toString());
                                            break;
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
                                            combination.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "user":
                                            User user = new User();
                                            user.setUserId(entry.getValue().toString());
                                            combination.setUser(user);
                                            break;
                                        case "menu":
                                            Menu menu = new Menu();
                                            menu.setName(entry.getValue().toString());
                                            combination.setMenu(menu);
                                            break;
                                        case "bread":
                                            Bread bread = new Bread();
                                            bread.setName(entry.getValue().toString());
                                            combination.setBread(bread);
                                            break;
                                        case "cheese":
                                            Cheese cheese = new Cheese();
                                            cheese.setName(entry.getValue().toString());
                                            combination.setCheese(cheese);
                                            break;
                                        case "vegetableList":
                                            List<Vegetable> vegeList = new ArrayList<>();
                                            List<String> vegeNameList = (ArrayList<String>) entry.getValue();
                                            for (String vegeName : vegeNameList) {
                                                Vegetable vege = new Vegetable();
                                                vege.setName(vegeName);
                                                vegeList.add(vege);
                                            }
                                            combination.setVegetableList(vegeList);
                                            /* ?????? ?????????????????? ?????? ??????..
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

                                             */
                                            break;
                                        case "sauceList":
                                            List<Sauce> sauceList = new ArrayList<>();
                                            List<String> sauceNameList = (ArrayList<String>) entry.getValue();
                                            for (String sauceName : sauceNameList) {
                                                Sauce sauce = new Sauce();
                                                sauce.setName(sauceName);
                                                sauceList.add(sauce);
                                            }
                                            combination.setSauceList(sauceList);
                                            break;
                                        case "optionList":
                                            List<Options> optionsList = new ArrayList<>();
                                            List<String> opNameList = (ArrayList<String>) entry.getValue();
                                            for (String opName : opNameList) {
                                                Options options = new Options();
                                                options.setName(opName);
                                                optionsList.add(options);
                                            }
                                            combination.setOptionsList(optionsList);
                                            break;
                                    }
                                }
                                combination.setId(document.getId());
                                combinationList.add(combination);
                                Log.d(TAG, combination.getId());
                            }
                            fireStoreListCallback.onCallbackList(combinationList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void get5CombListOrderByScraps(FireStoreListCallback fireStoreListCallback) {
        List<Combination> combinationList = new ArrayList<>();

        combColl.orderBy("scraps", Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "imgUrl":
                                            combination.setImgUrl(entry.getValue().toString());
                                            Log.d(TAG, combination.getImgUrl());
                                            break;
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
                                            combination.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "user":
                                            User user = new User();
                                            user.setUserId(entry.getValue().toString());
                                            combination.setUser(user);
                                            break;
                                        case "menu":
                                            Menu menu = new Menu();
                                            menu.setName(entry.getValue().toString());
                                            combination.setMenu(menu);
                                            break;
                                        case "bread":
                                            Bread bread = new Bread();
                                            bread.setName(entry.getValue().toString());
                                            combination.setBread(bread);
                                            break;
                                        case "cheese":
                                            Cheese cheese = new Cheese();
                                            cheese.setName(entry.getValue().toString());
                                            combination.setCheese(cheese);
                                            break;
                                        case "vegetableList":
                                            List<Vegetable> vegeList = new ArrayList<>();
                                            List<String> vegeNameList = (ArrayList<String>) entry.getValue();
                                            for (String vegeName : vegeNameList) {
                                                Vegetable vege = new Vegetable();
                                                vege.setName(vegeName);
                                                vegeList.add(vege);
                                            }
                                            combination.setVegetableList(vegeList);
                                            break;
                                        case "sauceList":
                                            List<Sauce> sauceList = new ArrayList<>();
                                            List<String> sauceNameList = (ArrayList<String>) entry.getValue();
                                            for (String sauceName : sauceNameList) {
                                                Sauce sauce = new Sauce();
                                                sauce.setName(sauceName);
                                                sauceList.add(sauce);
                                            }
                                            combination.setSauceList(sauceList);
                                            break;
                                        case "optionList":
                                            List<Options> optionsList = new ArrayList<>();
                                            List<String> opNameList = (ArrayList<String>) entry.getValue();
                                            for (String opName : opNameList) {
                                                Options options = new Options();
                                                options.setName(opName);
                                                optionsList.add(options);
                                            }
                                            combination.setOptionsList(optionsList);
                                            break;
                                    }
                                }
                                combination.setId(document.getId());
                                combinationList.add(combination);
                                Log.d(TAG, combination.getId());
                            }
                            fireStoreListCallback.onCallbackList(combinationList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void getCombById(String combId, FireStoreCallback fireStoreCallback) {
        combColl.document(combId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Combination combination = new Combination();

                            DocumentSnapshot document = task.getResult();
                            Map<String, Object> data = document.getData();
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                switch (entry.getKey()) {
                                    case "imgUrl":
                                        combination.setImgUrl(entry.getValue().toString());
                                        break;
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
                                        combination.setKcal(Double.parseDouble(entry.getValue().toString()));
                                        break;
                                    case "price":
                                        combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                        break;
                                    case "user":
                                        User user = new User();
                                        user.setUserId(entry.getValue().toString());
                                        combination.setUser(user);
                                        break;
                                    case "menu":
                                        Menu menu = new Menu();
                                        menu.setName(entry.getValue().toString());
                                        combination.setMenu(menu);
                                        break;
                                    case "bread":
                                        Bread bread = new Bread();
                                        bread.setName(entry.getValue().toString());
                                        combination.setBread(bread);
                                        break;
                                    case "cheese":
                                        Cheese cheese = new Cheese();
                                        cheese.setName(entry.getValue().toString());
                                        combination.setCheese(cheese);
                                        break;
                                    case "vegetableList":
                                        List<Vegetable> vegeList = new ArrayList<>();
                                        List<String> vegeNameList = (ArrayList<String>) entry.getValue();
                                        for (String vegeName : vegeNameList) {
                                            Vegetable vege = new Vegetable();
                                            vege.setName(vegeName);
                                            vegeList.add(vege);
                                        }
                                        combination.setVegetableList(vegeList);
                                        break;
                                    case "sauceList":
                                        List<Sauce> sauceList = new ArrayList<>();
                                        List<String> sauceNameList = (ArrayList<String>) entry.getValue();
                                        for (String sauceName : sauceNameList) {
                                            Sauce sauce = new Sauce();
                                            sauce.setName(sauceName);
                                            sauceList.add(sauce);
                                        }
                                        combination.setSauceList(sauceList);
                                        break;
                                    case "optionList":
                                        List<Options> optionsList = new ArrayList<>();
                                        List<String> opNameList = (ArrayList<String>) entry.getValue();
                                        for (String opName : opNameList) {
                                            Options options = new Options();
                                            options.setName(opName);
                                            optionsList.add(options);
                                        }
                                        combination.setOptionsList(optionsList);
                                        break;
                                }
                            }
                            combination.setId(document.getId());
                            Log.d(TAG, combination.getId());
                            fireStoreCallback.onCallback(combination);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void getCombListByUser(String userId, FireStoreListCallback fireStoreListCallback) {
        List<Combination> combinationList = new ArrayList<>();

        combColl.whereEqualTo("user", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "imgUrl":
                                            combination.setImgUrl(entry.getValue().toString());
                                            break;
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
                                            combination.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "user":
                                            User user = new User();
                                            user.setUserId(entry.getValue().toString());
                                            combination.setUser(user);
                                            break;
                                        case "menu":
                                            Menu menu = new Menu();
                                            menu.setName(entry.getValue().toString());
                                            combination.setMenu(menu);
                                            break;
                                        case "bread":
                                            Bread bread = new Bread();
                                            bread.setName(entry.getValue().toString());
                                            combination.setBread(bread);
                                            break;
                                        case "cheese":
                                            Cheese cheese = new Cheese();
                                            cheese.setName(entry.getValue().toString());
                                            combination.setCheese(cheese);
                                            break;
                                        case "vegetableList":
                                            List<Vegetable> vegeList = new ArrayList<>();
                                            List<String> vegeNameList = (ArrayList<String>) entry.getValue();
                                            for (String vegeName : vegeNameList) {
                                                Vegetable vege = new Vegetable();
                                                vege.setName(vegeName);
                                                vegeList.add(vege);
                                            }
                                            combination.setVegetableList(vegeList);
                                            break;
                                        case "sauceList":
                                            List<Sauce> sauceList = new ArrayList<>();
                                            List<String> sauceNameList = (ArrayList<String>) entry.getValue();
                                            for (String sauceName : sauceNameList) {
                                                Sauce sauce = new Sauce();
                                                sauce.setName(sauceName);
                                                sauceList.add(sauce);
                                            }
                                            combination.setSauceList(sauceList);
                                            break;
                                        case "optionList":
                                            List<Options> optionsList = new ArrayList<>();
                                            List<String> opNameList = (ArrayList<String>) entry.getValue();
                                            for (String opName : opNameList) {
                                                Options options = new Options();
                                                options.setName(opName);
                                                optionsList.add(options);
                                            }
                                            combination.setOptionsList(optionsList);
                                            break;
                                    }
                                }
                                combination.setId(document.getId());
                                combinationList.add(combination);
                                Log.d(TAG, combination.getId());
                            }
                            fireStoreListCallback.onCallbackList(combinationList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void getCombListInclude(String field, String condition, FireStoreListCallback fireStoreListCallback) {
        List<Combination> combinationList = new ArrayList<>();

        combColl.whereIn(field, Arrays.asList(condition))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "imgUrl":
                                            combination.setImgUrl(entry.getValue().toString());
                                            break;
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
                                            combination.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "user":
                                            User user = new User();
                                            user.setUserId(entry.getValue().toString());
                                            combination.setUser(user);
                                            break;
                                        case "menu":
                                            Menu menu = new Menu();
                                            menu.setName(entry.getValue().toString());
                                            combination.setMenu(menu);
                                            break;
                                        case "bread":
                                            Bread bread = new Bread();
                                            bread.setName(entry.getValue().toString());
                                            combination.setBread(bread);
                                            break;
                                        case "cheese":
                                            Cheese cheese = new Cheese();
                                            cheese.setName(entry.getValue().toString());
                                            combination.setCheese(cheese);
                                            break;
                                        case "vegetableList":
                                            List<Vegetable> vegeList = new ArrayList<>();
                                            List<String> vegeNameList = (ArrayList<String>) entry.getValue();
                                            for (String vegeName : vegeNameList) {
                                                Vegetable vege = new Vegetable();
                                                vege.setName(vegeName);
                                                vegeList.add(vege);
                                            }
                                            combination.setVegetableList(vegeList);
                                            break;
                                        case "sauceList":
                                            List<Sauce> sauceList = new ArrayList<>();
                                            List<String> sauceNameList = (ArrayList<String>) entry.getValue();
                                            for (String sauceName : sauceNameList) {
                                                Sauce sauce = new Sauce();
                                                sauce.setName(sauceName);
                                                sauceList.add(sauce);
                                            }
                                            combination.setSauceList(sauceList);
                                            break;
                                        case "optionList":
                                            List<Options> optionsList = new ArrayList<>();
                                            List<String> opNameList = (ArrayList<String>) entry.getValue();
                                            for (String opName : opNameList) {
                                                Options options = new Options();
                                                options.setName(opName);
                                                optionsList.add(options);
                                            }
                                            combination.setOptionsList(optionsList);
                                            break;
                                    }
                                }
                                combination.setId(document.getId());
                                combinationList.add(combination);
                                Log.d(TAG, combination.getId());
                            }
                            fireStoreListCallback.onCallbackList(combinationList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void getCombListIncludeInArray(String field, String condition, FireStoreListCallback fireStoreListCallback) {
        List<Combination> combinationList = new ArrayList<>();

        combColl.whereArrayContainsAny(field, Arrays.asList(condition))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Combination combination = new Combination();

                                Map<String, Object> data = document.getData();
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    switch (entry.getKey()) {
                                        case "imgUrl":
                                            combination.setImgUrl(entry.getValue().toString());
                                            break;
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
                                            combination.setKcal(Double.parseDouble(entry.getValue().toString()));
                                            break;
                                        case "price":
                                            combination.setPrice(Integer.parseInt(entry.getValue().toString()));
                                            break;
                                        case "user":
                                            User user = new User();
                                            user.setUserId(entry.getValue().toString());
                                            combination.setUser(user);
                                            break;
                                        case "menu":
                                            Menu menu = new Menu();
                                            menu.setName(entry.getValue().toString());
                                            combination.setMenu(menu);
                                            break;
                                        case "bread":
                                            Bread bread = new Bread();
                                            bread.setName(entry.getValue().toString());
                                            combination.setBread(bread);
                                            break;
                                        case "cheese":
                                            Cheese cheese = new Cheese();
                                            cheese.setName(entry.getValue().toString());
                                            combination.setCheese(cheese);
                                            break;
                                        case "vegetableList":
                                            List<Vegetable> vegeList = new ArrayList<>();
                                            List<String> vegeNameList = (ArrayList<String>) entry.getValue();
                                            for (String vegeName : vegeNameList) {
                                                Vegetable vege = new Vegetable();
                                                vege.setName(vegeName);
                                                vegeList.add(vege);
                                            }
                                            combination.setVegetableList(vegeList);
                                            break;
                                        case "sauceList":
                                            List<Sauce> sauceList = new ArrayList<>();
                                            List<String> sauceNameList = (ArrayList<String>) entry.getValue();
                                            for (String sauceName : sauceNameList) {
                                                Sauce sauce = new Sauce();
                                                sauce.setName(sauceName);
                                                sauceList.add(sauce);
                                            }
                                            combination.setSauceList(sauceList);
                                            break;
                                        case "optionList":
                                            List<Options> optionsList = new ArrayList<>();
                                            List<String> opNameList = (ArrayList<String>) entry.getValue();
                                            for (String opName : opNameList) {
                                                Options options = new Options();
                                                options.setName(opName);
                                                optionsList.add(options);
                                            }
                                            combination.setOptionsList(optionsList);
                                            break;
                                    }
                                }
                                combination.setId(document.getId());
                                combinationList.add(combination);
                                Log.d(TAG, combination.getId());
                            }
                            fireStoreListCallback.onCallbackList(combinationList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    //ReadByid
    public Combination getCombByTitle(String id) {
        Combination[] combination = {new Combination()};
        combColl.document(id)
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

    /* ??? dao?????? db ???????????? ????????????
    public void setCombinationList(Combination combination) {
        MenuDAO menuDAO = new MenuDAO();
        String menuId = combination.getMenu().getName();
        menuDAO.findMenuById(menuId, object -> combination.setMenu((Menu) object));

        BreadDAO breadDAO = new BreadDAO();
        String breadId = combination.getBread().getName();
        breadDAO.findBreadById(breadId, object -> combination.setBread((Bread) object));

        CheeseDAO cheeseDAO = new CheeseDAO();
        String cheeseId = combination.getCheese().getName();
        cheeseDAO.findCheeseById(cheeseId, object -> combination.setCheese((Cheese) object));

        VegetableDAO vegetableDAO = new VegetableDAO();
        final int[] count = {0};
        while(count[0] < combination.getVegetableList().size()){
            vegetableDAO.findVegetableById(combination.getVegetableList().get(count[0]).getName(), new FireStoreCallback() {
                @Override
                public void onCallback(Object object) {
                    combination.getVegetableList().set(count[0]++, (Vegetable) object);
                }
            });
        }
    }

     */
}
