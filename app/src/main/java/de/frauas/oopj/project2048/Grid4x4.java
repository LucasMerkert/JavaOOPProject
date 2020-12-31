package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Grid4x4 extends AppCompatActivity {

    final static int WIDTH = 4;
    final static int HEIGHT = 4;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid4x4);

        int gridHeight = getIntent().getIntExtra("EXTRA_GRID_HEIGHT", 0);
        TextView textView = findViewById(R.id.textView20);
        textView.setText(gridHeight + "");


        // Create a ConstraintLayout in which to add the ImageView
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        // Instantiate an ImageView and define its properties
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.background4x4);
        //i.setContentDescription(getResources().getString(R.string.));

        // set the ImageView bounds to match the Drawable's dimensions
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view.
        constraintLayout.addView(i);
        setContentView(constraintLayout);
    }


}