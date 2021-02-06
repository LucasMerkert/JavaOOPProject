package de.frauas.oopj.project2048;

import java.util.Random;

/**
 * @author Tarik, Friedrich, Ana, Lucas
 *
 */
public class Grid {
    Tile[][] matrix;
    int width;
    int height;
    int tileCount;
    boolean change = false;
    int[] pos_free_array;

    //NEED GRID PRINT FUNCTION IWIE
    /**
     * Contructor for Grid object, the main playing field. The top-left corner of the grid is [0][0], the one to the right of it [1][0]; the one below [0][1]
     * @param width
     * @param height
     */
    public Grid(int width, int height) {
        this.tileCount = 0;
        this.width = width;
        this.height = height;
        matrix = new Tile[width][height];
        pos_free_array = new int[16];
        for(int i=0; i<16 ;i++){
            pos_free_array[i] = i;
        }
    }

    /**
     * Spawn 2 Tiles at the beginning of a game
     */
    private void initSpawn() {

    }

    /* two possibilities:
     * a) execute isSwipePossible before swiping (check all directions)
     * pro: check if game over
     * con: maybe slower?
     * b) execute isSwipePossible after swiping (only check swiping direction)
     * pro: maybe faster?
     * con: how to check game over?
     */


    /*detect merge when swipeUp not implemented (yet) :(
     * were thinking about if value of matrix cell == to one before -> merge possible
     * but dont know how to get value yet
     */
    /**
     * swipeUp: Merges tiles if possible and moves Tiles to new position in swipe direction
     * @return true if tiles changed position, to spawn new tile
     * this method might get merged into swipeUp if it is unnecessary
     * problem if 4480, swipe left, how to merge? result should be 8800, not 16000
     */
    public boolean swipeUp() {
        change = false;
        for(int column=0; column<width; column++) {
            int pivotTile = 0;
            for(int row=1; row<height; row++) {
                if (matrix[row][column] == null) continue;
                if (matrix[pivotTile][column].getExp() == matrix[row][column].getExp()){
                    //merge! function?
                    matrix[pivotTile][column] = new Tile(1 + matrix[pivotTile][column].getExp());
                    //MAYBE matrix[pivotTile][column].exp++ ;
                    //      update bitmap
                    matrix[row][column] = null;
                    tileCount--;
                    //Animation
                    //Sound

                    //grid changed to spawn new tiles
                    change = true;
                    pivotTile++;
                }
                else {
                    //copy row contents onto pivot tile, then clear contents of row tile
                    if (matrix[pivotTile][column] == null) {
                        //want to compare contents at said coordinate
                        matrix[pivotTile][column] = matrix[row][column];
                        matrix[row][column] = null;
                        //Animation
                        //Sound

                        //grid changed to spawn new tiles
                        change = true;
                    }
                    else{
                        //pivot empty
                        if (pivotTile++ != row) {
                            matrix[pivotTile++][column] = matrix[row][column];
                            matrix[row][column] = null;
                            //Animation
                            //Sound

                            //grid changed to spawn new tiles
                            change = true;
                        }
                        pivotTile++;
                    }
                }
            }
        }
        return change;
    }


    /**
     * swipeDown: Merges tiles if possible and moves Tiles to new position in swipe direction
     * @return true if tiles changed position, to spawn new tile
     */
    public boolean swipeDown() {
        change = false;
        for(int column=0; column<width; column++) {
            int pivotTile = height-1;
            for(int row=height-2; row>0; row--) {
                if (matrix[row][column] == null) continue;
                if (matrix[pivotTile][column].getExp() == matrix[row][column].getExp()){
                    //merge! function?
                    matrix[pivotTile][column] = new Tile(1 + matrix[pivotTile][column].getExp());
                    matrix[row][column] = null;
                    tileCount--;
                    //Animation
                    //Sound

                    //grid changed to spawn new tiles
                    change = true;
                    pivotTile--;
                }
                else {
                    //copy row contents onto pivot tile, then clear contents of row tile
                    if (matrix[pivotTile][column] == null) {
                        //want to compare contents at said coordinate
                        matrix[pivotTile][column] = matrix[row][column];
                        matrix[row][column] = null;
                        //Animation
                        //Sound

                        //grid changed to spawn new tiles
                        change = true;
                    }
                    else{
                        //pivot empty
                        if (pivotTile-- != row) {
                            matrix[pivotTile--][column] = matrix[row][column];
                            matrix[row][column] = null;
                            //Animation
                            //Sound

                            //grid changed to spawn new tiles
                            change = true;
                        }
                        pivotTile--;
                    }
                }
            }
        }
        return change;
    }

