package com.mobile.upway.controller;

import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Comment;

import java.util.List;

public interface FireStoreListCallback<E> {
    void onCallbackList(List<E> combList);
}
