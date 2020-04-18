package com.example.lfg_source.main.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.Group;
import com.example.lfg_source.entity.User;

import java.util.ArrayList;
import java.util.List;

public class GroupSwipeFragment extends SwipeFragment {
    private GroupSwipeViewModel mViewModel;
    private GestureDetectorCompat gestureDetectorCompat = null;

    private Group selectedGroup;
    private List<Group> groupsToSwipe = new ArrayList<>();

    public GroupSwipeFragment() { }

    public void setSelectedGroup(Group selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public static GroupSwipeFragment newInstance() {
        return new GroupSwipeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.setGestuerSwipe();
        View view = inflater.inflate(R.layout.group_swipe_fragment, container, false);
        super.getViewElements(view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
        return view;
    }
/*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GroupSwipeViewModel.class);
        final Observer<List<User>> userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                groupsToSwipe.addAll(users);
                showSuggestion();
            }
        };
        mViewModel.getGroups().observe(getViewLifecycleOwner(), groupObserver);
        mViewModel.sendMessage();
    }*/

    @Override
    public void showSuggestion(){
        if(groupsToSwipe.size() < 3){
            mViewModel.sendMessage();
        }
        if(!groupsToSwipe.isEmpty()){
            super.setViewElements(groupsToSwipe.get(0).getName(),
                    groupsToSwipe.get(0).getDescription(),
                    groupsToSwipe.get(0).getTags());
            super.setProgress();
            groupsToSwipe.remove(0);
        }else {
            setViewElements("Zurzeit wurden leider keine Passenden Personen gefunden",
                    null,
                    new String[0]);
        }
    }

}
