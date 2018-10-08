package net.a6te.lazycoder.muslim_pro_islamicremainders.MVP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;

import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.SavedData;
import net.a6te.lazycoder.muslim_pro_islamicremainders.database.MyDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class HomePresenter implements MVPPresenter.HomePresenter {

    private Context context;
    private MVPView.HomeView mvpView;
    private SavedData savedData;
    private MyDatabase myDatabase;


    public HomePresenter(Fragment fragment) {
        mvpView = (MVPView.HomeView) fragment;
        context = fragment.getContext();
        savedData = new SavedData(context);
        myDatabase = new MyDatabase(context);


    }

    @Override
    public void createBitMap(View layoutView) {
        mvpView.storeBitMapImage(createBitmapFromView(layoutView));
    }

    @Override
    public void createIntentToShareImage(File filePath){
        mvpView.shareImage(getShareIntent(filePath));

    }

    @Override
    public void initializeRemainder(){

        long newInterval = savedData.getNewRemainderInterval();
        long oldInterval = savedData.getOldRemainderInterval();

        if (newInterval != oldInterval) {

            int hour = savedData.getAppStartHour();
            int mint = savedData.getAppStartMin();

            mvpView.updateRemainder(context, hour, mint, newInterval);
            savedData.setOldRemainderInterval(newInterval);
        }
    }

    /*
    * Convert text{verse} to bitmap
    * */
    //this method will create a bitmap image from given view
    public Bitmap createBitmapFromView(View layoutView){
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(layoutView.getWidth(), layoutView.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =layoutView.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        layoutView.draw(canvas);
        return returnedBitmap;
    }

    //this method will create a intent for share our image
    public Intent getShareIntent(File filePath){

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                context.getResources().getString(R.string.app_signature)+" "+context.getResources().getString(R.string.app_google_play_url)+".");

        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(filePath));

        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        return shareIntent;
    }

    /*
     * this method will make ready data for new atkhar image
     * */
    @Override
    public void prepareAtkhar(){

        int lastAthkarId = savedData.getLastAthkarId();
        String tableName = savedData.getAthkarTableName();//get saved athkar table name
        getAtkhar(lastAthkarId,tableName);

    }

    /*
     * if user press create button then we don't need to wait for new date also we will change old information
     * */
    @Override
    public void prepareAtkharBtnPress(){

        int lastAtkharId = savedData.getLastAthkarId();

        lastAtkharId += 1;
        savedData.setLastAthkarId(lastAtkharId);
        getAtkhar(lastAtkharId);
    }

    public void getAtkhar(int id){

        String tableName = getTableName();
        int lastDataId = myDatabase.getLastDataId(tableName);
        String atkhar;

        if (lastDataId >= id){
            //still available new data
            atkhar = myDatabase.getAtkhar(tableName, id);
        }else {

            //no new data available we already seen last atkhar lets start again from first
            id = 0;
            atkhar = myDatabase.getAtkhar(tableName, id);
            savedData.setLastAthkarId(id);
        }

        mvpView.setTodayImage(atkhar);
    }

    public void getAtkhar(int id, String tableName){

        if (tableName == null){
            tableName = getTableName();
        }
        int lastDataId = myDatabase.getLastDataId(tableName);

        Log.d("TEST", "total atkar or table "+tableName+" athkar = "+lastDataId);
        Log.d("TEST", "get athkar ID : "+lastDataId);
        String atkhar;

        if (lastDataId >= id){
            //still available new data
            atkhar = myDatabase.getAtkhar(tableName, id);
        }else {

            //no new data available we already seen last atkhar lets start again from first
            id = 0;
            atkhar = myDatabase.getAtkhar(tableName, id);
            savedData.setLastAthkarId(id);
        }

        mvpView.setTodayImage(atkhar);
    }

    public String getTableName(){
        List<String > tableLanguages = Arrays.asList(context.getResources().getStringArray(R.array.remainder_language_table_name));

        int size = tableLanguages.size();
        boolean[] remainderLanguages = savedData.getRemainderLanguages(size);

        ArrayList<Integer> indexNoOfSelectedLanguage = new ArrayList<>();

        for (int i = 0; i < size; i++){
            if (remainderLanguages[i]){
                indexNoOfSelectedLanguage.add(i);
            }
        }
        size = indexNoOfSelectedLanguage.size();
        Random random= new Random();
        int randomSelectedLanguageIndex = random.nextInt(size);

        String tableName = tableLanguages.get(indexNoOfSelectedLanguage.get(randomSelectedLanguageIndex));
        savedData.saveAthkarTableName(tableName);//new image generated table name
        return tableName;
    }
}
