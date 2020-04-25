package com.example.lfg_source.main.edit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.Group;
import com.example.lfg_source.entity.UserContact;
import com.example.lfg_source.main.home.HomeFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class EditFragment extends Fragment {
    private UserContact loggedInUser;
    private Group group = null;
    private TextInputLayout inputDescription;
    private Button save;
    private Button cancel;
    private TagContainerLayout mTagContainerLayout;
    private TextInputLayout textTag;
    private Button btnAddTag;
    private Switch active;

    ArrayList<String> tags = new ArrayList<String>();

    public EditFragment(UserContact loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public EditFragment(UserContact loggedInUser, Group group) {
        this.loggedInUser = loggedInUser;
        this.group = group;
    }

    protected void setUpTagContainer() {
        mTagContainerLayout.setTags(tags);
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(final int position, String text) {
                showDeleteConfirmation(position, text);
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                showDeleteConfirmation(position, text);
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {
            }

            @Override
            public void onTagCrossClick(int position) {
            }
        });
    }

    private void showDeleteConfirmation(final int position, String text){
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(text)
                .setMessage("You will delete this tag!")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position < mTagContainerLayout.getChildCount()) {
                            mTagContainerLayout.removeTag(position);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    protected void setButtons() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allValidate() | !validateTags()) {
                    return;
                }
                update();
                goToHome();
            }
        });
        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagContainerLayout.addTag(textTag.getEditText().getText().toString());
                textTag.getEditText().setText("");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }

    protected void goToHome() {
        Fragment newFragment = new HomeFragment(loggedInUser);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected boolean allValidate() {
        return false;
    }

    protected void update() {
    }

    protected boolean validateTags() {
        if (mTagContainerLayout.getTags().size() > 20 || mTagContainerLayout.getTags().size() < 7) {
            textTag.setError("Geben Sie zwischen 7 und 20 Tags ein");
            return false;
        }
        return true;
    }

    protected void getViewElements(View view) {
        inputDescription = view.findViewById(R.id.description);
        save = view.findViewById(R.id.save_button);
        cancel = view.findViewById(R.id.cancel_button);
        mTagContainerLayout = (TagContainerLayout) view.findViewById(R.id.tagcontainerLayout);
        btnAddTag = (Button) view.findViewById(R.id.button_tag);
        textTag = view.findViewById(R.id.text_tag);
        active = view.findViewById(R.id.active);

        if (loggedInUser != null && group == null) {
            if (loggedInUser.getDescription() != null) {
                inputDescription.getEditText().setText(loggedInUser.getDescription());
            }
            if (loggedInUser.getTags() != null) {
                for (String tag : loggedInUser.getTags()) {
                    tags.add(tag);
                }
            }
            active.setChecked(loggedInUser.getActive());
        } else if (group != null) {
            if (group.getDescription() != null) {
                inputDescription.getEditText().setText(group.getDescription());
            }
            if (group.getTags() != null) {
                for (String tag : group.getTags()) {
                    tags.add(tag);
                }
            }
            active.setChecked(group.getActive());
        }
    }

    protected boolean getActiveState() {
        return active.isChecked();
    }

    protected String getInputDescriptionString() {
        return inputDescription.getEditText().toString().trim();
    }

    protected ArrayList<String> getTags() {
        return (ArrayList<String>) mTagContainerLayout.getTags();
    }
}
