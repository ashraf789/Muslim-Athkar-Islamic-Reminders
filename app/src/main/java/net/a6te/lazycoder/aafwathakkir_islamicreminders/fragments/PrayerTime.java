package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.CheckInternetConnection;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.PrayerTimePresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MainActivity;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.PrayerTimeAdapter;


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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callPresenter();
        playSound();//it will play allhu akbar sound
    }

    private void callPresenter() {
        if (internetConnectionTest.netCheck(getContext())){
            presenter.startCalculation();
            errorNoInternetTv.setVisibility(View.GONE);
        }else {
            Toast.makeText(getContext(),getResources().getString(R.string.required_data_connection), Toast.LENGTH_SHORT).show();
            errorNoInternetTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initializeRecyclerView(PrayerTimeAdapter adapter) {
        if (adapter != null) {
            prayerTimeRV.setAdapter(adapter);
        }
    }

    @Override
    public void setCityName(String cityName) {
        cityTv.setText(cityName);
    }


//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event) {
//
//
//        // get the event and remove drom sticky
////        MessageEvent stickyEvent = EventBus.getDefault().removeStickyEvent(MessageEvent.class);
////
////        if(stickyEvent != null) {
////            if (event.isEmptyValue()){
////                Toast.makeText(getContext(),getResources().getString(R.string.required_data_connection),Toast.LENGTH_SHORT).show();
////                errorNoInternetTv.setVisibility(View.VISIBLE);
////
////            }else if (event.getEventName().equals(Utils.CITY_NAME)){
////                errorNoInternetTv.setVisibility(View.GONE);
////                cityTv.setText(event.getMessage());
////
////            }
////        }
//
//    };


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

    public void playSound(){
        if (!ring.isPlaying()) {
//            ring.start();
        }
    }
    @Override
    public void onPause() {
        ring.stop();
        super.onPause();
    }

}
