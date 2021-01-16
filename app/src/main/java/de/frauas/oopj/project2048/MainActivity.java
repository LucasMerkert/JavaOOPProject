package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //test1
    //F war hier
    //Tarik war auch hier
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
          Sets up an on-click-listener for each button, executing the respective code block assigned to the button-listener
         */
        final Button _gameCanvas = findViewById(R.id.button4x4);
        _gameCanvas.setOnClickListener(v -> {
            //Button 4x4 is clicked; Start 4x4 View
            openGameCanvas();
        });

        //Optimierbar
        final Button _button5x5 = findViewById(R.id.button5x5);
        _button5x5.setOnClickListener(v -> {
            //Button 5x5 is clicked; Start 5x5 View
            open5x5activity();
        });

        final Button _button6x6 = findViewById(R.id.button6x6);
        _button6x6.setOnClickListener(v -> {
            //Button 6x6 is clicked; Start 6x6 View
            open6x6activity();
        });

        final Button _test = findViewById(R.id.startCanvasTest);
        _test.setOnClickListener(v -> {
            startCanvasTest();
        });

        final Button _buttonCanavas = findViewById(R.id.buttonCanvas);
        _buttonCanavas.setOnClickListener(v -> {
            //Button 6x6 is clicked; Start 6x6 View
            openCanvas();
        });
    }

    private void startCanvasTest() {
        Intent startCanvasTest = new Intent(this, GameCanvas.class);
        startActivity(startCanvasTest);
    }


    private void openGameCanvas(){
        Intent gameCanvasOpen = new Intent(this, GameCanvas.class);
        gameCanvasOpen.putExtra("EXTRA_GRID_HEIGHT", 4);
        gameCanvasOpen.putExtra("EXTRA_GRID_WIDTH", 4);
        startActivity(gameCanvasOpen);
    }

    private void open5x5activity() {
        Intent grid5x5open = new Intent(this, Grid4x4.class);
        grid5x5open.putExtra("EXTRA_GRID_HEIGHT", 5);
        grid5x5open.putExtra("EXTRA_GRID_WIDTH", 5);
        startActivity(grid5x5open);
    }

    private void open6x6activity() {
        Intent grid6x6open = new Intent(this, Grid4x4.class);
        grid6x6open.putExtra("EXTRA_GRID_HEIGHT", 6);
        grid6x6open.putExtra("EXTRA_GRID_WIDTH", 6);
        startActivity(grid6x6open);
    }
    private void openCanvas(){
        Intent canvasOpen = new Intent(this, TestCanvas.class);

        startActivity(canvasOpen);
    }

}