    /**
     * swipeLeft: Merges tiles if possible and moves Tiles to new position in swipe direction
     * @return true if tiles changed position, to spawn new tile
     */
    public boolean swipeLeft() {
        change = false;
        for(int row=0; row<height; row++) {
            int pivotTile = 0;
            for(int column=1; column<width; column++) {
                if (matrix[row][column] == null) continue;
                if (matrix[row][pivotTile].getExp() == matrix[row][column].getExp()){
                    //merge! function?
                    matrix[row][pivotTile] = new Tile(1 + matrix[row][pivotTile].getExp());
                    matrix[row][column] = null;
                    tileCount--;
                    //Animation
                    //Sound

                    //grid changed to spawn new tiles
                    change = true;
                    pivotTile++;
                }
                else {
                    //copy row contents onto pivot tile, then clear contents of row tile
                    if (matrix[row][pivotTile] == null) {
                        //want to compare contents at said coordinate
                        matrix[row][pivotTile] = matrix[row][column];
                        matrix[row][column] = null;
                        //Animation
                        //Sound

                        //grid changed to spawn new tiles
                        change = true;
                    }
                    else{
                        //pivot empty
                        if (pivotTile++ != row) {
                            matrix[row][pivotTile++] = matrix[row][column];
                            matrix[row][column] = null;
                            //Animation
                            //Sound

                            //grid changed to spawn new tiles
                            change = true;
                        }
                        pivotTile++;
                    }
                }
            }
        }
        return change;
    }

    /**
     * swipeRight: Merges tiles if possible and moves Tiles to new position in swipe direction
     * @return true if tiles changed position, to spawn new tile
     */
    public boolean swipeRight() {
        change = false;
        for(int row=0; row<height; row++) {
            int pivotTile = height - 1;
            for(int column=height-2; column>0; column--) {
                if (matrix[row][column] == null) continue;
                if (matrix[row][pivotTile].getExp() == matrix[row][column].getExp()){
                    //merge! function?
                    matrix[row][pivotTile] = new Tile(1 + matrix[row][pivotTile].getExp());
                    matrix[row][column] = null;
                    tileCount--;
                    //Animation
                    //Sound

                    //grid changed to spawn new tiles
                    change = true;
                    pivotTile--;
                }
                else {
                    //copy row contents onto pivot tile, then clear contents of row tile
                    if (matrix[row][pivotTile] == null) {
                        //want to compare contents at said coordinate
                        matrix[row][pivotTile] = matrix[row][column];
                        matrix[row][column] = null;
                        //Animation
                        //Sound

                        //grid changed to spawn new tiles
                        change = true;
                    }
                    else{
                        //pivot empty
                        if (pivotTile-- != row) {
                            matrix[row][pivotTile--] = matrix[row][column];
                            matrix[row][column] = null;
                            //Animation
                            //Sound

                            //grid changed to spawn new tiles
                            change = true;
                        }
                        pivotTile--;
                    }
                }
            }
        }
        spawnNewTile();
        return change;
    }


    /**
     * Spawn 1 Tiles with the value 2 or 4
     * @return true if successfully spawned the tiles
     */
    private boolean spawnNewTile() {
        if(tileCount == 16){return false;}
        int[] pos_free_array = fill_array();
        //finds random field in matrix which is empty
        int position = new Random().nextInt((16 - tileCount));
        int new_tile_position = pos_free_array[position];
        matrix[position/4][position%4] = new Tile(1);
        //Canvas update needed

        tileCount++;
       return true;
    }

    /**
     * Finds all positions which are empty in matrix
     * @return Array with empty matrix positions
     */
    private int [] fill_array(){
        int count = 0;
        int empty_pos = 0;
        int[] array = new int[16-tileCount];
        for(int i=0; i<height; i++){
            for(int j=0; i<width; i++){
                if(matrix[j][i] == null){
                    array[empty_pos] = count;
                    empty_pos++;
                }
                count++;
            }
        }
        return array;
    }

    /**
     * Determine if there are free Tiles in grid
     * @return true if there are free Tiles in grid
     */
    private boolean isSpawnTilePossible(){
        return  true;
    }



    /**
     * Determine if you are unable to Spawn new Tiles and unable to swipe in any direction
     * @return true if game is over
     */
    public boolean isGameOver() {
        return false;
    }

    public int getValue(int x, int y){
        if(matrix[x][y] == null) {
            return 0;
        } else {
            return matrix[x][y].getValue();
        }
    }



}
