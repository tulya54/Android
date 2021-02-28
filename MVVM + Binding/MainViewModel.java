package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public static final String TAG = "MainViewModel";

    //  Click observers
    private MutableLiveData<Integer> clickResponse = new MutableLiveData<>();
    public MutableLiveData<Integer> getClickResponse() {
        return clickResponse;
    }
    //  Api observers
    private MutableLiveData<ApiResponse> response = new MutableLiveData<>();
    public MutableLiveData<ApiResponse> getResponse() {
        return response;
    }


    public MainViewModel() {

    }
    
    //  Click action
    public void onClickItem(int btn) {
        clickResponse.setValue(btn);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (clickResponse != null) {
            clickResponse = null;
        }
        if (response != null) {
            response = null;
        }
    }
}
