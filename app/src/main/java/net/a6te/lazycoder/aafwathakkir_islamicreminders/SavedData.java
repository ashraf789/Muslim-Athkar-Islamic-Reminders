package net.a6te.lazycoder.aafwathakkir_islamicreminders;

import android.content.Context;
import android.content.SharedPreferences;

public class SavedData {
    private static final String APP_PREFS_NAME = "SavedData";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String APP_LANGUAGE_SELECTED_ID ="appLanguageSelectedId";
    private static final String REMAINDER_LANGUAGE_SELECTED_ID ="remainderLanguageSelectedId";
    private static final String FREQUENCY_SELECTED_ID = "frequencyId";
    private static final String CALCULATION_METHOD_ID = "calculationMethodId";
    private static final String JURISTIC_METHOD_ID = "juristicMethodId";
    private static final String HOUR = "hour";
    private static final String MIN = "min";

    private static final String OLD_INTERVAL = "oldInterval";
    private static final String NEW_INTERVAL = "newInterval";

    private static final String REMAINDER_LANGUAGES = "remainderLanguages";


    public SavedData(Context context)
    {
        this.appSharedPrefs = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public void setAppLanguageSelectedId(int id){
        prefsEditor.putInt(APP_LANGUAGE_SELECTED_ID, id);
        prefsEditor.commit();
    }
    public int getAppLanguageSelectedId() {
        return appSharedPrefs.getInt(APP_LANGUAGE_SELECTED_ID,0);
    }

    public void setRemainderLanguageSelectedId(int id){
        prefsEditor.putInt(REMAINDER_LANGUAGE_SELECTED_ID, id);
        prefsEditor.commit();
    }

    public int getRemainderLanguageSelectedId() {
        return appSharedPrefs.getInt(REMAINDER_LANGUAGE_SELECTED_ID,0);
    }
    public void setFrequencySelectedId(int id){
        prefsEditor.putInt(FREQUENCY_SELECTED_ID, id);
        prefsEditor.commit();
    }
    public int getFrequencySelectedId() {
        return appSharedPrefs.getInt(FREQUENCY_SELECTED_ID,0);
    }
    public void setCalculationMethodId(int id){
        prefsEditor.putInt(CALCULATION_METHOD_ID, id);
        prefsEditor.commit();
    }

    //default value of calculation method id 5 [5 is the index number of egipt]
    public int getCalculationMethodId() {
        return appSharedPrefs.getInt(CALCULATION_METHOD_ID,5);
    }
    public void setJuristicMethodId(int id){
        prefsEditor.putInt(JURISTIC_METHOD_ID, id);
        prefsEditor.commit();
    }
    public int getJuristicMethodId() {
        return appSharedPrefs.getInt(JURISTIC_METHOD_ID,0);
    }

    //when user change remainder frequency that time will be starting time for remainder
    //default value is 9am 0min
    public int getAppStartHour(){
        return appSharedPrefs.getInt(HOUR,9);
    }

    public void setAppStartHour(int hour){
        prefsEditor.putInt(HOUR, hour);
        prefsEditor.commit();
    }

    public int getAppStartMin(){
        return appSharedPrefs.getInt(MIN,0);
    }

    public void setAppStartMin(int min){
        prefsEditor.putInt(MIN, min);
        prefsEditor.commit();
    }


    public long getNewRemainderInterval(){
        return appSharedPrefs.getLong(NEW_INTERVAL,(24*60 * 60 * 1000));//(24*60 * 60 * 1000) = 24 interval is once a day remainder
    }

    public void setNewRemainderInterval(long inteval){
        prefsEditor.putLong(NEW_INTERVAL, inteval);
        prefsEditor.commit();
    }


    public long getOldRemainderInterval(){
        return appSharedPrefs.getLong(OLD_INTERVAL,0);//o means didn't set yet
    }

    public void setOldRemainderInterval(long inteval){
        prefsEditor.putLong(OLD_INTERVAL, inteval);
        prefsEditor.commit();
    }


    public void storeRemainderLanguages(boolean[] array) {

        prefsEditor.putInt(REMAINDER_LANGUAGES, array.length);

        for(int i=0;i<array.length;i++)
            prefsEditor.putBoolean(REMAINDER_LANGUAGES + i, array[i]);

        prefsEditor.commit();
    }

    public boolean[] getRemainderLanguages(int defaultSize) {

        int size = appSharedPrefs.getInt(REMAINDER_LANGUAGES, defaultSize);
        boolean array[] = new boolean[size];
        boolean allFalse = true;
        for(int i=0;i<size;i++) {


            array[i] = appSharedPrefs.getBoolean(REMAINDER_LANGUAGES + i, false);

            if (array[i]) {
                allFalse = false;
            }
        }

        if (allFalse){
            array[0] = true;//if no language select then english index position[0] will be true
        }
        return array;
    }

//    public boolean storeRemainderLanguages(Boolean[] array) {
//
//        prefsEditor.putInt(REMAINDER_LANGUAGES, array.length);
//
//        for(int i=0;i<array.length;i++)
//            prefsEditor.putBoolean(REMAINDER_LANGUAGES + i, array[i]);
//
//        return prefsEditor.commit();
//    }
//
//    public Boolean[] getRemainderLanguages(int defaultSize) {
//
//        int size = appSharedPrefs.getInt(REMAINDER_LANGUAGES, defaultSize);
//        Boolean array[] = new Boolean[size];
//        for(int i=0;i<size;i++)
//            array[i] = appSharedPrefs.getBoolean(REMAINDER_LANGUAGES + i, false);
//
//        return array;
//    }



}
