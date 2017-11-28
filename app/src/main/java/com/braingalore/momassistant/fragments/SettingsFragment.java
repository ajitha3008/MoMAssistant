package com.braingalore.momassistant.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braingalore.momassistant.R;

/**
 * Created by s92 on 11/27/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.settings_preference_screen);
    }
}