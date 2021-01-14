package de.frauas.oopj.project2048;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasTest2 extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String LOGTAG = "Touch Event";
    private static final int MIN_SWIPE_DISTANCE = 150;
    private float x1, x2, y1, y2;
    private GestureDetector gestureDetector;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);

        Canvas gameGrid;
        ImageView canvasBackgroundImage = (ImageView)findViewById(R.id.imageCanvas);

        /*ConstraintLayout screen = findViewById(R.id.gameLayout);
        int screenWidth = screen.getWidth();
        int screenHeight = screen.getHeight();

        int canvasHeight = canvasBackgroundImage.getHeight();
        int canvasWidth = canvasBackgroundImage.getWidth();*/

        //Bitmap mBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
        //Bitmap mBitmap = ((BitmapDrawable)canvasBackgroundImage.getDrawable()).getBitmap();
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grid4x4_background).copy(Bitmap.Config.ARGB_8888, true);
        canvasBackgroundImage.setImageBitmap(mBitmap);

        gameGrid = new Canvas(mBitmap);
        //int backgroundColour = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        //gameGrid.drawColor(backgroundColour);


        this.gestureDetector = new GestureDetector(CanvasTest2.this, this);
    }

    /**
     * Called when a touch screen event was not handled by any of the views
     * under it.  This is most useful to process touch events that happen
     * outside of your window bounds, where there is no view to receive it.
     *
     * @param event The touch screen event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        //detecting the x and y coordinated both at the stat and at the end of the screen touch event
        switch(event.getAction()){
            //start of motion coords
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            //end of motion coords
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                //evaluating the swipe
                float xDifference = x1 - x2;
                float yDifference = y1 - y2;

                if(Math.abs(xDifference) > MIN_SWIPE_DISTANCE){
                    //Horizontal swipe detected
                    if(x2>x1){
                        Toast.makeText(this, "Swipe to the right", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe to the right");
                    } else {
                        Toast.makeText(this, "Swipe to the left", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe to the left");
                    }
                }
                else if(Math.abs(yDifference) > MIN_SWIPE_DISTANCE){
                    if(y2>y1){
                        Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe down");
                    } else {
                        Toast.makeText(this, "Swipe Up", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe up");
                    }
                }
                else{
                    Toast.makeText(this, "Not enough distance", Toast.LENGTH_SHORT).show();
                }
        }


        return super.onTouchEvent(event);
    }

    /**
     * Notified when a tap occurs with the down {@link MotionEvent}
     * that triggered it. This will be triggered immediately for
     * every down event. All other events should be preceded by this.
     *
     * @param e The down motion event.
     */
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    /**
     * The user has performed a down {@link MotionEvent} and not performed
     * a move or up yet. This event is commonly used to provide visual
     * feedback to the user to let them know that their action has been
     * recognized i.e. highlight an element.
     *
     * @param e The down motion event
     */
    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * Notified when a tap occurs with the up {@link MotionEvent}
     * that triggered it.
     *
     * @param e The up motion event that completed the first tap
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
     * current move {@link MotionEvent}. The distance in x and y is also supplied for
     * convenience.
     *
     * @param e1        The first down motion event that started the scrolling.
     * @param e2        The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis that has been scrolled since the last
     *                  call to onScroll. This is NOT the distance between {@code e1}
     *                  and {@code e2}.
     * @param distanceY The distance along the Y axis that has been scrolled since the last
     *                  call to onScroll. This is NOT the distance between {@code e1}
     *                  and {@code e2}.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /**
     * Notified when a long press occurs with the initial on down {@link MotionEvent}
     * that trigged it.
     *
     * @param e The initial on down motion event that started the longpress.
     */
    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * Notified of a fling event when it occurs with the initial on down {@link MotionEvent}
     * and the matching up {@link MotionEvent}. The calculated velocity is supplied along
     * the x and y axis in pixels per second.
     *
     * @param e1        The first down motion event that started the fling.
     * @param e2        The move motion event that triggered the current onFling.
     * @param velocityX The velocity of this fling measured in pixels per second
     *                  along the x axis.
     * @param velocityY The velocity of this fling measured in pixels per second
     *                  along the y axis.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
