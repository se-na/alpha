package com.example.swipe_prototype;

import com.google.android.material.behavior.SwipeDismissBehavior;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntegrationTests {

    @Mock
    SwipeViewModel viewModel;
    SwipeFragment swipeFragment;
    MainActivity mainActivity;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void bottomNavigation(){
        swipeFragment.setInterested(false);
    }

    @Test
    public void swipeVewModel_Test(){
        viewModel.sendMessage();
        ArrayList<User> listOfUsers = (ArrayList<User>) viewModel.getUsers().getValue();

        assertEquals(3, listOfUsers.size());

        assertEquals("1", listOfUsers.get(0).getId());
        assertEquals("Marhad", listOfUsers.get(0).getfirstName());
        assertEquals("Fehta", listOfUsers.get(0).getName());

        assertEquals("2", listOfUsers.get(1).getId());
        assertEquals("Habian", listOfUsers.get(1).getfirstName());
        assertEquals("Fauser", listOfUsers.get(1).getName());

        assertEquals("3", listOfUsers.get(2).getId());
        assertEquals("Kaniel", listOfUsers.get(2).getfirstName());
        assertEquals("Deller", listOfUsers.get(2).getName());
    }

}