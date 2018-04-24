package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.content.Context;
import android.view.LayoutInflater;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.Surah;

import java.util.ArrayList;

public interface MVPPresenter {

    interface HomePresenter{

    }
    interface PrayerTimePresenter{

    }
    interface QiblaPresenter{

    }
    interface QuranPresenter{
        void prepareSearchAdapter(LayoutInflater inflater, ArrayList<Surah> surahs);
    }
    interface SettingsPresenter{

    }
}
