package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.app.AlarmManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.LocaleManager;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.SavedData;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.SpinnerAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.SpinnerCheckBoxAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.SpinnerWithCheckBoxAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Home;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.interfaces.CallAttachBaseContext;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.SpinnerWithCheckBoxItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SettingsPresenter implements MVPPresenter.SettingsPresenter{

    private Fragment fragment;
    private List<String > juristicMethods;
    private List<String > prayerTimeCalculationMethods;
    private List<String > frequencies;
    private List<String> languages;

    private MVPView.SettingsView mvpView;
    private SavedData savedData;
    private CallAttachBaseContext callAttachBaseContext;
    private Home homeFragment;
    private ArrayAdapter<String> adapter;




    public SettingsPresenter(Fragment fragment) {
        this.fragment = fragment;
        mvpView = (MVPView.SettingsView) fragment;
        savedData = new SavedData(fragment.getContext());
        callAttachBaseContext = (CallAttachBaseContext) fragment.getContext();
        homeFragment = new Home();
    }

    @Override
    public void prepareAdapters(){
        languageAdapter();
        frequencyAdapter();
        prayerTimeCalculationMethod();
        juristicMethod();
        remainderLanguageAdapter();
    }

    private void remainderLanguageAdapter() {

        List<String> temp = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.remainder_language));
        int size = temp.size();

        boolean[] selectedLanguage = savedData.getRemainderLanguages(size);

        adapter = new ArrayAdapter<String>(fragment.getContext(), android.R.layout.simple_spinner_item);
        for (String data: temp){
            adapter.add(data);
        }
        mvpView.initializeRemainderLanguage(adapter,selectedLanguage);

    }


    private void juristicMethod() {
        juristicMethods = null;
        juristicMethods = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.juristic_method));

        //get saved data from shared preferences
        int indexNo = savedData.getJuristicMethodId();
        String selectedName = juristicMethods.get(indexNo);

        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), juristicMethods);
        mvpView.initializeJuristicSpinner(adapter,selectedName,indexNo);
    }

    private void prayerTimeCalculationMethod() {
        prayerTimeCalculationMethods = null;
        prayerTimeCalculationMethods = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.prayer_calculation_method));


        int indexNo = savedData.getCalculationMethodId();
        String selectedName = prayerTimeCalculationMethods.get(indexNo);


        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), prayerTimeCalculationMethods);
        mvpView.initializePrayerTimeCalculationSpinner(adapter,selectedName, indexNo);
    }

    private void frequencyAdapter() {
        frequencies = null;
        frequencies = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.frequency));

        int indexNo = savedData.getFrequencySelectedId();
        String selectedName = frequencies.get(indexNo);


        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), frequencies);
        mvpView.initializeFrequencySpinner(adapter,selectedName, indexNo);
    }

    private void languageAdapter() {
        languages = null;
        languages = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.app_languages));

        int indexNo = savedData.getAppLanguageSelectedId();
        String selectedName = languages.get(indexNo);

        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), languages);
        mvpView.initializeLanguageSpinner(adapter, selectedName, indexNo);

    }


    //save selected item id into shared preferences

    @Override
    public void saveAppLanguageId(int id){
        savedData.setAppLanguageSelectedId(id);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    callAttachBaseContext.onAttachBaseContext(fragment.getContext());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void saveFrequencyId(int id){
        long newInterval = calculateInterval(id);
        Calendar calendar = Calendar.getInstance();

        savedData.setFrequencySelectedId(id);
        savedData.setNewRemainderInterval(newInterval);

        savedData.setAppStartHour(calendar.get(Calendar.HOUR_OF_DAY));
        savedData.setAppStartMin(calendar.get(Calendar.MINUTE));

        if (newInterval != savedData.getOldRemainderInterval()) {
            homeFragment.updateRemainder(fragment.getContext(),calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), newInterval);
            savedData.setOldRemainderInterval(newInterval);
        }
    }

    private long calculateInterval(int id) {
        /*
        <string-array name="frequency">
            <item>Once a day</item>
            <item>Every 12 hours</item>
            <item>Every 6 hours</item>
            <item>Every 3 hours</item>
            <item>Every 1 hour</item>
        </string-array>
        */

        switch (id){
            case 0:
            //Once a day
                return AlarmManager.INTERVAL_DAY;
            case 1:
                //Every 12 hours
                return AlarmManager.INTERVAL_HALF_DAY;
            case 2:
                //Every 6 hours
                return (AlarmManager.INTERVAL_HOUR*6);
            case 3:
                //Every 3 hours
                return (AlarmManager.INTERVAL_HOUR*3);
//                return (60 * 1000);
            case 4:
                //Every 1 hours
                return AlarmManager.INTERVAL_HOUR;
        }

        return AlarmManager.INTERVAL_HOUR;
    }

    @Override
    public void saveCalculationMethodId(int id){
        savedData.setCalculationMethodId(id);
    }
    @Override
    public void saveJuristicMethodId(int id){
        savedData.setJuristicMethodId(id);
    }
    @Override
    public void saveSelectedRemainderLanguage(boolean[] selectedLanguage){
        savedData.storeRemainderLanguages(selectedLanguage);
    }
}
