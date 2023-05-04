package com.example.a3.ui.save_load;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaveLoadViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SaveLoadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is save/load fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}