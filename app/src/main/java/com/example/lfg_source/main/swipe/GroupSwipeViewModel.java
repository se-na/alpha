package com.example.lfg_source.main.swipe;

import androidx.lifecycle.MutableLiveData;

import com.example.lfg_source.entity.Group;
import com.example.lfg_source.entity.User;
import com.example.lfg_source.rest.RestClient;

import java.util.List;

public class GroupSwipeViewModel<Groups> extends SwipeViewModel {
    @Override
    public void sendMessage() {
        final String url = "http://152.96.56.38:8080/User";
        RestClient task = new RestClient();
        task.setSwipeViewModel(this);
        task.execute(url);
    }
}
