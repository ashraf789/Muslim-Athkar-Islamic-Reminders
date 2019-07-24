package net.a6te.lazycoder.muslim_pro_islamicremainders.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import net.a6te.lazycoder.muslim_pro_islamicremainders.CheckInternetConnection;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPPresenter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPView;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.PrayerTimePresenter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.SavedData;
import net.a6te.lazycoder.muslim_pro_islamicremainders.Utils;
import net.a6te.lazycoder.muslim_pro_islamicremainders.adapters.PrayerTimeAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrayerTime extends Fragment implements MVPView.PrayerTimeView{


    private RecyclerView prayerTimeRV;
    private View v;
    private MVPPresenter.PrayerTimePresenter presenter;
    private TextView cityTv;
    private TextView errorNoInternetTv;
    private SwipeRefreshLayout refreshLayout;
    private CheckInternetConnection internetConnectionTest;
    private MediaPlayer ring;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private SavedData savedData;

    public PrayerTime() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_prayer_time, container, false);
        initializeAll();
        return v;
    }

    /*
    * initialize all necessary variable or initialize object etc
    * */
    private void initializeAll() {
        presenter = new PrayerTimePresenter(this);
        prayerTimeRV = v.findViewById(R.id.prayerTimeRV);//prayerTime recyclerView(RV)
        prayerTimeRV.setHasFixedSize(true);
        prayerTimeRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        cityTv = v.findViewById(R.id.cityNameTv);
        errorNoInternetTv = v.findViewById(R.id.errorNoInternetTv);

        refreshLayout = v.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(refreshListener);
        internetConnectionTest = new CheckInternetConnection();
        ring= MediaPlayer.create(getContext(),R.raw.prayer_allahu_akbar);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(connectionStatusReceiver
                ,new IntentFilter(Utils.BROADCAST_CONNECTION_STATUS));

        savedData = new SavedData(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callPresenter();
        playSound();//it will play allhu akbar sound
    }


    private void callPresenter() {

        /** We have old lat and long so we can calculate by old report */
        if (savedData.getLong() != 0 && savedData.getLat() != 0){
            presenter.startCalculationPrayerTime();
        }
        if (internetConnectionTest.netCheck(getContext())){

            if (checkLocationPermission()){
                presenter.startCalculationPrayerTime();
                unVisibleErrorTv();
            }else {
                Toast.makeText(getContext(),getResources().getString(R.string.gps_setting_message), Toast.LENGTH_SHORT).show();
                visibleErrorTv(getContext().getResources().getString(R.string.gps_setting_message));
            }

        }else {
            Toast.makeText(getContext(),getResources().getString(R.string.required_data_connection), Toast.LENGTH_SHORT).show();
            errorNoInternetTv.setText(getContext().getResources().getString(R.string.required_data_connection));
            errorNoInternetTv.setVisibility(View.VISIBLE);
        }
    }

    /*
    * set Recycler view adapter
    * */
    @Override
    public void initializeRecyclerView(PrayerTimeAdapter adapter) {
        if (adapter != null) {
            prayerTimeRV.setAdapter(adapter);
        }
    }

    public void visibleErrorTv(String message){
        errorNoInternetTv.setVisibility(View.VISIBLE);
        errorNoInternetTv.setText(message);
    }
    public void unVisibleErrorTv(){
        errorNoInternetTv.setVisibility(View.GONE);
    }

    @Override
    public void setCityName(String cityName) {
        cityTv.setText(cityName);
    }


    /*
    * if GPS is not turn on this method will show a alert dialog to user to setting enable GPS
    * */
    @Override
    public void showGpsSettingAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

        // Setting Dialog Title
        alertDialog.setTitle(R.string.gps_setting_title);

        // Setting Dialog Message
        alertDialog.setMessage(R.string.gps_setting_message);

        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                getContext().startActivity(intent);

            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                visibleErrorTv(getContext().getString(R.string.gps_setting_message));
            }
        });

        // Showing Alert Message
        alertDialog.show();


    }


    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            callPresenter();
            refreshLayout.setRefreshing(false);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    public void playSound(){
        if (!ring.isPlaying()) {
            ring.start();
        }
    }
    @Override
    public void onPause() {
        ring.stop();
        super.onPause();
    }

    /*
    * Taking runtime permission for location
    * */

    public boolean checkLocationPermission() {

        final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(!hasPermissions(getContext(), PERMISSIONS)){

            ActivityCompat.requestPermissions(getActivity(),
                    PERMISSIONS,
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;
        } else {
            return true;
            //permission already granted
            //like android version < 5(lollipop) don,t need runtime permission

        }
    }

    /*
    * check is already permission granted or not
    * */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        //permission granted
                    }

                } else {
                    permissionDenied();
                }
                return;
            }

        }
    }

    /*
    * if user deny to give permission this method will be called
    * */
    private void permissionDenied() {
        Toast.makeText(getContext(), R.string.permission_denied,Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver connectionStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String message = bundle.getString(Utils.CONNECTION_STATUS);

            if (bundle.getInt(Utils.STATUS_CODE) == Utils.ALL_CONNECTED){
                unVisibleErrorTv();
                presenter.startCalculationPrayerTime();
            }else if (bundle.getInt(Utils.STATUS_CODE) == Utils.NO_CONNECTION_CODE){
                visibleErrorTv(message);
            }

        }
    };
}
