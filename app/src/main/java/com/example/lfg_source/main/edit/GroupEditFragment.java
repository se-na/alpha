package com.example.lfg_source.main.edit;

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
import com.google.android.material.textfield.TextInputLayout;

public class GroupEditFragment extends EditFragment {

    private GroupEditViewModel mViewModel;
    private Group actualGroup;

    private TextInputLayout inputGroupName;

    public static GroupEditFragment newInstance() {
        return new GroupEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_edit_fragment, container, false);
        setActualGroup();
        super.getViewElements(view);
        getGroupViewElements(view);
        super.setButtons();
        super.setUpTagContainer();

        return view;
    }

    @Override
    protected void update() {
        actualGroup.changeAttributes(
                super.getInputDescriptionString(),
                super.getActiveState(),
                inputGroupName.getEditText().getText().toString().trim(),
                super.getTags()
        );
    }

    private void setActualGroup() {
        actualGroup = new Group();
    }

    private void getGroupViewElements(View view) {
        inputGroupName = view.findViewById(R.id.groupName);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GroupEditViewModel.class);
    }

    private boolean validateGroupName(){
        String firstName = inputGroupName.getEditText().getText().toString().trim();
        if(firstName.isEmpty()){
            inputGroupName.setError("Geben Sie einen Gruppennamen an ein");
            return false;
        }
        return true;
    }

    @Override
    protected boolean allValidate(){
        return !(!validateGroupName());
    }
}
