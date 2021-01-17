package de.frauas.oopj.project2048;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class GameCanvas extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private Grid gameGrid;
    private static final String LOGTAG = "Touch Event";
    private static final int MIN_SWIPE_DISTANCE = 150;
    private float x1, x2, y1, y2;
    private GestureDetector gestureDetector;
    private Canvas gameCanvas;
    private CanvasHandler canvasHandler;
    private Bitmap background;
    private static int q0, q1, q2, q3, TILE_LENGTH;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_canvas);

        gameGrid = new Grid(4,4);
        ImageView canvasBackgroundImage = findViewById(R.id.imageCanvas);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.grid4x4_background).copy(Bitmap.Config.ARGB_8888, true);
        canvasBackgroundImage.setImageBitmap(background);
        gameCanvas = new Canvas(background);
        canvasHandler = new CanvasHandler(gameCanvas);
        gameCanvas = canvasHandler.drawGridToCanvas(gameGrid,background);
        gestureDetector = new GestureDetector(GameCanvas.this, this);

        q0 = 0;
        q1 = (int)(0.25d * gameCanvas.getHeight());
        q2 = (int)(0.50d * gameCanvas.getHeight());
        q3 = (int)(0.75d * gameCanvas.getHeight());
        TILE_LENGTH = (int)(0.25d * gameCanvas.getHeight());
        Log.d("Canvas Quantiles", "q1=" + q1);
        Log.d("Canvas Quantiles", "q2=" + q2);
        Log.d("Canvas Quantiles", "q3=" + q3);
        Log.d("Canvas Quantiles", "TILE_LENGTH=" + TILE_LENGTH);

        drawTileAtLocation(2,3);
    }

    private void drawTileAtLocation(int xpos, int ypos){
        Log.d("Tile Coords", "xpos=" + xpos);
        Log.d("Tile Coords", "ypos=" + ypos);

        Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _paint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));

        gameCanvas.drawRect(xpos/4.0f * gameCanvas.getWidth(),
                ypos/4.0f * gameCanvas.getHeight(),
                xpos/4.0f * gameCanvas.getWidth() + TILE_LENGTH,
                ypos/4.0f * gameCanvas.getHeight() + TILE_LENGTH,
                _paint);
    }

    int getYCoords(int ypos){
        return 1;
    }

    int getXCoords(int xpos){
        return 1;
    }

    /**
     * Called when a touch screen event was not handled by any of the views
     * under it.
     * Detects the x and y coordinates at the start and at the end of the user touching the screen and determines in which direction (UP, DOWN, LEFT, RIGHT) the swipe occured.
     *
     * @param event The touch screen event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        //detecting the x and y coordinated both at the start and at the end of the screen touch
        switch (event.getAction()) {
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

                if (Math.abs(xDifference) > MIN_SWIPE_DISTANCE && Math.abs(xDifference) > Math.abs(yDifference)) {
                    //Horizontal swipe detected
                    if (x2 > x1) {
                        Toast.makeText(this, "Swipe to the right", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe to the right");
                        gameGrid.swipeRight();
                    } else {
                        Toast.makeText(this, "Swipe to the left", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe to the left");
                        gameGrid.swipeLeft();
                    }
                    return true;
                } else if (Math.abs(yDifference) > MIN_SWIPE_DISTANCE && Math.abs(xDifference) < Math.abs(yDifference)) {
                    //Vertical swipe detected
                    if (y2 > y1) {
                        Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe down");
                        gameGrid.swipeDown();
                    } else {
                        Toast.makeText(this, "Swipe Up", Toast.LENGTH_SHORT).show();
                        Log.d(LOGTAG, "Swipe up");
                        gameGrid.swipeUp();
                    }
                    return true;
                } else {
                    Toast.makeText(this, "Not enough distance", Toast.LENGTH_SHORT).show();
                    Log.d(LOGTAG, "Not enough distance");
                }
        }
        updateCanvas();

        return super.onTouchEvent(event);
    }

    private void updateCanvas() {
        gameCanvas = canvasHandler.drawGridToCanvas(gameGrid,background);
    }


    //The following methods need to be implemented by GestureDetector which is abstract and are not used.

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
