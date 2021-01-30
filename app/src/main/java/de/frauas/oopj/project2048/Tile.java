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
    //private int value;
    private Bitmap display;

    /**
     * Constructor for tile object,
     * value = 2^nr
     * @param nr
     *
     */
    public Tile(int nr) {
        exp = nr;
        //value = (int) Math.pow(2, nr);
        //Bitmap fehlt
    }



    /**
     * Getter for Tile value
     * @return value of Tile
     */
    public int getValue() { return (int) Math.pow(2, exp);
    }

    /**
     * Getter for Tile exp
     * @return value of Tile
     */
    public int getExp() { return exp;
    }


}

