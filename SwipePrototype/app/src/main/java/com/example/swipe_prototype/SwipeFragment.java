package com.example.swipe_prototype;

import androidx.annotation.VisibleForTesting;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SwipeFragment extends Fragment {

    private SwipeViewModel mViewModel;

    private boolean interested;
    private GestureDetectorCompat gestureDetectorCompat = null;
    private TextView id;
    private TextView name;
    private TextView firstName;
    private List<User> usersToSwipe = new ArrayList<>();

    int listCounter = 0;

    public boolean isInterested() {
        return interested;
    }

    public static SwipeFragment newInstance() {
        return new SwipeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(getActivity().getParent(), gestureListener);
        View view = inflater.inflate(R.layout.swipe_fragment, container, false);
        id = view.findViewById(R.id.id);
        name = view.findViewById(R.id.name);
        firstName = view.findViewById(R.id.firstname);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SwipeViewModel.class);
        final Observer<List<User>> userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersToSwipe.addAll(users);
                id.setText(usersToSwipe.get(listCounter).getId().toString());
                name.setText(usersToSwipe.get(listCounter).getName());
                firstName.setText(usersToSwipe.get(listCounter).getfirstName());
                listCounter++;
            }
        };
        mViewModel.getUsers().observe(getViewLifecycleOwner(), userObserver);
        mViewModel.sendMessage();

    }

    public void setInterested(boolean value) {
        this.interested = value;
        String text = "Match interested: " + value;
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void showSuggestion(){
        if(listCounter < usersToSwipe.size()){
            id.setText(usersToSwipe.get(listCounter).getId().toString());
            name.setText(usersToSwipe.get(listCounter).getName());
            firstName.setText(usersToSwipe.get(listCounter).getfirstName());
            listCounter++;
        }else { // hier später no Matches find zeigen...
            listCounter = 0;
            id.setText(usersToSwipe.get(listCounter).getId().toString());
            name.setText(usersToSwipe.get(listCounter).getName());
            firstName.setText(usersToSwipe.get(listCounter).getfirstName());
            listCounter++;
        }
    }
}
