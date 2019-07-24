package net.a6te.lazycoder.muslim_pro_islamicremainders.MVP;

import android.app.AlarmManager;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.SavedData;
import net.a6te.lazycoder.muslim_pro_islamicremainders.adapters.SpinnerAdapter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.fragments.Home;
import net.a6te.lazycoder.muslim_pro_islamicremainders.interfaces.CallAttachBaseContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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

        adapter = new ArrayAdapter<>(fragment.getContext(), android.R.layout.simple_spinner_item);
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
        <item>Every 1 hour</item>
        <item>Every 3 hours</item>
        <item>Every 6 hours</item>
        <item>Every 12 hours</item>
        <item>Once a day</item>
        */

        switch (id){
            case 0:
                //Every 1 hours
                return AlarmManager.INTERVAL_HOUR;
            case 1:
                //Every 3 hours
                return (AlarmManager.INTERVAL_HOUR*3);
            case 2:
                //Every 6 hours
                return (AlarmManager.INTERVAL_HOUR*6);
            case 3:
                //Every 12 hours
                return AlarmManager.INTERVAL_HALF_DAY;
            case 4:
                //Once a day
                return AlarmManager.INTERVAL_DAY;
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
        generateNewAthkarTable();
    }

    public void generateNewAthkarTable(){
        List<String > tableLanguages = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.remainder_language_table_name));

        int size = tableLanguages.size();
        boolean[] remainderLanguages = savedData.getRemainderLanguages(size);

        ArrayList<Integer> indexNoOfSelectedLanguage = new ArrayList<>();

        for (int i = 0; i < size; i++){
            if (remainderLanguages[i]){
                indexNoOfSelectedLanguage.add(i);
            }
        }
        size = indexNoOfSelectedLanguage.size();
        Random random= new Random();
        int randomSelectedLanguageIndex = random.nextInt(size);

        String tableName = tableLanguages.get(indexNoOfSelectedLanguage.get(randomSelectedLanguageIndex));
        savedData.saveAthkarTableName(tableName);//new image generated table name
    }
}
