package com.braingalore.momassistant;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.braingalore.momassistant.fragments.RecordFragment;
import com.braingalore.momassistant.fragments.RecordingListFragment;
import com.braingalore.momassistant.fragments.SettingsFragment;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    RecordFragment recordFragment;

    RecordingListFragment recordListFragment;

    SettingsFragment settingsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager;
            FragmentTransaction fragmentTransaction;
            switch (item.getItemId()) {
                case R.id.navigation_record:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    recordFragment = new RecordFragment();
                    fragmentTransaction.replace(R.id.fragment_container, recordFragment, "HELLO");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_rec_list:
                    recordFragment.stopRecording();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    recordListFragment = new RecordingListFragment();
                    fragmentTransaction.replace(R.id.fragment_container, recordListFragment, "HELLO");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_settings:
                    recordFragment.stopRecording();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    settingsFragment = new SettingsFragment();
                    fragmentTransaction.replace(R.id.fragment_container, settingsFragment, "HELLO");
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    public void enableBackInSupportActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void disableBackInSupportActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        fragmentTransaction.replace(R.id.fragment_container, dashBoardFragment, "dashboard");
        fragmentTransaction.commit();*/
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_record);

        File mPath = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getResources().getString(R.string.app_name) + "/");
        mPath.mkdir();
    }

}
