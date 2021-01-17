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

public class CanvasHandler {

    private static int q0, q1, q2, q3, TILE_LENGTH;
    private Canvas canvas;


    /**
     * Constructor for CanvasHandler
     *
     * @param canvas The canvas to be handled
     */
    public CanvasHandler(Canvas canvas){
        this.canvas = canvas;

        q0 = 0;
        q1 = (int)(0.25d * canvas.getHeight());
        q2 = (int)(0.50d * canvas.getHeight());
        q3 = (int)(0.75d * canvas.getHeight());
        TILE_LENGTH = (int)(0.25d * canvas.getHeight());
        Log.d("Canvas Quantiles", "q1=" + q1);
        Log.d("Canvas Quantiles", "q2=" + q2);
        Log.d("Canvas Quantiles", "q3=" + q3);
        Log.d("Canvas Quantiles", "TILE_LENGTH=" + TILE_LENGTH);
    }

    /**
     * Draws Tile to a specified location on Canvas
     * @param xpos x-coordinate of Tile to be drawn
     * @param ypos y-coordinate of Tile to be drawn
     */
    private void drawTileAtLocation(int xpos, int ypos){
        Log.d("Tile Coords", "xpos=" + xpos);
        Log.d("Tile Coords", "ypos=" + ypos);

        Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _paint.setColor(0);

        canvas.drawRect(xpos/4.0f * canvas.getWidth(),
                ypos/4.0f * canvas.getHeight(),
                xpos/4.0f * canvas.getWidth() + TILE_LENGTH,
                ypos/4.0f * canvas.getHeight() + TILE_LENGTH,
                _paint);
    }

    /**
     * Draw current Grid of Tiles onto Canvas
     *
     * @param grid Current state of the game
     * @return the Canvas which was drawn from the Grid
     */
    public Canvas drawGridToCanvas(Grid grid, Bitmap background) {
        canvas = new Canvas(background);
        drawTileAtLocation(2,3);
        Paint black = new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(0);
        canvas.drawText("Keep tapping", 100,100, black);

        return canvas;
    }
}