package de.frauas.oopj.project2048;

import android.content.Context;
import android.util.Log;

import java.util.LinkedList;


/**
 * class to manage the Tile
 * @author ana, friedrich, tarik, lucas
 */
public class TilePath {
	private final int x;
	private final int y;
	private final boolean merge;
	private final int WIDTH, HEIGHT;

	/**
	 * method to set the TilePath
	 * @param x coordinate before swipe
	 * @param y coordinate before swipe
	 * @param merge flag true if tile was merged or not
	 * @param activityContext activity context
	 */
	public TilePath(int x, int y, boolean merge, Context activityContext){
		Application2048 app = (Application2048) activityContext.getApplicationContext();
		WIDTH = app.getWidth();
		HEIGHT = app.getHeight();

		if(y < 0 || y >= HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		else if(x < 0 || x >= WIDTH){
			throw new IllegalArgumentException("column not [" + WIDTH + ",0]");
		}

		this.x = x;
		this.y = y;
		this.merge = merge;

		Log.d("Tile Path creation", "Old Coords: "+ x + " " + y + "\n" + "MergeFlag: " + merge);
	}

    /**
     * returns pre-swipe x coordinate of a tile
     * @return  x coordinate before swipe
     */
    public int getX(){
        return x;
    }

    /**
     * returns pre-swipe y coordinate of a tile
     * @return y coordinate before swipe
     */
    public int getY(){
        return y;
    }

    /**
     * indicate if tile was merged or not
     * @return if tile was merged or not
     */
    public boolean getMerge(){
        return merge;
    }
}

