package com.example.swipe_prototype.entity;

import com.example.swipe_prototype.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserUnitTest {

    User user = new User();

    @Test
    public void setAndGetId() {
        user.setId(2);
        int id = user.getId();
        assertEquals(2, id);
    }

    @Test
    public void setAndGetName() {
        user.setName("Hungering");
        assertEquals("Hungering", user.getName());
    }

    @Test
    public void setAndGetFirstName() {
        user.setfirstName("Adam");
        assertEquals("Adam", user.getfirstName());
    }

}