package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class CanvasTest extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);


        linearLayout = findViewById(R.id.canvasSocket);
        GameCanvas myView = new GameCanvas(this);
        linearLayout.addView(myView);
    }
}