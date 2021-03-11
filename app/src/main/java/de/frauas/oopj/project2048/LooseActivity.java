package de.frauas.oopj.project2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LooseActivity extends AppCompatActivity {

	int score, highscore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loose);
		Intent _intent = getIntent();

		this.score = _intent.getIntExtra("score", -1);
		this.highscore = _intent.getIntExtra("high score", -1);

		TextView scoreTextField = findViewById(R.id.scoreText);
		TextView highScoreTextField = findViewById(R.id.highScoreText);
		TextView newHighScoreTextField = findViewById(R.id.newHighScoreField);


		scoreTextField.setText("Your Score: " + score);
		if(highscore < score) {
			highScoreTextField.setText("High Score: " + score);
			newHighScoreTextField.setText("New High Score! Congratualtions!");
		}
		else highScoreTextField.setText("High Score: " + highscore);

		final Button _restart = findViewById(R.id.restartGameButton);
		_restart.setOnClickListener(v -> {
			restartGame();
		});

		final Button _titleScreen = findViewById(R.id.titleScreenButton);
		_titleScreen.setOnClickListener(v -> {
			titleScreen();
		});

		final Button _exit = findViewById(R.id.exitGameButton);
		_exit.setOnClickListener(v -> {
			exitGame();
		});
	}

	private void exitGame() {
		Intent homeIntent = new Intent(Intent.ACTION_MAIN);
		homeIntent.addCategory(Intent.CATEGORY_HOME);
		homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(homeIntent);
	}

	private void titleScreen() {
		Intent titleIntent = new Intent(this, MainActivity.class);
		startActivity(titleIntent);
	}

	private void restartGame() {
		Intent restartIntent = new Intent(this, GameActivity.class);
		startActivity(restartIntent);
	}
}