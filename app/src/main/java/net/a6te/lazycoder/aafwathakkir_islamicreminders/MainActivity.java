package net.a6te.lazycoder.aafwathakkir_islamicreminders;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Home;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.PrayerTime;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Qibla;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Quran;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Settings;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.interfaces.CallAttachBaseContext;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CallAttachBaseContext{

    private RelativeLayout navHomeRl,navPrayerRl,navQiblaRl,navQuranRl,navSettingRl,navUrlRl;

    private Fragment fragment;
    private FragmentTransaction transaction;
    private RelativeLayout selectedNav;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private View view;
    private MediaPlayer ring;


    @Override
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.setLocale(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeAll();
        playSound();//this method will play opening sound

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        transaction.add(R.id.containerMain,fragment);
        transaction.commit();

    }

    /*
    * initializing all variable object ect
    * */
    private void initializeAll() {

        ring= MediaPlayer.create(MainActivity.this,R.raw.open_app_salam);
        navHomeRl = findViewById(R.id.navHomeRl);
        navPrayerRl = findViewById(R.id.navPrayerRl);
        navQiblaRl =  findViewById(R.id.navQiblaRl);
        navQuranRl =  findViewById(R.id.navQuranRl);
        navSettingRl = findViewById(R.id.navSettingRl);
        navUrlRl =  findViewById(R.id.navUrlRl);

        navHomeRl.setOnClickListener(this);
        navPrayerRl.setOnClickListener(this);
        navQiblaRl.setOnClickListener(this);
        navQuranRl.setOnClickListener(this);
        navSettingRl.setOnClickListener(this);
        navUrlRl.setOnClickListener(this);

        fragment = new Home();
        transaction = getSupportFragmentManager().beginTransaction();

        selectedNav = findViewById(R.id.navHomeRl);//navigation default selected menu is home menu

        checkLocationPermission();//this method will take location permission from user
    }


    //navigation menu onclick listener
    //after click on a navigation menu fragment will be change
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navHomeRl:
                fragment = new Home();
                changeSelectedNavBg(findViewById(R.id.navHomeRl));
                break;
            case R.id.navPrayerRl:
                fragment = new PrayerTime();
                changeSelectedNavBg(findViewById(R.id.navPrayerRl));
                break;
            case R.id.navQiblaRl:
                fragment = new Qibla();
                changeSelectedNavBg(findViewById(R.id.navQiblaRl));
                break;
            case R.id.navQuranRl:
                fragment = new Quran();
                changeSelectedNavBg(findViewById(R.id.navQuranRl));
                break;
            case R.id.navSettingRl:
                fragment = new Settings();
                changeSelectedNavBg(findViewById(R.id.navSettingRl));
                break;
            case R.id.navUrlRl:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.WEB_URL));
                startActivity(browserIntent);

                break;
        }

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerMain,fragment);
        transaction.commit();
        closeDrawer();
    }

    //This method will change selected button background color
    // and also it will change previous selected navigation menu background to white background
    public void changeSelectedNavBg(View newSelectedNavId){
        selectedNav.setBackgroundResource(R.color.color_white);

        selectedNav = (RelativeLayout) newSelectedNavId;
        selectedNav.setBackgroundResource(R.color.cardview_shadow_start_color);
    }
    //close navigation drawer
    public void closeDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }


    public boolean checkLocationPermission() {

        String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ContextCompat.checkSelfPermission(this, String.valueOf(PERMISSIONS))
                != PackageManager.PERMISSION_GRANTED) {

            //permission is not already granted we need to request for permission

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    String.valueOf(PERMISSIONS))) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.location_permission_title)
                        .setMessage(R.string.location_permission_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        PERMISSIONS,
                        MY_PERMISSIONS_REQUEST_LOCATION);

            }

            return false;
        } else {

            //permission already granted
            //like android version < 5(lollipop) don,t need runtime permission
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
                    if (ContextCompat.checkSelfPermission(this,
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

    private void permissionDenied() {
        Toast.makeText(this, R.string.permission_denied,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttachBaseContext(Context context){

        finish();
        startActivity(getIntent());
    }



    public void playSound(){
//        ring.start();
    }

    @Override
    protected void onDestroy() {
        ring.stop();
        super.onDestroy();
    }
}
