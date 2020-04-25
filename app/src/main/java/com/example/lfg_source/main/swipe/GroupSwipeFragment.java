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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import co.lujun.androidtagview.TagContainerLayout;

public class GroupSwipeFragment extends SwipeFragment {
    private GroupSwipeViewModel mViewModel;
    private int userId;
    private GestureDetectorCompat gestureDetectorCompat;

    private TextView name;
    private TextView description;
    private TagContainerLayout mTagContainerLayout;
    private Drawable drawable;
    private ProgressBar mProgress;

    private boolean isInit = true;

    private List<Group> groupsToSwipe = new ArrayList<>();

    public GroupSwipeFragment(int loggedInUserId) {
        userId = loggedInUserId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setGestureSwipe();
        View view = inflater.inflate(R.layout.group_swipe_fragment, container, false);
        name = view.findViewById(R.id.name);
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
        mViewModel = ViewModelProviders.of(this).get(GroupSwipeViewModel.class);
        mViewModel.setUserId(userId);
        final Observer<List<Group>> userObserver = new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                groupsToSwipe.addAll(groups);
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
        if (groupsToSwipe.size() < 3) {
            mViewModel.sendMessage();
        }
        if (!groupsToSwipe.isEmpty()) {
            setViewElements(groupsToSwipe.get(0).getName(),
                    groupsToSwipe.get(0).getDescription(),
                    groupsToSwipe.get(0).getTags());
            setProgress();
            groupsToSwipe.remove(0);
        } else {
            setViewElements("Zurzeit wurden leider keine Passenden Gruppen gefunden",
                    "",
                    new ArrayList<String>());
        }
    }

    void setViewElements(String lastName, String description, ArrayList<String> tags) {
        this.name.setText(lastName);
        this.description.setText(description);
        mTagContainerLayout.setTags(tags);
    }
}
