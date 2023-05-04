package com.example.a3.ui.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BaseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is base character fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}