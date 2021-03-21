package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Main activity of our application.
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        /*
          Sets up an on-click-listener for each button, executing the respective code block assigned to the button-listener
         */
		final Button _test = findViewById(R.id.button4x4);
		_test.setOnClickListener(v -> {
			startGameActivity();
		});
	}

	/**
	 * Starts the game activity
	 */
	private void startGameActivity() {
		Intent GameActivity = new Intent(this, GameActivity.class);
		startActivity(GameActivity);
	}


}
