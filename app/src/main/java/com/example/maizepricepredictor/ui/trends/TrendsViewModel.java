package com.example.maizepricepredictor.ui.trends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrendsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrendsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is trends fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}