package com.example.swipe_prototype.mainActivity;

import com.example.swipe_prototype.HomeFragment;
import com.example.swipe_prototype.MainActivity;
import com.example.swipe_prototype.User;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainActivitaUnitTest {

    User user = new User();

    @Test
    public void testHomeFragment(){
        MainActivity main = new MainActivity();
        HomeFragment home = new HomeFragment();
        Assert.assertEquals("set home Fragment Right: ", main.HOMEFRAGMENT, main.selectedFragment);

    }

}