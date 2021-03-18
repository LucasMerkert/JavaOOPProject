package de.frauas.oopj.project2048;

import java.util.LinkedList;

public class TilePath {
    private int x;
    private int y;
    private int pivot;
    //private Tile tile;
    private int exp;
    private boolean merge;
    //CHANGE THIS IT'S JUST FOR DEBUG (line 13+14)
    int HEIGHT = 4;
    int WIDTH = 4;


    public TilePath(int x, int y, int pivot, int exp, boolean merge){
        //WRITE GETTER
        if(y < 0 || y > HEIGHT){
            throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
        }
        if(x < 0 || x > WIDTH){
            throw new IllegalArgumentException("column not [0," + WIDTH + "]");
        }
        // check all conditions in case of rectangular (not square) Grid
        if(pivot < 0 || pivot > WIDTH || pivot > HEIGHT){
            throw new IllegalArgumentException("pivotTile not [0," + WIDTH + "]");
        }

        this.x = x;
        this.y = y;
        this.pivot = pivot;
        this.exp = exp;

    }

    public void printList(){
        System.out.print("current tile [" + x + "] [" + y + "]  ->  pivot tile [" + x + "] [" + pivot + "] exp:" + exp + "\n");
    }

    public LinkedList<TilePath> tilePathLinkedList
            = new LinkedList<>();
}