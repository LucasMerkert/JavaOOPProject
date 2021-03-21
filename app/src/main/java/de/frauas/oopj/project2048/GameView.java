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
 * @author Tarik, Friedrich, Ana, Lucas
 * Extends ImageView to display current game state on the screen
 */
public class GameView extends androidx.appcompat.widget.AppCompatImageView {

	private static final int OFFSET = 35;
	private Paint backgroundColor;
	private Paint textColor;
	private Bitmap backgroundBitmap, gameBitmap,tileBitmap;
	private Canvas gameCanvas;
	private int screenWidth;
	private int screenHeight;
	private Context context;
	private Rect gameBackgroundRect;
	private double factor;
	private Grid currentState;

	public GameView(Context context, AttributeSet attributesSet) {
		super(context, attributesSet);
		setupPaint();
		this.context = context;
		factor = 0;
	}

	/**
	 * initial definition for the background color and the Text color
	 */
	private void setupPaint() {

		backgroundColor = new Paint();
		backgroundColor.setARGB(200, 175,246,200);	//Türkis - Turquois

		textColor = new Paint();
		textColor.setColor(Color.BLACK);
		textColor.setTextSize(50);
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
		backgroundBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
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

	/**
	 * Draws grid on the canvas with Animation
	 */
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
						drawTileAtpos(i,j);
					}
				}
			}
		}
	}

	/**
	 * Draws grid on the canvas without animation
	 */
	public void drawGridOnCanvas(){
		if(currentState == null) {
			throw new NullPointerException("No game state to display");
		}
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int j = 0; j <= 3; j++) { //row
			for(int i = 0; i <= 3;i++ ) { //column
				if(currentState.getTileAtPos(i,j) != null) { // There is a tile at position(i,j) of the grid
						drawTileAtpos(i,j);
				}
			}
		}
	}

	/**
	 * Draws a Tile with the respective Bitmap to the canvas at position (x,y)
	 * @param x column coordinate of the tile
	 * @param y row coordinate of the tile
	 */
	public void drawTileAtpos(int x, int y) {
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
		tileBitmap = currentState.getTileAtPos(x,y).getDisplay();
		gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);
	}

	/**
	 * Draws For each frame the position of the Tiles which have to be moved
	 * @param x new column position of the Tile
	 * @param y new row position of the Tile
	 */
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



	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(currentState == null) {
			throw new NullPointerException("No game state to display");
		}

		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int y = 0; y <= 3; y++) { //row
			for(int x = 0; x <= 3;x++ ) { //column
				if(currentState.getTileAtPos(x,y) != null) { // There is a tile at position(i,j) of the grid
					if(currentState.getTileAtPos(x,y).getTilePath() != null){
						System.out.println("Animation Frame: " + x + " " + y);
						Rect tileRect;
						double distance =  ((screenWidth - 2 * OFFSET) * 0.242);
						double left =  (OFFSET + 0.03 * (screenWidth - 2 * OFFSET) + currentState.getTileAtPos(x, y).getTilePath().getX() * distance);
						double top =  (screenHeight - OFFSET - 0.03 * (screenWidth - 2 * OFFSET) - ((4 - currentState.getTileAtPos(x, y).getTilePath().getY()) * distance) + 0.028 * (screenWidth - 2 * OFFSET));
						double right =  (OFFSET + 0.03 * (screenWidth - 2 * OFFSET) + (currentState.getTileAtPos(x, y).getTilePath().getX() + 1) * distance - 0.028 * (screenWidth - 2 * OFFSET));
						double bottom =  (screenHeight - OFFSET - 0.03 * (screenWidth - 2 * OFFSET) - (4 - (currentState.getTileAtPos(x, y).getTilePath().getY() + 1)) * distance);
						double distanceToMove;

						if (currentState.getTileAtPos(x, y).getTilePath().getX() == x) {
							distanceToMove =  factor * distance * Math.abs(currentState.getTileAtPos(x, y).getTilePath().getY() - y);
							//down
							if (currentState.getTileAtPos(x, y).getTilePath().getY() > y) {
								tileRect = new Rect((int) left, (int) (top - distanceToMove), (int)right, (int) (bottom - distanceToMove));
								//up
							} else {
								tileRect = new Rect((int) left, (int) (top + distanceToMove), (int)right, (int) (bottom + distanceToMove));
							}
						} else if (currentState.getTileAtPos(x, y).getTilePath().getY() == y) {
							distanceToMove =  factor * distance * Math.abs(currentState.getTileAtPos(x, y).getTilePath().getX() - x);
							//left
							if (currentState.getTileAtPos(x, y).getTilePath().getX() > x) {
								tileRect = new Rect((int) (left- distanceToMove), (int) top, (int) (right -distanceToMove), (int) bottom);
								//right
							} else {
								tileRect = new Rect((int) (left+ distanceToMove), (int) top, (int) (right +distanceToMove), (int) bottom);

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
					} else {
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
						tileBitmap = currentState.getTileAtPos(x,y).getDisplay();
						gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);
					}
				}
			}
		}
		System.out.println("Factor: "+ factor);
		if(factor < 1) {
			factor += 0.5;
			invalidate();
		} else{
			factor = 0;
			drawGridOnCanvas();
		}
	}

	/**
	 * Setter for the currentState grid and the according tilepaths
	 * @param currentState Current logical state of the grid
	 */
	public void setCurrentState(Grid currentState) {
		
		this.currentState = currentState;
	}


}
