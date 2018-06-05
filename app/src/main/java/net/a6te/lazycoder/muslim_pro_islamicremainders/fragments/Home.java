package net.a6te.lazycoder.muslim_pro_islamicremainders.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.TextViewCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.HomePresenter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPPresenter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPView;
import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.Remainder.AlarmReceiver;
import net.a6te.lazycoder.muslim_pro_islamicremainders.Remainder.NotificationScheduler;
import net.a6te.lazycoder.muslim_pro_islamicremainders.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */


public class Home extends Fragment implements View.OnClickListener, MVPView.HomeView{


    private View view;
    private TextView autoSizeTv;


    private RelativeLayout createImageRL;//relative layout that we will convert to an image bitmap
    private ImageView shareIvBtn;
    private Button createNewImageBtn;
    private String appName;
    private File imageDirectory;
    private String imageName;
    private MVPPresenter.HomePresenter presenter;
    private MediaPlayer ring;
    public static final int SHARE_IMAGE_REQUEST_CODE=101;
    private Set<String > test;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        initializeAll();

        return view;
    }



    private void initializeAll() {
        autoSizeTv = view.findViewById(R.id.atkharTv);
        createImageRL = view.findViewById(R.id.createImageRL);
        shareIvBtn = view.findViewById(R.id.shareIvBtn);
        createNewImageBtn = view.findViewById(R.id.createNewImageBtn);



        appName = getContext().getResources().getString(R.string.app_title);
        imageDirectory = new File(Environment.getExternalStorageDirectory() + "/"+appName+"/");
        imageName = "Athkar.jpg";

        presenter = new HomePresenter(this);

        shareIvBtn.setOnClickListener(this);
        createNewImageBtn.setOnClickListener(this);
        ring= MediaPlayer.create(getContext(),R.raw.shared_thank_you);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(autoSizeTv, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(connectionStatusReceiver
                ,new IntentFilter(Utils.BROADCAST_CONNECTION_STATUS));
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(messageReceiver
                ,new IntentFilter(Utils.BROADCAST_ACTION));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeAdds();
        //initialize remainder
        presenter.initializeRemainder();
        presenter.prepareAtkhar();
    }

    private void initializeAdds() {
        MobileAds.initialize(getContext(),
                getString(R.string.add_publish_id));

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void updateRemainder(Context context,int hour, int mint, long interval){

        NotificationScheduler.setReminder(context, AlarmReceiver.class, hour, mint,interval);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shareIvBtn:
                presenter.createBitMap(createImageRL);//this will create new image
                shareImageBtn();
                break;
            case R.id.createNewImageBtn:
                presenter.prepareAtkharBtnPress();
                break;
        }
    }

    private void shareImageBtn(){
        File filePath = new File(imageDirectory,"/"+imageName);

        presenter.createIntentToShareImage(filePath);
    }

    //this is method will call from presenter it will take a intent then it will make share event
    @Override
    public void shareImage(Intent shareIntent){

        Intent intent2 = Intent.createChooser(shareIntent, getString(R.string.share_via));
        startActivityForResult(intent2,SHARE_IMAGE_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHARE_IMAGE_REQUEST_CODE ){

            if (resultCode == RESULT_OK){
                playSound();
                Log.d("TEST", "onActivityResult: play sound ");

            }

            Log.d("TEST", "onActivityResult: "+resultCode);
        }
    }


    @Override
    public void setTodayImage(String  data){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            autoSizeTv.setText(Html.fromHtml(data,Html.FROM_HTML_MODE_LEGACY));
        } else {
            autoSizeTv.setText(Html.fromHtml(data));
        }
    }
    @Override
    public void storeBitMapImage(Bitmap bitmap){

        File folderDirectory = imageDirectory;
        //create storage directories, if they don't exist
        folderDirectory.mkdirs();
        try {
            String filePath = folderDirectory.toString() +"/"+imageName;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            //choose another format if PNG doesn't suit you
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            bos.flush();
            bos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    BroadcastReceiver connectionStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String message = bundle.getString(Utils.CONNECTION_STATUS);

//            if (bundle.getInt(Utils.STATUS_CODE) == Utils.ALL_CONNECTED){
//                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
//
//            }else if (bundle.getInt(Utils.STATUS_CODE) == Utils.NO_CONNECTION_CODE){
//                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
//            }

        }
    };

    public void playSound(){
        ring= MediaPlayer.create(getContext(),R.raw.shared_thank_you);
        ring.start();

    }

    @Override
    public void onResume() {
        ring= MediaPlayer.create(getContext(),R.raw.shared_thank_you);
        super.onResume();
    }

    @Override
    public void onPause() {
        ring.stop();
        super.onPause();
    }

    //broadcast receiver
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isUpdateData = intent.getBooleanExtra(Utils.EXTENDED_IS_UPDATE_DATA,false);
            //new data update
            if (isUpdateData){
                presenter.prepareAtkhar();

            }
        }
    };
}
