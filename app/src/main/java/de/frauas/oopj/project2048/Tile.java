package de.frauas.oopj.project2048;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.graphics.Canvas;
import androidx.appcompat.app.AppCompatActivity;

/**
 * class to construct, upgrade, getValue, getExp Tiles and to get the corresponding picture of each tile,
 *
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class Tile {
	private int exp;
	private Bitmap display;
	private Context context;
	private TilePath tilePath;

	/**
	 * Constructor for tile object,
	 * @param exp = Exponent
	 *        value = 2^exp
	 */
	public Tile(int exp, Context context){
		if(exp < 0 || exp >= 17 )
			throw new IllegalArgumentException("Tile is a negative exponent or to big to to be displayed ");

		this.context = context;
		this.tilePath = null;
		this.exp = exp;
		setDisplay();
	}

	/**
	 * Method to set the old location of a tile after a swipe
	 * @param x old x coordinate or column
	 * @param y old y coordinate or row
	 * @param merge if the tile was merge = true or swiped = false
	 */
	public void setPath(int x, int y, boolean merge){
		tilePath = new TilePath( x,  y,  merge, context);
	}

	/**
	 * deletes tilePath
	 */
	public void deletePath(){
		tilePath = null;
	}

	/**
	 * method to return  X coordinate before the last swipe
	 * @return old X coordinate
	 */
	public int getX(){
		return tilePath.getPathX();
	}

	/**
	 * method to return old Y coordinate before the last swipe
	 * @return old Y coordinate
	 */
	public int getY(){
		return tilePath.getPathY();
	}

	/**
	 * method to return if tile was merged or not during last swipe
	 * @return tile was merged or not
	 */
	public boolean getMerge(){
		return tilePath.getPathMerge();
	}

	/**
	 * Assigns the according bitmap to the value of a tile
	 */
	private void setDisplay() {
		switch(exp) {
			case 1:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_2);
				break;
			case 2:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_4);
				break;
			case 3:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_8);
				break;
			case 4:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_16);
				break;
			case 5:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_32);
				break;
			case 6:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_64);
				break;
			case 7:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_128);
				break;
			case 8:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_256);
				break;
			case 9:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_512);
				break;
			case 10:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_1024);
				break;
			case 11:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_2048);
				break;
			case 12:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_4096);
				break;
			case 13:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_8192);
				break;
			case 14:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_16384);
				break;
			case 15:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_32768);
				break;
			case 16:
				display = BitmapFactory.decodeResource(context.getResources(),R.drawable.tile_65536);
				break;
			default:
				throw new IllegalArgumentException("Exponent of Tile to high");
		}
	}

	/**
	 * increments the exp of tile
	 */
	public void upgrade(){
		exp++;
		setDisplay();
	}

	/**
	 * Getter for Tile value
	 * @return value of Tile
	 */
	public int getValue() {
		return (int) Math.pow(2, exp);
	}

	/**
	 * Getter for Tile exp
	 * @return value of Tile
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Getter of the Bitmap assigned to a Tile
	 * @return Bitmap of the Tile
	 */
	public Bitmap getDisplay(){
		return this.display;
	}


}

