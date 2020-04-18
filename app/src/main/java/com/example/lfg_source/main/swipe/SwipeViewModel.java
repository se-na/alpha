package com.example.lfg_source.main.swipe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lfg_source.rest.RestClient;

import java.util.List;

public class SwipeViewModel<T> extends ViewModel {
    private MutableLiveData<List<T>> data;

    public void setData(List<T> data) {
        this.data.setValue(data);
    }

    public MutableLiveData<List<T>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    public void sendMessage() {
    }
}
