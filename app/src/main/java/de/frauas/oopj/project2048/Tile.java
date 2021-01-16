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
    private int value;
    private Bitmap display;

    /**
     * Constructor for tile object,
     * @param value
     * @throws TileSpawnException if value is not 2 or 4
     */
    public Tile(int value, int xPos, int yPos) throws TileSpawnException{
        if (value == 2 || value == 4) {
            this.value=value;
        } else throw new TileSpawnException();
        /*
        if(value == 2) {
            //TODO Set Tile 2 Bitmap
            display = BitmapFactory.decodeResource(getResources(), R.drawable.grid4x4_background).copy(Bitmap.Config.ARGB_8888, true);
        } else if(value == 4) {
            //TODO Set Tile 4 Bitmap
            display = BitmapFactory.decodeResource(getResources(), R.drawable.grid4x4_background).copy(Bitmap.Config.ARGB_8888, true);
        }*/
    }


    /**
     * Getter for Tile value
     * @return value of Tile
     */
    public int getValue() {
        return this.value;
    }


    @Override
    /**
     * Outputs tile as String; Overrides 'toString()' from java.lang.Object
     */
    public String toString(){
        return "("+ Integer.toString(value) + ")";
    }

}
