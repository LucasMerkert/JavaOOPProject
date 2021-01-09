package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TestCanvas extends AppCompatActivity {


    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();
    private static final int OFFSET = 30;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;
    private TextView mTextView;
    private int counter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_canvas);


        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),
                R.color.colorRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(),
                R.color.colorAccent, null);


        mPaint.setColor(mColorBackground);

        mPaintText.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.colorPrimaryDark, null)
        );
        mPaintText.setTextSize(30);

        mImageView = (ImageView) findViewById(R.id.imageView6);
        mTextView = (TextView) findViewById(R.id.textView);
        counter = 0;

    }

    public void drawSomething(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;

        if (mOffset == OFFSET) {
            mTextView.setText("mOff =OFF");
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);

            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);

            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);
            mOffset += OFFSET; // 1: moff = 30 2: moff = 60

        } else if (mOffset < halfWidth && mOffset < halfHeight) {
            //Change color of rectangle
            mPaint.setColor(mColorRectangle + 10*counter);
            mRect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
            mCanvas.drawRect(mRect, mPaint);
            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);
            mTextView.setText("" + counter);
            counter++;
            //Increase offset
            //mOffset += OFFSET;
        }else {

            mPaint.setColor(mColorAccent);
            mCanvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, mPaint);
            String text = getString(R.string.done);
            //Get bounding box for text to calculate where to draw it
            mPaintText.getTextBounds(text, 0,text.length(), mBounds);
            int x = halfWidth - mBounds.centerX();
            int y = halfHeight - mBounds.centerY();
            mCanvas.drawText(text, x, y, mPaintText);
        }


        //draw changes
        view.invalidate();

    }
}