package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.LayoutInflater;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.Surah;

import java.util.ArrayList;

public interface MVPPresenter {

    interface HomePresenter{

    }
    interface PrayerTimePresenter{

        void startCalculation();
    }
    interface QiblaPresenter{
        void startCalculatingLocation();

        boolean checkSensorAvailability(SensorManager mSensorManager);

        void calculateQiblaInfo();

        void onResume();

        void onPause();
    }

    interface QuranPresenter{
        void prepareSearchAdapter(LayoutInflater inflater, ArrayList<Surah> surahs);
    }
    interface SettingsPresenter{

    }
}
