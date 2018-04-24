package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrayerTime extends Fragment {


    public PrayerTime() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prayer_time, container, false);
    }

}
