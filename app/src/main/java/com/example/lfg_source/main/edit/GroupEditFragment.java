package com.example.lfg_source.main.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.Group;
import com.example.lfg_source.entity.UserContact;
import com.google.android.material.textfield.TextInputLayout;

public class GroupEditFragment extends EditFragment {
    UserContact loggedInUserOrGroupAdmin;
    private GroupEditViewModel mViewModel;
    private Group actualGroup;

    private TextInputLayout inputGroupName;

    public GroupEditFragment(UserContact loggedInUserOrGroupAdmin) {
        super(loggedInUserOrGroupAdmin, new Group());
        this.loggedInUserOrGroupAdmin = loggedInUserOrGroupAdmin;
    }

    public GroupEditFragment(Group group, UserContact loggedInUserOrGroupAdmin) {
        super(loggedInUserOrGroupAdmin, group);
        this.loggedInUserOrGroupAdmin = loggedInUserOrGroupAdmin;
        actualGroup = group;
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
        if (actualGroup == null) {
            actualGroup = new Group();
        }
    }

    private void getGroupViewElements(View view) {
        inputGroupName = view.findViewById(R.id.groupName);

        if (actualGroup != null) {
            inputGroupName.getEditText().setText(actualGroup.getName());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GroupEditViewModel.class);
    }

    private boolean validateGroupName() {
        String firstName = inputGroupName.getEditText().getText().toString().trim();
        if (firstName.isEmpty()) {
            inputGroupName.setError("Geben Sie einen Gruppennamen an ein");
            return false;
        }
        return true;
    }

    @Override
    protected boolean allValidate() {
        return !(!validateGroupName());
    }
}
