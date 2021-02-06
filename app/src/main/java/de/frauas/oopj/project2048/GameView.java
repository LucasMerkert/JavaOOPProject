package de.frauas.oopj.project2048;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import java.util.jar.Attributes;

public class GameView extends androidx.appcompat.widget.AppCompatImageView {

    private Paint testPaint;
    private Bitmap testBitmap;
    private Canvas testcanvas;
    private int vWidth;
    private int vHeight;
    private Context context;

    public GameView(Context context, AttributeSet attributesSet) {
        super(context, attributesSet);
        setupPaint();
        this.context = context;



    }

    private void setupPaint() {

        testPaint = new Paint();
        //testPaint = ResourcesCompat.getColor(getResources(),
          //      R.color.colorRectangle, null);
        testPaint.setColor(Color.GREEN);
    }

    public void drawRectangle() {
        //vWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        //vHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int vWidth = size.x;
        int vHeight = size.y;


        Log.d("GameView", vWidth + " " + vHeight);

        Log.d("GameView", "drawing Rect");

        testBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        this.setImageBitmap(testBitmap);
        testcanvas = new Canvas(testBitmap);



        Rect rectangle = new Rect(0,0,vWidth,vHeight);
        testcanvas.drawRect(rectangle, testPaint);
        Log.d("GameView", "drew Rect");
    }

    public Canvas getCanvas(){
        return this.testcanvas;
    }


}
