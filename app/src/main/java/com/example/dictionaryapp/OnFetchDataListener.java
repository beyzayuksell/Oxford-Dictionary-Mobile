package com.example.dictionaryapp;

import com.example.dictionaryapp.Models.APIResponse;

public interface OnFetchDataListener {

    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
