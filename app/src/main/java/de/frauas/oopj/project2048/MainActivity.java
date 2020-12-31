package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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
        final Button _button4x4 = findViewById(R.id.button4x4);
        _button4x4.setOnClickListener(v -> {
            //Button 4x4 is clicked; Start 4x4 View
            open4x4activity();
        });

        final Button _button5x5 = findViewById(R.id.button5x5);
        _button5x5.setOnClickListener(v -> {
            //Button 5x5 is clicked; Start 5x5 View
            open5x5activity();
        });

        /*
        final Button _button6x6 = findViewById(R.id.button6x6);
        _button6x6.setOnClickListener(v -> {
            //Button 6x6 is clicked; Start 6x6 View
            open6x6activity();
            }
        });*/
    }

    public void open4x4activity(){
        Intent grid4x4open = new Intent(this, Grid4x4.class);
        grid4x4open.putExtra("EXTRA_GRID_HEIGHT", 4);
        grid4x4open.putExtra("EXTRA_GRID_WIDTH", 4);
        startActivity(grid4x4open);
    }

    private void open5x5activity() {
        Intent grid5x5open = new Intent(this, Grid4x4.class);
        grid5x5open.putExtra("EXTRA_GRID_HEIGHT", 5);
        grid5x5open.putExtra("EXTRA_GRID_WIDTH", 5);
        startActivity(grid5x5open);
    }

}
