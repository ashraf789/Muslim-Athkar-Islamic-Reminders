package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.widget.ArrayAdapter;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.CustomSuggestionsAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.PrayerTimeAdapter;

import java.util.List;

public interface MVPView {
    interface HomeView{

    }
    interface PrayerTimeView{

        void initializeRecyclerView(PrayerTimeAdapter adapter);


        void setCityName(String cityName);
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
        void initializeLanguageSpinner(List<String> dataSet);
        void initializeFrequencySpinner(List<String> dataSet);
        void initializePrayerTimeCalculationSpinner(List<String> dataSet);
        void initializeJuristicSpinner(List<String> dataSet);

        void initializeRemainderLanguage(ArrayAdapter adapter);
    }

}
