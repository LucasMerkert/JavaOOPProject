package de.frauas.oopj.project2048;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the activity where the game take place. It manages user input and passes them on to GameView and Grid classes.
 * It is responsible for starting, restarting and ending (loosing) the game.
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class GameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

	private static final String LOGTAG = "Touch Event";
	private static final int MIN_SWIPE_DISTANCE = 100;

	private Grid gameGrid;
	private float x1, y1;	//Gesture Detector Coords
	private GestureDetector gestureDetector;
	private SoundPlayer sound;
	private GameView gameView;
	private TextView inGameScoreText;

	private final int WIDTH = 4;
	private final int HEIGHT = 4;

	/**
	 * Called when Activity is started. Sets up the restart button, grid, canvas, and gesture detetor.
	 * @param savedInstanceState naturally passed by android
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_game);
		((Application2048) this.getApplication()).setWidth(WIDTH);
		((Application2048) this.getApplication()).setHeight(HEIGHT);

		inGameScoreText = findViewById(R.id.inGameScoreText);
		gameView = findViewById(R.id.gameView);
		gameView.initCanvas();

		sound = new SoundPlayer(this);

		gameGrid = new Grid(WIDTH, HEIGHT,this);
		updateCanvas();
		gestureDetector = new GestureDetector(GameActivity.this, this);

		final Button _restart = findViewById(R.id.restartBtn);
		_restart.setOnClickListener(v -> {
			restartGame();
		});

		final Button temp_loose = findViewById(R.id.temp_loose);
		temp_loose.setOnClickListener(v -> {
			looseGame(gameGrid.getScore());
		});
	}

	/**
	 * Gets called initially at game start and after a change has been done to the gameGrid.
	 * Calls gameView to update the canvas, checks for loosing conditon and updates the current score
	 */
	private void updateCanvas() {
		gameView.setCurrentState(gameGrid);
		Log.d("GameCanvas", "invalidate");
		gameView.invalidate();
		if (gameGrid.isGameOver()) looseGame(gameGrid.getScore());
		else inGameScoreText.setText("Your Score: " + gameGrid.getScore());
		//The following is console log and not relevant for release
		for(int j =0; j <= 3; j++){
			for(int i = 0; i <= 3; i++) {
				System.out.print(gameGrid.getValue(i, j) + " ");
			}
			System.out.println(" :" + j );
		}
	}

	/**
	 * Called by updateCanvas() when a loosing condition is detected. Checks for a new high score and updates if necessary.
	 * Starts activity LooseActivity packing the achieved score and the high score as extras.
	 * @param currentScore the achieved score
	 */
	private void looseGame(int currentScore) {
		sound.playGameoverSound();
		SharedPreferences savefile = getPreferences(MODE_PRIVATE);
		int highScore = savefile.getInt("High Score", 0);
		if(highScore < currentScore){		//new High Score!
			SharedPreferences.Editor writer = savefile.edit();
			writer.putInt("High Score", currentScore);
			writer.apply();
		}

		Intent looseActivity = new Intent(this, LooseActivity.class);
		looseActivity.putExtra("score", currentScore);
		looseActivity.putExtra("high score", highScore);
		startActivity(looseActivity);
	}

	/**
	 * Gets called by pushing the restart game button.
	 * Restarts the Game by resetting the view and grid.
	 */
	private void restartGame() {
		gameView.initCanvas();
		gameGrid = new Grid(4,4, this);
		updateCanvas();
	}

	/**
	 * Called when a touch screen event was not handled by any of the views
	 * under it.
	 * Detects the x and y coordinates at the start and at the end of the user touching the screen
	 * and determines in which direction (UP, DOWN, LEFT, RIGHT) the swipe occured.
	 * calls swipe____() functions in gameGrid and then updated canvas
	 * @param event The touch screen event being processed.
	 * @return Returns true if a swipe was detected, fals if not
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		//detecting the x and y coordinated both at the start and at the end of the screen touch
		switch (event.getAction()) {
			//start of motion coords
			case MotionEvent.ACTION_DOWN:
				x1 = event.getX();
				y1 = event.getY();
				break;
			//end of motion coords
			case MotionEvent.ACTION_UP:
				//end of motion
				float x2 = event.getX();
				float y2 = event.getY();
				//evaluating the swipe
				float xDifference = x1 - x2;
				float yDifference = y1 - y2;

				if (Math.abs(xDifference) > MIN_SWIPE_DISTANCE && Math.abs(xDifference) > Math.abs(yDifference)) {
					//Horizontal swipe detected
					if (x2 > x1) {
						//Toast.makeText(this, "Swipe to the right", Toast.LENGTH_SHORT).show();
						Log.d(LOGTAG, "Swipe to the right");
						boolean change = gameGrid.swipeRight();
						if(change) sound.playWooshSound();
					} else {
						//Toast.makeText(this, "Swipe to the left", Toast.LENGTH_SHORT).show();
						Log.d(LOGTAG, "Swipe to the left");
						boolean change = gameGrid.swipeLeft();
						if(change) sound.playWooshSound();
					}
					updateCanvas();
					return true;
				} else if (Math.abs(yDifference) > MIN_SWIPE_DISTANCE && Math.abs(xDifference) < Math.abs(yDifference)) {
					//Vertical swipe detected
					if (y2 > y1) {
						//Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
						Log.d(LOGTAG, "Swipe down");
						boolean change = gameGrid.swipeDown();
						if(change) sound.playWooshSound();
					} else {
						//Toast.makeText(this, "Swipe Up", Toast.LENGTH_SHORT).show();
						Log.d(LOGTAG, "Swipe up");
						boolean change = gameGrid.swipeUp();
						if(change) sound.playWooshSound();
					}
					updateCanvas();
					return true;
				} else {
					//Toast.makeText(this, "Not enough distance", Toast.LENGTH_SHORT).show();
					Log.d(LOGTAG, "Not enough distance");
					return false;
				}
		}
		return super.onTouchEvent(event);
	}































	//The following methods need to be implemented for GestureDetector which is abstract and are not used by us directly.

	/**
	 * Notified when a tap occurs with the down {@link MotionEvent}
	 * that triggered it. This will be triggered immediately for
	 * every down event. All other events should be preceded by this.
	 *
	 * @param e The down motion event.
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	/**
	 * The user has performed a down {@link MotionEvent} and not performed
	 * a move or up yet. This event is commonly used to provide visual
	 * feedback to the user to let them know that their action has been
	 * recognized i.e. highlight an element.
	 *
	 * @param e The down motion event
	 */
	@Override
	public void onShowPress(MotionEvent e) {

	}

	/**
	 * Notified when a tap occurs with the up {@link MotionEvent}
	 * that triggered it.
	 *
	 * @param e The up motion event that completed the first tap
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	/**
	 * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
	 * current move {@link MotionEvent}. The distance in x and y is also supplied for
	 * convenience.
	 *
	 * @param e1        The first down motion event that started the scrolling.
	 * @param e2        The move motion event that triggered the current onScroll.
	 * @param distanceX The distance along the X axis that has been scrolled since the last
	 *                  call to onScroll. This is NOT the distance between {@code e1}
	 *                  and {@code e2}.
	 * @param distanceY The distance along the Y axis that has been scrolled since the last
	 *                  call to onScroll. This is NOT the distance between {@code e1}
	 *                  and {@code e2}.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return false;
	}

	/**
	 * Notified when a long press occurs with the initial on down {@link MotionEvent}
	 * that trigged it.
	 *
	 * @param e The initial on down motion event that started the longpress.
	 */
	@Override
	public void onLongPress(MotionEvent e) {

	}

	/**
	 * Notified of a fling event when it occurs with the initial on down {@link MotionEvent}
	 * and the matching up {@link MotionEvent}. The calculated velocity is supplied along
	 * the x and y axis in pixels per second.
	 *
	 * @param e1        The first down motion event that started the fling.
	 * @param e2        The move motion event that triggered the current onFling.
	 * @param velocityX The velocity of this fling measured in pixels per second
	 *                  along the x axis.
	 * @param velocityY The velocity of this fling measured in pixels per second
	 *                  along the y axis.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return false;
	}
}
