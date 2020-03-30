package com.example.swipe_prototype.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.swipe_prototype.HomeFragment;
import com.example.swipe_prototype.MainActivity;
import com.example.swipe_prototype.R;
import com.example.swipe_prototype.RESTTask;
import com.example.swipe_prototype.SwipeFragment;
import com.example.swipe_prototype.SwipeViewModel;
import com.example.swipe_prototype.User;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HomeFragmentUnitTest {

    User user = new User();

    @Test
    public void testSetViewModel() {
        SwipeViewModel swipe = new SwipeViewModel();
        RESTTask rest = new RESTTask();
        rest.setSwipeViewModel(swipe);
        assertEquals("set Swipemodel: ", swipe, rest.swipeViewModel);
    }
}