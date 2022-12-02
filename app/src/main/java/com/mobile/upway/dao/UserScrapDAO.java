package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.dto.User;
import com.mobile.upway.dto.User_scrap;

import java.util.Map;

public class UserScrapDAO {
    private FirebaseFirestore db;
    private CollectionReference userScrapColl;

    public UserScrapDAO() {
        db = FirebaseFirestore.getInstance();
        userScrapColl = db.collection("userScrap");
    }
}
