package de.frauas.oopj.project2048;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.graphics.Canvas;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Tarik
 *
 */
public class Tile {
	private int exp;
	public int pos; //for posfree array check
	//private int value;
	private Bitmap display;

	/**
	 * Constructor for tile object,
	 * @param exp = Exponent
	 *        value = 2^exp
	 * @throws IllegalArgumentException
	 */
	public Tile(int exp) throws IllegalArgumentException{
		if(exp < 0 )
			throw new IllegalArgumentException("Tile is a negative number");
		else this.exp = exp;
		//value = (int) Math.pow(2, nr);
		//Bitmap fehlt
	}

	public void upgrade(){
		this.exp = 1 + this.getExp();
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


}

