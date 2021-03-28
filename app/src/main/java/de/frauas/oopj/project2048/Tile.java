package de.frauas.oopj.project2048;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Tile class for use on Grid, Tiles are moved on the screen
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class Tile {
	private int exp;
	private Bitmap display;
	private Context context;
	private TilePath tilePath;
	private boolean newSpawn;
	private boolean useBaseThree;

	/**
	 * Constructor for tile object
	 * @param exp Exponent
	 * @param context activity context
	 * @param useBaseThree indicates if base of exponent is three
	 */
	public Tile(int exp, Context context, boolean useBaseThree){
		if(exp < 0 || exp >= 17 )
			throw new IllegalArgumentException("Tile is a negative exponent or to big to to be displayed ");
		this.context = context;
		this.tilePath = null;
		this.exp = exp;
		setDisplay();
		this.newSpawn = true;
		this.useBaseThree = useBaseThree;
		setDisplay();
	}

	/**
	 * Sets the tile bitmap according to the tile exponent (exp)
	 */
	private void setDisplay() {
		if(useBaseThree){
			switch (exp) {
				case 1:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_3_3x3);
					break;
				case 2:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_9_3x3);
					break;
				case 3:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_27_3x3);
					break;
				case 4:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_81_3x3);
					break;
				case 5:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_243_3x3);
					break;
				case 6:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_729_3x3);
					break;
				case 7:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_2187_3x3);
					break;
				case 8:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_6561_3x3);
					break;
				case 9:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_19683_3x3);
					break;
				case 10:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_59049_3x3);
					break;
				case 11:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_177147_3x33);
					break;
				case 12:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_531441_3x3);
					break;
				case 13:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_4782969_3x3);
					break;
				case 14:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_14348907_3x3);
					break;
				case 15:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_43046721_3x3);
					break;
				case 16:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.atile_129140163_3x3);
					break;
				default:
					throw new IllegalArgumentException("Exponent of Tile to high");
			}
		} else {
			switch (exp) {
				case 1:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_2);
					break;
				case 2:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_4);
					break;
				case 3:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_8);
					break;
				case 4:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_16);
					break;
				case 5:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_32);
					break;
				case 6:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_64);
					break;
				case 7:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_128);
					break;
				case 8:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_256);
					break;
				case 9:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_512);
					break;
				case 10:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_1024);
					break;
				case 11:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_2048);
					break;
				case 12:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_4096);
					break;
				case 13:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_8192);
					break;
				case 14:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_16384);
					break;
				case 15:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_32768);
					break;
				case 16:
					display = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_65536);
					break;
				default:
					throw new IllegalArgumentException("Exponent of Tile to high");
			}
		}
	}

	/**
	 * increments the exp of tile and updates bitmap
	 */
	public void upgrade(){
		exp++;
		setDisplay();
	}

	/**
	 * Getter for Tile value depending on it's base
	 * @return value of Tile
	 */
	public int getValue() {
		if(useBaseThree){
			return (int) Math.pow(3, exp);
		}else {
			return (int) Math.pow(2, exp);
		}
	}

	/**
	 * Getter for Tile exp
	 * @return exponent of Tile
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Getter of the Bitmap assigned to a Tile
	 * @return Bitmap of the Tile
	 */
	public Bitmap getDisplay(){
		return display;
	}


	/**
	 * Getter to check if the tile is newly spawned
	 * @return boolean flag true if tile is newly spawned
	 */
	public boolean getNewSpawn(){
		return newSpawn;
	}

	/**
	 * Method to set and remember the previous location of a tile after a swipe
	 * @param x previous x coordinate or column
	 * @param y previous y coordinate or row
	 * @param merge true if the tile was merged, false if not
	 */
	public void setPath(int x, int y, boolean merge){
		if (x < 0 || x >= 4)
			throw new IllegalArgumentException("x coordinate out of bounds");
		else if (y < 0 || y >= 4)
			throw new IllegalArgumentException("y coordinate out of bounds");
		tilePath = new TilePath(x,  y,  merge, context);
	}

	/**
	 * deletes tilePath
	 */
	public void deletePath(){
		tilePath = null;
	}

	/**
	 * Getter for the tilePath
	 * @return tilePath
	 */
	public TilePath getTilePath() {
		return tilePath;
	}

	/**
	 * sets new spawn flag
	 * @param newSpawn flag true if tile is newly spawned
	 */
	public void setNewSpawn(boolean newSpawn){
		this.newSpawn = newSpawn;
	}
}

