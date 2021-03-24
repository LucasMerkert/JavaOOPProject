package de.frauas.oopj.project2048;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.LinkedList;


/**
 * Extends ImageView to display current game state on the screen
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class GameView extends androidx.appcompat.widget.AppCompatImageView {

	private static final int OFFSET = 35;
	private static final double ANIMATION_SPEED = 0.1;
	private static final int ROUNDING_ERROR_OFFSET = 100;

	private Paint backgroundColor;
	private Bitmap gameBitmap,tileBitmap;
	private Canvas gameCanvas;
	private int screenWidth;
	private int screenHeight;
	private Context context;
	private Rect gameBackgroundRect;
	private double factor;
	private Grid currentState;

	/**
	 * Constructor for custom View to display game
	 * @param context application context
	 * @param attributesSet passed on by android environment
	 */
	public GameView(Context context, AttributeSet attributesSet) {
		super(context, attributesSet);
		setupPaint();
		this.context = context;
		factor = 0;
	}

	/**
	 * initial definition for the background color
	 */
	private void setupPaint() {

		backgroundColor = new Paint();
		//backgroundColor.setARGB(200, 175,246,200);	//Mint Green
		backgroundColor.setColor(R.attr.colorPrimaryVariant);

	}

	/**
	 * initial setup for the Canvas(size, background, associate bitmap for background)
	 */
	public void initCanvas() {
		//get size of the screen
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
		screenHeight = size.y;

		Log.d("GameView", screenWidth + " " + screenHeight);
		Log.d("GameView", "drawing Rect");

		//associate bitmap with canvas with screen size
		Bitmap backgroundBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
		this.setImageBitmap(backgroundBitmap);
		gameCanvas = new Canvas(backgroundBitmap);

		//define rectangle to draw the background color on
		Rect rectangle = new Rect(0,0, screenWidth,screenHeight);
		gameCanvas.drawRect(rectangle, backgroundColor);
		Log.d("GameView", "drew Rect");

		//draw Game Background onto screen
		gameBackgroundRect = new Rect(OFFSET,screenHeight-screenWidth+OFFSET,screenWidth - OFFSET,screenHeight- OFFSET);
		gameBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.grid4x4_background);
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
	}

	//Not in Use
	/*
	/**
	 * Draws grid on the canvas with Animation
	 */
	/*
	public void drawGridOnCanvasAnimate(){
		if(currentState == null) {
			throw new NullPointerException("No game state to display");
		}
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int j = 0; j <= 3; j++) { //row
			for(int i = 0; i <= 3;i++ ) { //column
				if(currentState.getTileAtPos(i,j) != null) { // There is a tile at position(i,j) of the grid
					if(currentState.getTileAtPos(i,j).getTilePath() != null){
						System.out.println("Animation Frame: " + i + " " + j);
						animateTilePath(i,j); // Tile has to be animated
					} else {
						drawTileAtpos(i,j,currentState.getTileAtPos(i,j));
					}
				}
			}
		}
	}

	/**
	 * Draws For each frame the position of the Tiles which have to be moved
	 * @param x new column position of the Tile
	 * @param y new row position of the Tile
	 */
	/*
	public void animateTilePath(int x, int y) {

		Rect tileRect;
		int distance = (int) ((screenWidth - 2 * OFFSET) * 0.242);
		int left = (int) (OFFSET + 0.03 * (screenWidth - 2 * OFFSET) + currentState.getTileAtPos(x, y).getTilePath().getX() * distance);
		int top = (int) (screenHeight - OFFSET - 0.03 * (screenWidth - 2 * OFFSET) - ((4 - currentState.getTileAtPos(x, y).getTilePath().getY()) * distance) + 0.028 * (screenWidth - 2 * OFFSET));
		int right = (int) (OFFSET + 0.03 * (screenWidth - 2 * OFFSET) + (currentState.getTileAtPos(x, y).getTilePath().getX() + 1) * distance - 0.028 * (screenWidth - 2 * OFFSET));
		int bottom = (int) (screenHeight - OFFSET - 0.03 * (screenWidth - 2 * OFFSET) - (4 - (currentState.getTileAtPos(x, y).getTilePath().getY() + 1)) * distance);
		int distanceToMove;

		if (currentState.getTileAtPos(x, y).getTilePath().getX() == x) {
			distanceToMove = (int) factor * distance * Math.abs(currentState.getTileAtPos(x, y).getTilePath().getY() - y);
			//down
			if (currentState.getTileAtPos(x, y).getTilePath().getY() > y) {
				tileRect = new Rect(left, top - distanceToMove, right, bottom - distanceToMove);
				//up
			} else {
				tileRect = new Rect(left, top + distanceToMove, right, bottom + distanceToMove);

			}
		} else if (currentState.getTileAtPos(x, y).getTilePath().getY() == y) {
			distanceToMove = (int) factor * distance * Math.abs(currentState.getTileAtPos(x, y).getTilePath().getY() - y);
			//left
			if (currentState.getTileAtPos(x, y).getTilePath().getX() > x) {
				tileRect = new Rect(left - distanceToMove, top, right - distanceToMove, bottom);
				//right
			} else {
				tileRect = new Rect(left + distanceToMove, top, right + distanceToMove, bottom);

			}
		} else {
			throw new IllegalArgumentException("Tile was not moved");
		}
		if (currentState.getTileAtPos(x, y).getTilePath().getMerge()) {

			Tile tempTile = new Tile(currentState.getTileAtPos(x, y).getExp() - 1, context);
			tileBitmap = tempTile.getDisplay();
		} else {

			tileBitmap = currentState.getTileAtPos(x, y).getDisplay();
		}
		gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);

	}
	*/

	/**
	 * Draws grid on the canvas
	 */
	private void drawGridOnCanvas(){
		if(currentState == null) {
			throw new NullPointerException("No game state to display");
		}
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int j = 0; j <= 3; j++) { //row
			for(int i = 0; i <= 3;i++ ) { //column
				if(currentState.getTileAtPos(i,j) != null) { // There is a tile at position(i,j) of the grid
					drawTileAtpos(i,j,currentState.getTileAtPos(i,j));
				}
			}
		}
	}

	/**
	 * Draws a Tile with the respective Bitmap to the canvas at position [x][y]
	 * @param x column coordinate of the tile
	 * @param y row coordinate of the tile
	 */
	private void drawTileAtpos(int x, int y, Tile currentTile) {
		if(y < 0 || y > currentState.getHEIGHT()){
			throw new IllegalArgumentException("row not [0," + currentState.getHEIGHT() + "]");
		}
		if(x < 0 || x > currentState.getWIDTH()){
			throw new IllegalArgumentException("column not [0," + currentState.getWIDTH() + "]");
		}
		Rect tileRect = new Rect((int) (OFFSET+ 0.03*(screenWidth-2*OFFSET) + x* (screenWidth-2*OFFSET)*0.242),
				(int) (screenHeight- OFFSET -0.03*(screenWidth-2*OFFSET)- ((4-y)*(screenWidth-2*OFFSET)*0.242)+0.028*(screenWidth-2*OFFSET)),
				(int) (OFFSET+ 0.03*(screenWidth-2*OFFSET) + (x+ 1)* (screenWidth-2*OFFSET)*0.242-0.028*(screenWidth-2*OFFSET)),
				(int) (screenHeight- OFFSET -0.03*(screenWidth-2*OFFSET)- ((4-(y+1))*(screenWidth-2*OFFSET)*0.242)));
		tileBitmap = currentTile.getDisplay();
		gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);
	}

	/**
	 *This method was our biggest problem, we know this is probably not the optimal solution, but the best we could come up with.
	 * The problem here is that it is pretty difficult to animate a canvas, because all the provided animations from
	 * Android Studio only work in Views not on the Canvas itself. Therefore we couldn't use e.g the Animator class or Tweener.
	 * Also we tried to outsource the calculations below as seen above but that didn't work for some reason which we
	 * couldn't find. We tried to figure it out, but the explanations in the internet are very poor in that regard.
	 * @param canvas Canvas that will be shown on the screen
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(currentState == null) {
			throw new NullPointerException("No game state to display");
		}

		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int y = 0; y < currentState.getHEIGHT(); y++) { //rows
			for(int x = 0; x < currentState.getWIDTH();x++ ) { //columns
				if(currentState.getTileAtPos(x,y) != null) { // There is a tile at position(i,j) of the grid
					if(currentState.getTileAtPos(x,y).getTilePath() != null){

						//Animation of the single Tiles Frame by frame
						Rect tileRect;
						//default Position
						double distance =  ((screenWidth - 2 * OFFSET) * 0.242);
						double left =  (OFFSET + 0.03 * (screenWidth - 2 * OFFSET) + currentState.getTileAtPos(x, y).getTilePath().getX() * distance);
						double top =  (screenHeight - OFFSET - 0.03 * (screenWidth - 2 * OFFSET) - ((4 - currentState.getTileAtPos(x, y).getTilePath().getY()) * distance) + 0.028 * (screenWidth - 2 * OFFSET));
						double right =  (OFFSET + 0.03 * (screenWidth - 2 * OFFSET) + (currentState.getTileAtPos(x, y).getTilePath().getX() + 1) * distance - 0.028 * (screenWidth - 2 * OFFSET));
						double bottom =  (screenHeight - OFFSET - 0.03 * (screenWidth - 2 * OFFSET) - (4 - (currentState.getTileAtPos(x, y).getTilePath().getY() + 1)) * distance);
						double distanceToMove;

						//new position during animation
						if (currentState.getTileAtPos(x, y).getTilePath().getX() == x) {
							distanceToMove =  factor * distance * (currentState.getTileAtPos(x, y).getTilePath().getY() - y);
							tileRect = new Rect((int) left, (int) (top - distanceToMove), (int)right, (int) (bottom - distanceToMove));

						} else if (currentState.getTileAtPos(x, y).getTilePath().getY() == y) {
							distanceToMove =  factor * distance * (currentState.getTileAtPos(x, y).getTilePath().getX() - x);
							tileRect = new Rect((int) (left - distanceToMove), (int) top, (int) (right - distanceToMove), (int) bottom);

						} else {
							throw new IllegalArgumentException("Tile was not moved");
						}
						if (currentState.getTileAtPos(x, y).getTilePath().getMerge()) {

							Tile tempTile = new Tile(currentState.getTileAtPos(x, y).getExp() - 1, context);
							tileBitmap = tempTile.getDisplay();
							drawTileAtpos(x,y,tempTile);
						} else {

							tileBitmap = currentState.getTileAtPos(x, y).getDisplay();
						}
						gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);

					} else if(!currentState.getTileAtPos(x,y).getNewSpawn()) { // draws a tile to the canvas that didn't spawn during this swipe

						//Calculates tile position
						Rect tileRect = new Rect((int) (OFFSET+ 0.03*(screenWidth-2*OFFSET) + x* (screenWidth-2*OFFSET)*0.242),
								(int) (screenHeight- OFFSET -0.03*(screenWidth-2*OFFSET)- ((4-y)*(screenWidth-2*OFFSET)*0.242)+0.028*(screenWidth-2*OFFSET)),
								(int) (OFFSET+ 0.03*(screenWidth-2*OFFSET) + (x+ 1)* (screenWidth-2*OFFSET)*0.242-0.028*(screenWidth-2*OFFSET)),
								(int) (screenHeight- OFFSET -0.03*(screenWidth-2*OFFSET)- ((4-(y+1))*(screenWidth-2*OFFSET)*0.242)));
						tileBitmap = currentState.getTileAtPos(x,y).getDisplay();
						gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);
					}
				}
			}
		}

		//factor increase determines the number of frames, the smaller the increase the more frames per animation
		if(factor < 1) {
			factor += ANIMATION_SPEED;
			factor = (double) Math.round(factor*ROUNDING_ERROR_OFFSET)/ROUNDING_ERROR_OFFSET;
			invalidate();
		} else{
			factor = 0;
			drawGridOnCanvas();
		}
	}

	/**
	 * Setter for the currentState grid
	 * @param currentState Current logical state of the game
	 */
	public void setCurrentState(Grid currentState) {
		this.currentState = currentState;
	}
}
