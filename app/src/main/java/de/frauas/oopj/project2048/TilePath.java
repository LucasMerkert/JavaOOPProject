package de.frauas.oopj.project2048;

import android.content.Context;
import android.util.Log;

import java.util.LinkedList;


/**
 * class to manage the Tile path after a merge
 */
public class TilePath {
    private int x;
    private int y;
    private boolean merge;
    //CHANGE THIS IT'S JUST FOR DEBUG (line 13+14)
    int HEIGHT = 0;
    int WIDTH = 0;

    /**
     * method to set the TilePath
     * @param x coordinate before last swipe
     * @param y coordinate before last swipe
     * @param merge if tile was merges or not
     * @param context application context
     */
    public TilePath(int x, int y, boolean merge, Context context){
        try {
            WIDTH = ((Application2048) context.getApplication()).getWidth();
            HEIGHT = ((Application2048) context.getApplication()).getHeight();
        } catch (Exception e) {
            Log.e("Tile Path", "Using Static Values");
            WIDTH = 4;
            HEIGHT = 4;
        }
        if(y < 0 || y > HEIGHT){
            throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
        }
        if(x < 0 || x > WIDTH){
            throw new IllegalArgumentException("column not [" + WIDTH + ",0]");
        }
        this.x = x;
        this.y = y;
        this.merge = merge;
    }

    /**
     * prints the x and y value
     */
    public void printList(){
        System.out.print("position before swipe [" + x + "] [" + y + "] \n");
    }

    /**
     * returns pre-swipe x coordinate of a tile
     * @return  x coordinate before swipe
     */
    public int getPathX(){
        return x;
    }

    /**
     * returns pre-swipe y coordinate of a tile
     * @return y coordinate before swipe
     */
    public int getPathY(){
        return y;
    }

    /**
     * indicate if tile was merged or not
     * @return if tile was merged or not
     */
    public boolean getPathMerge(){
        return merge;
    }
}

