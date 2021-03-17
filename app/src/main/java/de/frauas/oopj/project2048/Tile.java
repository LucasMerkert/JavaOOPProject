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
	public int pos; //for posfree array check
	//private int value;
	private Bitmap display;
	private Context context;

	/**
	 * Constructor for tile object,
	 * @param exp = Exponent
	 *        value = 2^exp
	 */
	public Tile(int exp,Context context){

		this.context = context;
		if(exp < 0 )
			throw new IllegalArgumentException("Tile is a negative number");
		else this.exp = exp;
		setDisplay();


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

