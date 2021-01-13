package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class CanvasTest extends AppCompatActivity {

    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_canvas);


        layout = findViewById(R.id.gameLayout);
        GameCanvas myView = new GameCanvas(this);
        layout.addView(myView);
    }
}