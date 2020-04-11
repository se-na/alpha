package com.example.lfg_source.main.group;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.Group;

public class GroupFragment extends Fragment {

    private GroupViewModel mViewModel;
    private Group selectedGroup;

    public GroupFragment() {
    }

    public void setSelectedGroup(Group selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.group_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GroupViewModel.class);
    }

}
