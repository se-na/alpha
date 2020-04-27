package com.example.lfg_source.main.swipe;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import com.example.lfg_source.R;
import com.example.lfg_source.animation.DetectSwipeGestureListener;

import java.util.ArrayList;
import java.util.Objects;

import co.lujun.androidtagview.TagContainerLayout;

public class SwipeFragment extends Fragment {
    private TextView name;
    private TextView description;
    private TagContainerLayout mTagContainerLayout;
    private Drawable drawable;
    private ProgressBar mProgress;

    private GestureDetectorCompat gestureDetectorCompat;

    protected void getViewElements(View view){
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
    }

    public void setInterested(boolean value) {
        //TODO Funtioniert noch nicht -> Fix
        /*String text = "Match interested: " + value;
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        final String url = "http://152.96.56.38:8080/User/bool";
        RestClientPut task = new RestClientPut();
        task.setMessage(value);
        task.execute(url);*/
    }

    void setViewElements(String lastName, String description, ArrayList<String> tags) {
        this.name.setText(lastName);
        this.description.setText(description);
        this.mTagContainerLayout.setTags(tags);
    }

    protected void setGestureSwipe() {
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener(this);
        gestureDetectorCompat = new GestureDetectorCompat(
                Objects.requireNonNull(getActivity()).getParent(), gestureListener);
    }

    public void showSuggestion(){

    }

    protected void setProgress() {
        mProgress.setProgress(60);
        mProgress.setMax(100);
        mProgress.setProgressDrawable(drawable);
    }
}
