package com.example.lfg_source.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lfg_source.R;
import com.example.lfg_source.entity.Group;
import com.example.lfg_source.main.group.GroupSwipeFragment;
import com.example.lfg_source.main.match.MatchFragment;
import com.example.lfg_source.main.user.HomeFragment;
import com.example.lfg_source.main.user.UserSwipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public final HomeFragment HOMEFRAGMENT = new HomeFragment();
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public Fragment selectedFragment;
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public Group selectedGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("LFG Home");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, HOMEFRAGMENT);
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.mainNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_swipe:
                        if(selectedGroup == null){
                            getSupportActionBar().setTitle("Swipe User");
                            selectedFragment = new UserSwipeFragment();
                        }else {
                            getSupportActionBar().setTitle("Swipe Gruppe: " + selectedGroup.getName());
                            selectedFragment = new GroupSwipeFragment();
                            ((GroupSwipeFragment) selectedFragment).setSelectedGroup(selectedGroup);
                        }
                        break;
                    case R.id.action_Matches:
                        getSupportActionBar().setTitle("Match");
                        selectedFragment = new MatchFragment();
                        break;
                    default:
                        getSupportActionBar().setTitle("LFG Home");
                        selectedFragment = HOMEFRAGMENT;
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            }
        });
    }
}
