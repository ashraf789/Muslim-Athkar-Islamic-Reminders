package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;

import java.io.File;

public class HomePresenter implements MVPPresenter.HomePresenter {

    private Fragment fragment;
    private Context context;
    private MVPView.HomeView mvpView;

    public HomePresenter(Fragment fragment) {
        this.fragment = fragment;
        mvpView = (MVPView.HomeView) fragment;
        context = fragment.getContext();
    }

    @Override
    public void createBitMap(View layoutView) {
        mvpView.storeBitMapImage(createBitmapFromView(layoutView));
    }

    @Override
    public void createIntentToShareImage(File filePath){
        mvpView.shareImage(getShareIntent(filePath));

    }


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

}
