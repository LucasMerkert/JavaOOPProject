package de.frauas.oopj.project2048;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Grid4x4 extends AppCompatActivity {

    static int WIDTH = 0;
    static int HEIGHT = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid4x4);

        HEIGHT = getIntent().getIntExtra("EXTRA_GRID_HEIGHT", 0);
        WIDTH = getIntent().getIntExtra("EXTRA_GRID_WIDTH", 0);

        TextView titleText = findViewById(R.id.inGameTitleString);
        titleText.setText(String.format(Locale.ENGLISH,"This is a %dx%d game.", HEIGHT, WIDTH));

    }






}