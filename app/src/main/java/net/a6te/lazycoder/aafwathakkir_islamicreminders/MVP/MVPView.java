package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.CustomSuggestionsAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.PrayerTimeAdapter;

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

    }

}
