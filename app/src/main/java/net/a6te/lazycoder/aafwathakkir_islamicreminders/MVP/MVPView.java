package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.CustomSuggestionsAdapter;

public interface MVPView {
    interface HomeView{

    }
    interface PrayerTimeView{

    }
    interface QiblaView{

    }
    interface QuranView{
        void initializeSearchView(CustomSuggestionsAdapter adapter);
    }
    interface SettingsView{

    }

}
