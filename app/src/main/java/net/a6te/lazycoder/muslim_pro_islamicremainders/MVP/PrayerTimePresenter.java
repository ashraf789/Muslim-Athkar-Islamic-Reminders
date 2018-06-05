package net.a6te.lazycoder.muslim_pro_islamicremainders.MVP;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;


import net.a6te.lazycoder.muslim_pro_islamicremainders.GPSTracker;
import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.SavedData;
import net.a6te.lazycoder.muslim_pro_islamicremainders.adapters.PrayerTimeAdapter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.PrayerTimeModel;
import net.alhazmy13.PrayerTimes.PrayerTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class PrayerTimePresenter implements MVPPresenter.PrayerTimePresenter{
    private MVPView.PrayerTimeView MVPView;
    private  Fragment fragment;
    private PrayerTimeAdapter adapter;
    private ArrayList<PrayerTimeModel> prayerTimes;

    private GPSTracker gps;
    private double latitude, longitude;
    private String city = null;
    private int calculationMethodId;
    private int juristicMethodId;
    private SavedData savedData;
    private Context mContext;


    public PrayerTimePresenter(Fragment fragment) {
        this.fragment = fragment;
        mContext = fragment.getContext();
        this.MVPView = (net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPView.PrayerTimeView) fragment;
        prayerTimes = new ArrayList<>();
        savedData = new SavedData(fragment.getContext());
        calculationMethodId = savedData.getCalculationMethodId();
        juristicMethodId = savedData.getJuristicMethodId();
        city = savedData.getUserCity();
        latitude = savedData.getLat();
        longitude = savedData.getLong();
    }

    @Override
    public void startCalculationPrayerTime() {

        try {
            gps = new GPSTracker(mContext);
            getLocation();// if gps setting is not available this method will call alert dialog message from prayerTime fragment so must call it from main Thread

            PerformBackground background = new PerformBackground();
            background.execute();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    class PerformBackground extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {

            calculateTime();

            try {
                Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
                List<Address> addresses = gcd.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0) {

                    if (!addresses.get(0).getLocality().isEmpty()) {
                        savedData.saveUserCity(addresses.get(0).getLocality());
                        city = savedData.getUserCity();
                    }
                    return true;
                }else return false;
            } catch (IOException e) {
                e.printStackTrace();

            }

            return true;

        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);

            adapter = new PrayerTimeAdapter(prayerTimes);
            MVPView.initializeRecyclerView(adapter);
            MVPView.setCityName(city);

        }
    }


    public void calculateTime(){

        prayerTimes = new ArrayList<>();

        // our prayer_calculation_method and juristic_method(from string) index number must be same as bellow format
        //example for calculation method first index will be jafari = 0 position

        /*
        * // Calculation Methods
        public static final int Jafari = 0; // Ithna Ashari
        public static final int Karachi = 1; // University of Islamic Sciences, Karachi
        public static final int ISNA = 2; // Islamic Society of North America (ISNA)
        public static final int MWL = 3; // Muslim World League (MWL)
        public static final int Makkah = 4; // Umm al-Qura, Makkah
        public static final int Egypt = 5; // Egyptian General Authority of Survey
        public static final int Custom = 6; // Custom Setting
        public static final int Tehran = 7; // Institute of Geophysics, University of Tehran

        final class Juristic{
            // Juristic Methods
            public static final int Shafii = 0; // Shafii (standard)
            public static final int Hanafi = 1; // Hanafi
        }*/


        PrayerTime prayers = new PrayerTime();
        prayers.setTimeFormat(PrayerTime.TimeFormat.Time12);
        prayers.setCalcMethod(calculationMethodId);//PrayerTime.Calculation.Karachi
        prayers.setAsrJuristic(juristicMethodId);//PrayerTime.Juristic.Shafii
        prayers.setAdjustHighLats(PrayerTime.Adjusting.AngleBased);
        prayers.setOffsets(new int[]{0, 0, 0, 0, 0, 0, 0});


        ArrayList<String> times = prayers.getPrayerTimes(Calendar.getInstance(),
                savedData.getLat(), savedData.getLong(), getTimeZone());

        prayerTimes.add(new PrayerTimeModel(mContext.getString(R.string.fajr),times.get(PrayerTime.Time.Fajr)));
        prayerTimes.add(new PrayerTimeModel(mContext.getString(R.string.sunrise),times.get(PrayerTime.Time.Sunrise)));
        prayerTimes.add(new PrayerTimeModel(mContext.getString(R.string.dhuhr),times.get(PrayerTime.Time.Dhuhr)));
        prayerTimes.add(new PrayerTimeModel(mContext.getString(R.string.asr),times.get(PrayerTime.Time.Asr)));
        prayerTimes.add(new PrayerTimeModel(mContext.getString(R.string.magrib),times.get(PrayerTime.Time.Maghrib)));
        prayerTimes.add(new PrayerTimeModel(mContext.getString(R.string.isha),times.get(PrayerTime.Time.Isha)));

    }

    private boolean getLocation() {

        try {
            // check if GPS enabled
            if (gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                savedData.saveLongitude((float) longitude);
                savedData.saveLat((float) latitude);
                return true;
            } else {
                MVPView.showGpsSettingAlert();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }

    /*
     * this method will calculate time zone
     * with our required double format
     * */
    public double getTimeZone() {


        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();
        Double timeZone = Double.valueOf(TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS));

        return timeZone;
    }

    public void onDestroy(){

    }

}
