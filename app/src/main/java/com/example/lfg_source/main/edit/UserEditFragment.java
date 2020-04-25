package com.example.lfg_source.main.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.UserContact;
import com.google.android.material.textfield.TextInputLayout;

public class UserEditFragment extends EditFragment {

    private UserEditViewModel mViewModel;
    private UserContact actualuser;

    private TextInputLayout inputFirstName;
    private TextInputLayout inputLastName;
    private TextInputLayout inputEmail;
    private TextInputLayout inputPhone;

    public UserEditFragment() {
        super(null);
    }

    public UserEditFragment(UserContact loggedInUser) {
        super(loggedInUser);
        actualuser = loggedInUser;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_edit_fragment, container, false);
        setActualUser();
        super.getViewElements(view);
        getUserViewElements(view);
        super.setButtons();
        super.setUpTagContainer();
        return view;
    }

    private void setActualUser() {
        if (actualuser == null) {
            actualuser = new UserContact();
        }
    }

    @Override
    protected void update() {
        actualuser.changeAttributes(
                super.getInputDescriptionString(),
                super.getActiveState(),
                inputFirstName.getEditText().getText().toString().trim(),
                inputPhone.getEditText().getText().toString().trim(),
                inputLastName.getEditText().getText().toString().trim(),
                inputEmail.getEditText().getText().toString().trim(),
                super.getTags()
        );
    }

    private boolean validateFirstName() {
        String firstName = inputFirstName.getEditText().getText().toString().trim();
        if (firstName.isEmpty()) {
            inputFirstName.setError("Geben sie Ihren Vornamen ein");
            return false;
        }
        return true;
    }

    private boolean validateLastName() {
        String lastName = inputLastName.getEditText().getText().toString().trim();
        if (lastName.isEmpty()) {
            inputLastName.setError("Geben sie Ihren Nachnamen ein");
            return false;
        }
        return true;
    }

    private boolean validateContact() {
        String email = inputEmail.getEditText().getText().toString().trim();
        String phone = inputPhone.getEditText().getText().toString().trim();
        boolean validate = true;
        if (email.isEmpty() && phone.isEmpty()) {
            inputEmail.setError("Geben sie Ihre EmailAdresse oder Telefonnummer ein");
            inputPhone.setError("Geben sie Ihre EmailAdresse oder Telefonnummer ein");
            validate = false;
        }
        if (!email.isEmpty() && !email.contains("@")) {
            inputEmail.setError("Geben sie eine gültige EmailAdresse ein");
            validate = false;
        }
        return validate;
    }

    private void getUserViewElements(View view) {
        inputFirstName = view.findViewById(R.id.firstName);
        inputLastName = view.findViewById(R.id.lastName);
        inputEmail = view.findViewById(R.id.email);
        inputPhone = view.findViewById(R.id.phoneNumber);

        inputFirstName.getEditText().setText(actualuser.getFirstName());
        inputLastName.getEditText().setText(actualuser.getLastName());
        if (actualuser.getEmail() != null) {
            inputEmail.getEditText().setText(actualuser.getEmail());
        }
        if (actualuser.getPhone() != null) {
            inputPhone.getEditText().setText(actualuser.getPhone());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserEditViewModel.class);
    }

    @Override
    protected boolean allValidate() {
        return !(!validateLastName() | !validateFirstName() | !validateContact());
    }
}
