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

        // Instantiate an ImageView and define its properties
        ImageView background_image = findViewById(R.id.gameGrid);
        if (WIDTH == 4 && HEIGHT == 4) background_image.setImageResource(R.drawable.grid4x4);
        else if (WIDTH == 5 && HEIGHT == 5) background_image.setImageResource(R.drawable.grid5x5);
        else if (WIDTH == 6 && HEIGHT == 6) background_image.setImageResource(R.drawable.grid6x6);

    }




}