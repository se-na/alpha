package com.example.lfg_source.main.group;

import androidx.core.view.GestureDetectorCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupSwipeFragment extends Fragment {
    private GroupSwipeViewModel mViewModel;
    private GestureDetectorCompat gestureDetectorCompat = null;

    private Group selectedGroup;

    private TextView lastName;
    private TextView firstName;
    private TextView description;
    private Drawable drawable;
    private ProgressBar mProgress;
    private RecyclerView recyclerView;

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
        // detectSwipe();
        View view = inflater.inflate(R.layout.group_swipe_fragment, container, false);
        getViewElements(view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
        return view;
    }

   /* private void detectSwipe() {
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(
                Objects.requireNonNull(getActivity()).getParent(), gestureListener);
    }*/

    private void getViewElements(View view) {
        lastName = view.findViewById(R.id.name);
        firstName = view.findViewById(R.id.firstname);
        description = view.findViewById(R.id.description);
        mProgress = (ProgressBar) view.findViewById(R.id.circularProgressbar);
        Resources res = getResources();
        drawable = res.getDrawable(R.drawable.circular);
    }

    private void setProgress(){
        mProgress.setProgress(60);
        mProgress.setMax(100);
        mProgress.setProgressDrawable(drawable);
    }

    private void setViewElements(String lastName, String firstName, String description, String[] tags){
        this.lastName.setText(lastName);
        this.firstName.setText(firstName);
        this.description.setText(description);
    }

/*     @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GroupSwipeViewModel.class);
        final Observer<List<User>> userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                groupsToSwipe.addAll(groups);
                showSuggestion();
            }
        };
        mViewModel.getGroups().observe(getViewLifecycleOwner(), userObserver);
        mViewModel.sendMessage();
    }

    public void setInterested(boolean value) {
        String text = "Match interested: " + value;
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        final String url = "http://152.96.56.38:8080/User/bool";
        RestClientPut task = new RestClientPut();
        task.setMessage(value);
        task.execute(url);
        groupsToSwipe.remove(0);
    }

    public void showSuggestion(){
        if(groupsToSwipe.size() < 3){
            mViewModel.sendMessage();
        }
        if(!groupsToSwipe.isEmpty()){
            setViewElements(groupsToSwipe.get(0).getLastName(),
                    groupsToSwipe.get(0).getFirstName(),
                    groupsToSwipe.get(0).getDescription(),
                    groupsToSwipe.get(0).getTags());
            setProgress();
        }else {
            setViewElements("Zurzeit wurden leider keine Passenden Personen gefunden",
                    null,
                    null,
                    new String[0]);
        }
    }
*/
}
