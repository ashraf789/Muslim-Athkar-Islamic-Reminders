package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.DrawCompass;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.QiblaPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Qibla extends Fragment implements MVPView.QiblaView{

    private RelativeLayout directionContainer;
    private DrawCompass rose;
    View view;
    TextView qiblaDistance, qiblaDegree;

    private MVPPresenter.QiblaPresenter presenter;
    private SwipeRefreshLayout refreshLayout;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private Context context;
    private TextView errorTv;

    public Qibla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_qibla, container, false);
        initializeAll();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        directionContainer.addView(rose);
        rose.invalidate();

        checkLocationPermission();
        refreshLayout.setOnRefreshListener(refreshListener);
    }



    private void initializeAll() {
        qiblaDistance = view.findViewById(R.id.idDistance);
        qiblaDegree = view.findViewById(R.id.idDegree);

        directionContainer = view.findViewById(R.id.cantainer_layout);
        rose = new DrawCompass(context);
        presenter = new QiblaPresenter(this,context);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        errorTv = view.findViewById(R.id.errorTv);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void setQiblaInfo(String qiblaDegree, String qiblaDistance) {
        this.qiblaDegree.setText(qiblaDegree);
        this.qiblaDistance.setText(qiblaDistance);

    }

    //this will generate new compass direction
    @Override
    public void changeCompassDirection(float directionsNorth, float directionsQibla, float degree) {
        rose.setDirections(directionsNorth, directionsQibla, degree);
    }

    @Override
    public void notifyNoInternetConnection() {
        Toast.makeText(context, "You have no Internet connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSensorNotAvailable(){
        Snackbar.make(view,"Sorry magnetic sensor not available",Snackbar.LENGTH_SHORT).show();
        errorTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyNotEnabledGPS() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

            presenter.startCalculatingLocation();
            refreshLayout.setRefreshing(false);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void permission(View view) {
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //permission is not already granted we need to request for permission

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(context)
                        .setTitle("Location Permission ")
                        .setMessage("Access this device location")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            }

            return false;
        } else {

            //permission already granted
            //like 4.0,4.9 don,t need runtime permission
            permissionGranted();
            return true;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        permissionGranted();
                    }

                } else {
                    permissionDenied();
                }
                return;
            }

        }
    }


    public void permissionGranted(){
        presenter.startCalculatingLocation();
    }

    public void permissionDenied(){
        Toast.makeText(context,"please refresh screen to give permission",Toast.LENGTH_SHORT).show();

    }



}
