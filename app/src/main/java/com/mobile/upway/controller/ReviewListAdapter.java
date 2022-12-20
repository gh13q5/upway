package com.mobile.upway.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.CommentDAO;
import com.mobile.upway.dto.Comment;

import java.util.ArrayList;
import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder> {
    public static String TAG = "ReviewListAdapter";

    private Context context;
    private ArrayList<Comment> commentList;


    // FIREBASE AUTH
    private FirebaseAuth firebaseAuth;

    public ReviewListAdapter() {commentList = new ArrayList<>();}

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = inflater.inflate(R.layout.detail_review_item,parent,false);
        ReviewViewHolder viewHolder = new ReviewViewHolder(context, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Comment comment = commentList.get(position);

        holder.content.setText(comment.getContent());
        holder.nickname.setText(comment.getUser() +" 님");
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
    public void setList(ArrayList<Comment> list){
        this.commentList = list;
        notifyDataSetChanged();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder{
        public TextView content = null;
        public TextView nickname = null;
        public ReviewViewHolder(Context context, View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.reviewText);
            nickname = itemView.findViewById(R.id.reviewnickname);
        }
    }

}
