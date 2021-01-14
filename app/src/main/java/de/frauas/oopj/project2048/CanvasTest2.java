package de.frauas.oopj.project2048;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasTest2 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_canvas);

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
    }
}
