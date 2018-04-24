package net.a6te.lazycoder.aafwathakkir_islamicreminders;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Home;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.PrayerTime;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Qibla;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Quran;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments.Settings;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout navHomeRl,navPrayerRl,navQiblaRl,navQuranRl,navSettingRl,navUrlRl;

    private Fragment fragment;
    private FragmentTransaction transaction;
    private RelativeLayout selectedNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeAll();

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

    }

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
                changeSelectedNavBg(findViewById(R.id.navUrlRl));

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

}
