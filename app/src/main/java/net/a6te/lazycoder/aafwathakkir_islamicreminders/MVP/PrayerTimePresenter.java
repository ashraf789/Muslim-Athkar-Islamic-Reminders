package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;


import net.a6te.lazycoder.aafwathakkir_islamicreminders.GPSTracker;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.PrayerTimeAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.PrayerTimeModel;
import net.alhazmy13.PrayerTimes.PrayerTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class PrayerTimePresenter implements MVPPresenter.PrayerTimePresenter{
    private MVPView.PrayerTimeView MVPView;
    private Fragment fragment;
    private PrayerTimeAdapter adapter;
    private ArrayList<PrayerTimeModel> prayerTimes;

    private GPSTracker gps;
    private double latitude, longitude;
    private String city = null;


    public PrayerTimePresenter(Fragment fragment) {
        this.fragment = fragment;

        this.MVPView = (net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView.PrayerTimeView) fragment;
        prayerTimes = new ArrayList<>();
    }

    @Override
    public void startCalculation() {
        gps = new GPSTracker(fragment.getContext());

        getLocation();
        PerformBackground background = new PerformBackground();
        background.execute();

    }

    class PerformBackground extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {

            calculateTime();
            getCity();

            try {
                Geocoder gcd = new Geocoder(fragment.getContext(), Locale.getDefault());
                List<Address> addresses = gcd.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0) {
                    city = addresses.get(0).getLocality();
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

//            if (aVoid){
//                MVPView.setCityName(city);
//
//            }else {
//                MVPView.setCityName("null");
//            }
//


        }
    }

    private void getCity() {



    }

    public void calculateTime(){

        prayerTimes = new ArrayList<>();

        PrayerTime prayers = new PrayerTime();
        prayers.setTimeFormat(PrayerTime.TimeFormat.Time12);
        prayers.setCalcMethod(PrayerTime.Calculation.Karachi);
        prayers.setAsrJuristic(PrayerTime.Juristic.Shafii);
        prayers.setAdjustHighLats(PrayerTime.Adjusting.AngleBased);
        prayers.setOffsets(new int[]{0, 0, 0, 0, 0, 0, 0});


        ArrayList<String> times = prayers.getPrayerTimes(Calendar.getInstance(),
                latitude, longitude, getTimeZone());

        prayerTimes.add(new PrayerTimeModel("Fajr",times.get(PrayerTime.Time.Fajr)));
        prayerTimes.add(new PrayerTimeModel("Sunrise",times.get(PrayerTime.Time.Sunrise)));
        prayerTimes.add(new PrayerTimeModel("Dhuhr",times.get(PrayerTime.Time.Dhuhr)));
        prayerTimes.add(new PrayerTimeModel("Asr",times.get(PrayerTime.Time.Asr)));
        prayerTimes.add(new PrayerTimeModel("Maghrib",times.get(PrayerTime.Time.Maghrib)));
        prayerTimes.add(new PrayerTimeModel("Isha",times.get(PrayerTime.Time.Isha)));

    }

    private boolean getLocation() {

        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            return true;
        } else {
            gps.showSettingsAlert();
            return false;
        }
    }

    /*
     * this method will calculate time zone
     * with our required double format
     * */
    public double getTimeZone() {

        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d.%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

        return Double.valueOf(offset);
    }

}
