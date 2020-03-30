package com.example.swipe_prototype;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class SwipeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
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

    public void sendMessage() { //Funktion von Button in bottom navigation und Antwort irrgendwie zwischen speichern...
        final String url = "http://152.96.56.38:8080/api/User";
        RESTTask task = new RESTTask();
        task.setSwipeViewModel(this);
        task.execute(url);
    }
}
