package de.frauas.oopj.project2048;

/**
 * @author Tarik, Friedrich, Ana, Lucas
 *
 */
public class Grid {
    Tile[][] matrix;
    int width;
    int height;
    boolean change = false;

    /**
     * Contructor for Grid object, the main playing field. The top-left corner of the grid is [0][0], the one to the right of it [1][0]; the one below [0][1]
     * @param width
     * @param height
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new Tile[width][height];
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

    /**
     * TODO
     */
    public void swipeUp() {
        //if (isSwipeUpPossible()){

        //}
    }

    /*detect merge when swipeUp not implemented (yet) :(
     * were thinking about if value of matrix cell == to one before -> merge possible
     * but dont know how to get value yet
     */
    /**
     * Determine if swipe up is possible
     * @return true if swipe up is possible
     * this method might get merged into swipeUp if it is unnecessary
     * problem if 4480, swipe left, how to merge? result should be 8800, not 16000
     */
    private boolean isSwipeUpPossible() {
        change = false;
        for(int column=0; column<width; column++) {
            int pivotTile = 0;
            for(int row=1; row<height; row++) {
                if (matrix[row][column] == null) continue;
                if (matrix[pivotTile][column] == matrix[row][column]){
                    //merge function

                    //grid changed to spawn new tiles
                    change = true;
                    pivotTile++;
                }
                else {
                    // move row contents as method?
                    //copy row contents onto pivot tile, then clear contents of row tile
                    if (matrix[pivotTile][column] == null) {
                        //want to compare contents at said coordinate
                        matrix[pivotTile][column] = matrix[row][column];
                        matrix[row][column] = null;
                        //grid changed to spawn new tiles
                        change = true;
                    }
                    else{
                        if (pivotTile++ == row) {
                            matrix[pivotTile++][column] = matrix[row][column];
                            matrix[row][column] = null;
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
     * TODO
     */
    public void swipeLeft() {
       // if (isSwipeLeftPossible()){
        //}
    }

    /**
     * Determine if swipe Left is possible
     * @return true if swipe left is possible
     */
    private boolean isSwipeLeftPossible() {
        for(int row=0; row<height; row++) {
            for(int column=1; column<width; column++) {
                //as long as there is a free space in front of a tile, swipe is possible
                if (matrix[row][column-1] == null && matrix[row][column] != null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TODO
     */
    public void swipeRight() {
        //if (isSwipeRightPossible()){
        //}
    }

    /**
     * Determine if swipe right is possible
     * @return true if swipe right is possible
     */
    private boolean isSwipeRightPossible() {
        for(int row=0; row<height; row++) {
            for(int column=width; column>0; column--) {
                //as long as there is a free space in front of a tile, swipe is possible
                if (matrix[row][column-1] == null && matrix[row][column] != null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TODO
     */
    public void swipeDown() {
        //if (isSwipeDownPossible()){
        //}
    }

    /**
     * Determine if swipe down is possible
     * @return true if swipe down is possible
     */
    private boolean isSwipeDownPossible() {
        for(int column=0; column<width; column++) {
            for(int row=height; row>0; row--) {
                //as long as there is a free space in front of a tile, swipe is possible
                if (matrix[row-1][column] == null && matrix[row][column] != null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Spawn 1 Tiles with the value 2 or 4
     * @return true if successfully spawned the tiles
     */
    private boolean spawnTile() {
        return true;
    }

    /**
     * Determine if there are free Tiles in grid
     * @return true if there are free Tiles in grid
     */
    private boolean isSpawnTilePossible(){
        return  true;
    }

    /**
     * Determine whether Tile a and b have to be merged
     * @param movingTile Tile that is moved
     * @param staticTile Tile that is already on its final position
     * @return true if Tiles have to be merged
     */
    private boolean isMergePossible(Tile movingTile, Tile staticTile) {
        return true;
    }

    /**
     * Merge 2 Tiles with the same value into a new Tile
     * @param movingTile that is moved
     * @param staticTile Tile that will be replaced with the new Tile
     * @return merged Tile
     */
    private Tile mergeTiles(Tile movingTile, Tile staticTile) {
        return staticTile;
    }

    /**
     * Determine if you are unable to Spawn new Tiles and unable to swipe in any direction
     * @return true if game is over
     */
    public boolean isGameOver() {
        return false;
    }



}
