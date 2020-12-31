package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class Grid4x4 extends AppCompatActivity {

    final static int WIDTH = 4;
    final static int HEIGHT = 4;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid4x4);

        TextView[][] matrix = new TextView[WIDTH][HEIGHT];
        matrix[0][0] = findViewById(R.id.textView0_0);
        matrix[0][1] = findViewById(R.id.textView0_1);
        matrix[0][2] = findViewById(R.id.textView0_2);


        matrix[0][0].setText("This is text");
    }


}