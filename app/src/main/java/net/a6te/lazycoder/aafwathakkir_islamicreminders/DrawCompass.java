package net.a6te.lazycoder.aafwathakkir_islamicreminders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



public class DrawCompass extends View {


    private float directionNorth = 0;
    private float directionQibla = 0;

    private Bitmap compassBackground;
    private Bitmap compassNeedle;
    private Matrix rotateNeedle = new Matrix();
    private int width = 240;
    private int height = 240;
    private float centre_x = width * 0.5f;
    private float centre_y = height * 0.5f;
    private Context context;

    //all constructor
    public DrawCompass(Context context) {
        super(context);
        this.context = context;

        initCompassView();
        this.context = context;
    }
    public DrawCompass(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        initCompassView();
    }
    public DrawCompass(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initCompassView();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    //this method will initialize the compass view
    private void initCompassView() {
        compassNeedle = BitmapFactory.decodeResource(getResources(), R.drawable.qibla_needle);
        compassBackground = BitmapFactory.decodeResource(getResources(), R.drawable.compass_view);


        width = compassBackground.getWidth()*2;
        height = compassBackground.getHeight()*2;

        centre_x = width  * 0.5f;
        centre_y = height * 0.5f;
        rotateNeedle.postTranslate(centre_x - compassNeedle.getWidth()/2, centre_y - compassNeedle.getHeight()/2);
        invalidate();
    }

    //on this method compass direction will be set
    //it will take three parameter northDirection, QiblaDirection and degree
    public void setDirections(float directionsNorth, float directionsQibla, float degree) {
        this.directionNorth = directionsNorth;
        this.directionQibla = directionsQibla;
        rotateNeedle = new Matrix();

        rotateNeedle.postRotate(degree, compassNeedle.getWidth()/2, compassNeedle.getHeight()/2);
        rotateNeedle.postTranslate(centre_x - compassNeedle.getWidth()/2, centre_y - compassNeedle.getHeight()/2);
        invalidate();
    }

    // now this method will draw the compass by using compass view
    //which we already created on initCompassView() method
    @Override
    protected void onDraw(Canvas canvas) {

        Paint p = new Paint();
        canvas.rotate(-directionNorth, centre_x, centre_y);
        canvas.drawBitmap(compassBackground, compassBackground.getWidth()/2, compassBackground.getHeight()/2, p);
        canvas.drawBitmap(compassNeedle, rotateNeedle, p);
    }
}
