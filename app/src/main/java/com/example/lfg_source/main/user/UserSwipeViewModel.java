package com.example.lfg_source.main.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lfg_source.entity.User;
import com.example.lfg_source.rest.RestClient;

import java.util.List;

public class UserSwipeViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public void setUsers(List<User> users) {
        this.users.setValue(users);
    }

    public MutableLiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
        }
        return users;
    }

    public void sendMessage() {
        final String url = "http://152.96.56.38:8080/User";
        RestClient task = new RestClient();
        task.setSwipeViewModel(this);
        task.execute(url);
    }
}
