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


/**
 * @author Tarik, Friedrich, Ana, Lucas
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

	public GameView(Context context, AttributeSet attributesSet) {
		super(context, attributesSet);
		setupPaint();
		this.context = context;
	}

	/**
	 * initial definition for the background color and the Text color
	 */
	private void setupPaint() {

		backgroundColor = new Paint();
		backgroundColor.setARGB(255, 0,255,255);	//Türkis - Turquois

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
	 * Draws grid on the canvas
	 * @param gameGrid grid which holds current game state
	 */
	public void drawGridOnCanvas(Grid gameGrid){
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int j = 0; j <= 3; j++) { //row
			for(int i = 0; i <= 3;i++ ) { //column
				if(gameGrid.getTileAtPos(i,j) != null) {
					drawTileAtpos(i,j, gameGrid.getTileAtPos(i,j));
				}
			}
		}
	}

	/**
	 * Draws a Tile with the respective Bitmap to the canvas at position (x,y)
	 * @param x column coorsinate of the tile
	 * @param y row coordinate of the tile
	 * @param currentTile Tile which will be drawn to canvas
	 */
	public void drawTileAtpos(int x, int y, Tile currentTile) {
		Rect tileRect = new Rect((int) (OFFSET+ 0.03*(screenWidth-2*OFFSET) + x* (screenWidth-2*OFFSET)*0.242),
				(int) (screenHeight- OFFSET -0.03*(screenWidth-2*OFFSET)- ((4-y)*(screenWidth-2*OFFSET)*0.242)+0.028*(screenWidth-2*OFFSET)),
				(int) (OFFSET+ 0.03*(screenWidth-2*OFFSET) + (x+ 1)* (screenWidth-2*OFFSET)*0.242-0.028*(screenWidth-2*OFFSET)),
				(int) (screenHeight- OFFSET -0.03*(screenWidth-2*OFFSET)- ((4-(y+1))*(screenWidth-2*OFFSET)*0.242)));
		tileBitmap = currentTile.getDisplay();
		gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);
	}


	//TODO:animations
	public void slideColumnAnimation(int x1, int y1, int x2, int y2, int value) {

		Paint tileColor = new Paint();
		tileColor.setARGB(255, 255,0,0);
		//System.out.print("x: " + x +" y: "+ y);
		int top= screenHeight- OFFSET -((4-y1)*(screenWidth/4));
		while (Math.abs(y1-y2) != 0) {
			gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
			Rect tileRect = new Rect(x1 * (screenWidth / 4), screenHeight- OFFSET -((4-y1)*(screenWidth/4)), (x1 + 1) * (screenWidth / 4), screenHeight - OFFSET - ((4 - (y1 + 1)) * (screenWidth / 4)));
			gameCanvas.drawRect(tileRect, tileColor);
			gameCanvas.drawText(value + "", tileRect.centerX(), tileRect.centerY(), textColor);
			System.out.println("Animation: x: " + x1 + " y1: " + y1 + " y2: " + y2);
			postInvalidate();
			if(y1 < y2){
				top--;
			} else {
				y1--;
			}
			//SystemClock.sleep(100);
		}


	}


}
