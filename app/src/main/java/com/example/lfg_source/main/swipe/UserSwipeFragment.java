package com.example.lfg_source.main.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserSwipeFragment extends SwipeFragment {
    private UserSwipeViewModel mViewModel;

    private TextView firstName;

    private List<User> usersToSwipe = new ArrayList<>();

    public static UserSwipeFragment newInstance() {
        return new UserSwipeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.setGestuerSwipe();
        View view = inflater.inflate(R.layout.user_swipe_fragment, container, false);
        super.getViewElements(view);
        getUserViewElements(view);
        return view;
    }

    private void getUserViewElements(View view) {
        firstName = view.findViewById(R.id.firstname);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserSwipeViewModel.class);
        final Observer<List<User>> userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersToSwipe.addAll(users);
                showSuggestion();
            }
        };
        mViewModel.getData().observe(getViewLifecycleOwner(), userObserver);
        mViewModel.sendMessage();
    }

    @Override
    public void showSuggestion(){
        if(usersToSwipe.size() < 3){
            mViewModel.sendMessage();
        }
        if(!usersToSwipe.isEmpty()){
            super.setViewElements(usersToSwipe.get(0).getLastName(),
                    usersToSwipe.get(0).getDescription(),
                    usersToSwipe.get(0).getTags());
            super.setProgress();
            setUserViewElements(usersToSwipe.get(0).getFirstName());
            usersToSwipe.remove(0);
        }else {
            setViewElements("Zurzeit wurden leider keine Passenden Personen gefunden",
                    null,
                    new String[0]);
        }
    }

    private void setUserViewElements(String firstName) {
        this.firstName.setText(firstName);
    }
}
