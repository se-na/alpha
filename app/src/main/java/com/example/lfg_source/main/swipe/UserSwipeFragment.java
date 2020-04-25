package com.example.lfg_source.main.swipe;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lfg_source.R;
import com.example.lfg_source.animation.DetectSwipeGestureListener;
import com.example.lfg_source.entity.Group;
import com.example.lfg_source.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import co.lujun.androidtagview.TagContainerLayout;

public class UserSwipeFragment extends SwipeFragment {
    private Group groupThatSearches;
    private GestureDetectorCompat gestureDetectorCompat;
    private TextView name;
    private TextView firstName;
    private TextView description;
    private TagContainerLayout mTagContainerLayout;
    private Drawable drawable;
    private ProgressBar mProgress;
    private UserSwipeViewModel mViewModel;
    private List<User> usersToSwipe = new ArrayList<>();
    private boolean isInit = true;

    public UserSwipeFragment(Group groupThatSearches) {
        this.groupThatSearches = groupThatSearches;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setGestureSwipe();
        View view = inflater.inflate(R.layout.user_swipe_fragment, container, false);
        name = view.findViewById(R.id.name);
        firstName = view.findViewById(R.id.firstName);
        description = view.findViewById(R.id.description);
        mTagContainerLayout = view.findViewById(R.id.tagcontainerLayout);

        mProgress = view.findViewById(R.id.circularProgressbar);
        Resources res = getResources();
        drawable = res.getDrawable(R.drawable.circular);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
        return view;
    }

    private void setProgress() {
        mProgress.setProgress(60);
        mProgress.setMax(100);
        mProgress.setProgressDrawable(drawable);
    }

    private void setGestureSwipe() {
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener(this);
        gestureDetectorCompat = new GestureDetectorCompat(
                Objects.requireNonNull(getActivity()).getParent(), gestureListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserSwipeViewModel.class);
        mViewModel.setGroup(groupThatSearches);
        final Observer<List<User>> userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersToSwipe.addAll(users);
                if (isInit) {
                    showSuggestion();
                }
                isInit = false;
            }
        };
        mViewModel.getData().observe(getViewLifecycleOwner(), userObserver);
        mViewModel.sendMessage();
    }

    @Override
    public void showSuggestion() {
        if (usersToSwipe.size() < 3) {
            mViewModel.sendMessage();
        }
        if (!usersToSwipe.isEmpty()) {
            setViewElements(usersToSwipe.get(0).getLastName(),
                    usersToSwipe.get(0).getFirstName(),
                    usersToSwipe.get(0).getDescription(),
                    usersToSwipe.get(0).getTags());
            setProgress();
            usersToSwipe.remove(0);
        } else {
            setViewElements("Zurzeit wurden leider keine Passenden Personen gefunden",
                    "", "",
                    new ArrayList<String>());
        }
    }

    void setViewElements(String name, String firstName, String description, ArrayList<String> tags) {
        this.name.setText(name);
        this.firstName.setText(firstName);
        this.description.setText(description);
        mTagContainerLayout.setTags(tags);
    }
}