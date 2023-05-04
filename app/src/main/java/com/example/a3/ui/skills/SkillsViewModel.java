package com.example.a3.ui.skills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SkillsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SkillsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is skills fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}