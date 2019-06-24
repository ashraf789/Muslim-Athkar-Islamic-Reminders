package net.a6te.lazycoder.muslim_pro_islamicremainders;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import net.a6te.lazycoder.muslim_pro_islamicremainders.database.MyDatabase;
import net.a6te.lazycoder.muslim_pro_islamicremainders.fragments.Home;
import net.a6te.lazycoder.muslim_pro_islamicremainders.fragments.PrayerTime;
import net.a6te.lazycoder.muslim_pro_islamicremainders.fragments.Qibla;
import net.a6te.lazycoder.muslim_pro_islamicremainders.fragments.Quran;
import net.a6te.lazycoder.muslim_pro_islamicremainders.fragments.Settings;
import net.a6te.lazycoder.muslim_pro_islamicremainders.interfaces.CallAttachBaseContext;

/**
 *
 * This project is developed by : Syed Ashraf ullah
 * Year: 2018
 * Email: syedashrafullah15@gmail.com
 * Github: ashraf789
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, CallAttachBaseContext{

    private RelativeLayout navHomeRl,navPrayerRl,navQiblaRl,navQuranRl,navSettingRl,navUrlRl;

    private Fragment fragment;
    private FragmentTransaction transaction;
    private RelativeLayout selectedNav;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private View view;
    private MediaPlayer ring;
    private Intent mServiceIntent;
    private SavedData savedData;

    private NetworkStateReceiver receiver = null;
    private Context mContext;
    private MyDatabase database;

    @Override
    public void attachBaseContext(Context newBase) {
        mContext = LocaleManager.setLocale(newBase);
        super.attachBaseContext(mContext);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeAll();
        playSound();//this method will play opening sound
        this.startService(mServiceIntent);//start IntentService for fetch data from online server

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        if (savedData.getIsAppFirstTimeOpen()){
            fragment = new Settings();
            changeSelectedNavBg(findViewById(R.id.navSettingRl));
        }
        transaction.add(R.id.containerMain,fragment);
        transaction.commit();

    }

    /*
     * initializing all variable object ect
     * */
    private void initializeAll() {
        database = new MyDatabase(this);//when first time app open this will create a initial database
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


        mServiceIntent = new Intent(this, DownloadData.class);


        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);

        receiver = new NetworkStateReceiver();
        this.registerReceiver(receiver, filter);


        LocalBroadcastManager.getInstance(this).registerReceiver(connectionStatusReceiver
                ,new IntentFilter(Utils.BROADCAST_CONNECTION_STATUS));
        savedData = new SavedData(this);


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
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",getString(R.string.app_email), null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
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


    public void checkLocationPermission() {

        final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(MainActivity.this,
                    PERMISSIONS,
                    MY_PERMISSIONS_REQUEST_LOCATION);

        } else {
            //permission already granted
            //like android version < 5(lollipop) don,t need runtime permission
        }
    }

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
        ring.start();
    }

    @Override
    protected void onDestroy() {
        ring.stop();
        unregisterReceiver();
        super.onDestroy();
    }




    private void unregisterReceiver() {
        this.unregisterReceiver(receiver);
    }

    BroadcastReceiver connectionStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            if (bundle.getBoolean(Utils.DATA_CONNECTION_ENABLE)){
                    startServiceIntent();
            }
        }
    };

    public void startServiceIntent(){
        startService(mServiceIntent);
    }



}
