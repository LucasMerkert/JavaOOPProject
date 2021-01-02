package de.frauas.oopj.project2048;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Grid4x4 extends AppCompatActivity {

    static int WIDTH = 0;
    static int HEIGHT = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid4x4);

        HEIGHT = getIntent().getIntExtra("EXTRA_GRID_HEIGHT", 0);
        WIDTH = getIntent().getIntExtra("EXTRA_GRID_WIDTH", 0);
        TextView titleText = findViewById(R.id.InGameTitleString);
        titleText.setText(String.format("This is a %dx%d game.", HEIGHT, WIDTH));

    /*
        // Create a ConstraintLayout in which to add the ImageView
        ConstraintLayout constraintLayout = findViewById(R.id.activity_grid_layout);

        // Instantiate an ImageView and define its properties
        ImageView background_image = findViewById(R.id.gameGrid);
        background_image.setImageResource(R.drawable.background4x4);
        //background_image.setContentDescription(getResources().getString(R.string.));

        // set the ImageView bounds to match the Drawable's dimensions
        background_image.setAdjustViewBounds(true);
        background_image.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view.

        constraintLayout.addView(background_image);
        setContentView(constraintLayout);*/
    }


}