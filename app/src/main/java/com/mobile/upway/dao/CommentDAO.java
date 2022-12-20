package com.mobile.upway.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobile.upway.controller.FireStoreCallback;
import com.mobile.upway.controller.FireStoreListCallback;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public static String TAG = "CommentDAO";

    private FirebaseFirestore db;
    private CollectionReference commentColl;

    public CommentDAO() {
        db = FirebaseFirestore.getInstance();
        commentColl = db.collection("comment");
    }
    //read
    public void findCommentsByComb(FireStoreListCallback fireStoreListCallback, String comb) {
        List<Comment> commentList = new ArrayList<>();
        commentColl.whereEqualTo("combination", comb)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Comment comment = new Comment();
                                comment = document.toObject(Comment.class);
                                commentList.add(comment);
                            }
                            fireStoreListCallback.onCallbackList(commentList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    //write
    public void writeComment(Comment comment, FireStoreCallback fireStoreCallback){
        commentColl.add(comment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        fireStoreCallback.onCallback(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
}
