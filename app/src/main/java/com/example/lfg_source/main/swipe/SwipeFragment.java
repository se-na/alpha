package com.example.lfg_source.main.swipe;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import com.example.lfg_source.R;
import com.example.lfg_source.animation.DetectSwipeGestureListener;
import com.example.lfg_source.rest.RestClientPut;

import java.util.ArrayList;
import java.util.Objects;

import co.lujun.androidtagview.TagContainerLayout;

public class SwipeFragment extends Fragment {

    private TextView lastName;
    private TextView description;
    private Drawable drawable;
    private ProgressBar mProgress;
    private TagContainerLayout mTagContainerLayout;
    private GestureDetectorCompat gestureDetectorCompat = null;


    protected void setGestuerSwipe(){
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(
                Objects.requireNonNull(getActivity()).getParent(), gestureListener);
    }

    void getViewElements(View view) {
        lastName = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);
        mTagContainerLayout = (TagContainerLayout) view.findViewById(R.id.tagcontainerLayout);

        mProgress = (ProgressBar) view.findViewById(R.id.circularProgressbar);
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
    void setProgress(){
        mProgress.setProgress(60);
        mProgress.setMax(100);
        mProgress.setProgressDrawable(drawable);
    }

    void setViewElements(String lastName, String description, ArrayList<String> tags){
        this.lastName.setText(lastName);
        this.description.setText(description);
        mTagContainerLayout.setTags(tags);
    }

    public void setInterested(boolean value) {
        String text = "Match interested: " + value;
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        final String url = "http://152.96.56.38:8080/User/bool";
        RestClientPut task = new RestClientPut();
        task.setMessage(value);
        task.execute(url);
    }

    public void showSuggestion() {
        lastName.setText("Error");
    }
}
