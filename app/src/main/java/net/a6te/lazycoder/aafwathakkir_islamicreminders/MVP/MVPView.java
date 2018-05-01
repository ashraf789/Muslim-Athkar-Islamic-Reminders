package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.CustomSuggestionsAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.PrayerTimeAdapter;

import java.util.List;

public interface MVPView {
    interface HomeView{

        void setTodayImage(String data);

        void storeBitMapImage(Bitmap bitmap);


        void updateRemainder(Context context, int hour, int mint, long interval);

        void shareImage(Intent shareIntent);
    }
    interface PrayerTimeView{

        void initializeRecyclerView(PrayerTimeAdapter adapter);


        void setCityName(String cityName);

        void showGpsSettingAlert();
    }
    interface QiblaView{
        void setQiblaInfo(String qiblaDegree, String qiblaDistance);

        //this will generate new compass direction
        void changeCompassDirection(float directionsNorth, float directionsQibla, float degree);

        void notifyNoInternetConnection();

        void showSensorNotAvailable();

        void notifyNotEnabledGPS();
    }
    interface QuranView{
        void initializeSearchView(CustomSuggestionsAdapter adapter);
    }
    interface SettingsView{

        void initializeLanguageSpinner(ArrayAdapter adapter, String selectedName, int position);

        void initializeFrequencySpinner(ArrayAdapter adapter, String selectedName, int position);

        void initializePrayerTimeCalculationSpinner(ArrayAdapter adapter, String selectedName, int position);

        void initializeJuristicSpinner(ArrayAdapter adapter, String selectedName, int position);


        void initializeRemainderLanguage(ArrayAdapter adapter, boolean[] selectedLanguage);
    }

}
