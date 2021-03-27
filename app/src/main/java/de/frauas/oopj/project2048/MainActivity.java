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


        //Sets up an on-click-listener for the button
		final Button _grid4x4Base2 = findViewById(R.id.button4x4Base2);
		_grid4x4Base2.setOnClickListener(v -> {
			startGameActivityBase2();
		});

		final Button _grid4x4Base3 = findViewById(R.id.button4x4Base3);
		_grid4x4Base3.setOnClickListener(v -> {
			startGameActivityBase3();
		});
	}

	/**
	 * Starts the game activity with base 2
	 */
	private void startGameActivityBase2() {
		Intent GameActivity = new Intent(this, GameActivity.class);
		GameActivity.putExtra("useBaseThree", false);
		startActivity(GameActivity);
	}

	/**
	 * Starts the game activity with base 3
	 */
	private void startGameActivityBase3() {
		Intent GameActivity = new Intent(this, GameActivity.class);
		GameActivity.putExtra("useBaseThree", true);
		startActivity(GameActivity);
	}
}
