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

public class GameView extends androidx.appcompat.widget.AppCompatImageView {

	private static final int BOTTOM_OFFSET = 35;
	private Paint backgroundColor;
	private Paint textColor;
	private Bitmap backgroundBitmap, gameBitmap;
	private Canvas gameCanvas;
	private int screenWidth;
	private int screenHeight;
	private Context context;
	private Rect gameBackgroundRect;
	private Bitmap tileBitmap;

	public GameView(Context context, AttributeSet attributesSet) {
		super(context, attributesSet);
		setupPaint();
		this.context = context;
	}

	private void setupPaint() {

		backgroundColor = new Paint();
		backgroundColor.setARGB(255, 0,255,255);	//Türkis - Turquois

		textColor = new Paint();
		textColor.setColor(Color.BLACK);
		textColor.setTextSize(50);
	}

	public void initCanvas() {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
		screenHeight = size.y;


		Log.d("GameView", screenWidth + " " + screenHeight);
		Log.d("GameView", "drawing Rect");

		backgroundBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
		this.setImageBitmap(backgroundBitmap);
		gameCanvas = new Canvas(backgroundBitmap);

		Rect rectangle = new Rect(0,0, screenWidth,screenHeight);
		gameCanvas.drawRect(rectangle, backgroundColor);
		Log.d("GameView", "drew Rect");

		gameBackgroundRect = new Rect(0,screenHeight-screenWidth-BOTTOM_OFFSET ,screenWidth,screenHeight-BOTTOM_OFFSET);
		gameBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.grid4x4_background);
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
	}

	public void drawGridOnCanvas(Grid gameGrid){
		gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
		for(int j = 0; j <= 3; j++) { //row
			for(int i = 0; i <= 3;i++ ) { //column
				if(gameGrid.getValue(i,j) != 0) {
					drawTileAtpos(i,j, gameGrid.getTileAtPos(i,j));
				}
			}
		}
	}

	public void drawTileAtpos(int x, int y, Tile currentTile) {
		/*Paint tileColor = new Paint();
		tileColor.setARGB(255, 255,0,0);*/
		//System.out.print("x: " + x +" y: "+ y);
		Rect tileRect = new Rect(x * (screenWidth/4),screenHeight-BOTTOM_OFFSET-((4-y)*(screenWidth/4)) ,(x+1) * (screenWidth/4),screenHeight-BOTTOM_OFFSET-((4-(y+1))*(screenWidth/4)));
		tileBitmap = currentTile.getDisplay();
		gameCanvas.drawBitmap(tileBitmap, null, tileRect, null);
		/*gameCanvas.drawRect(tileRect, tileColor);
		gameCanvas.drawText(value + "", tileRect.centerX(), tileRect.centerY(), textColor);*/
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void slideColumnAnimation(int x1, int y1, int x2, int y2, int value) {

		Paint tileColor = new Paint();
		tileColor.setARGB(255, 255,0,0);
		//System.out.print("x: " + x +" y: "+ y);
		int top= screenHeight-BOTTOM_OFFSET-((4-y1)*(screenWidth/4));
		while (Math.abs(y1-y2) != 0) {
			gameCanvas.drawBitmap(gameBitmap, null, gameBackgroundRect, null);
			Rect tileRect = new Rect(x1 * (screenWidth / 4), screenHeight-BOTTOM_OFFSET-((4-y1)*(screenWidth/4)), (x1 + 1) * (screenWidth / 4), screenHeight - BOTTOM_OFFSET - ((4 - (y1 + 1)) * (screenWidth / 4)));
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
