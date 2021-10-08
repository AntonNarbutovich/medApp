package com.example.medappv2.ui.analysis;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExaminationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExaminationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Medical tests fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}