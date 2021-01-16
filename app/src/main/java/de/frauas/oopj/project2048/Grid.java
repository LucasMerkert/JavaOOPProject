package de.frauas.oopj.project2048;

/**
 * @author Tarik
 *
 */
public class Grid {
    Tile[][] matrix;
    int width;
    int height;

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

    /**
     * TODO
     */
    public void swipeUp() {
        for(int column=0; column<width; column++) {
            for(int row=0; row<height; row++) {
                if(matrix[column][row] != null) {

                }
            }
        }
    }

    /**
     * Determine if swipe up is possible
     * @return true if swipe up is possible
     */
    private boolean isSwipeUpPossible() {
        return true;
    }

    /**
     * TODO
     */
    public void swipeLeft() {
        for(int column=0; column<width; column++) {
            for(int row=0; row<height; row++) {
                if(matrix[column][row] != null) {

                }
            }
        }
    }

    /**
     * Determine if swipe Left is possible
     * @return true if swipe left is possible
     */
    private boolean isSwipeLeftPossible() {
        return true;
    }

    /**
     * TODO
     */
    public void swipeRight() {
        for(int column=0; column<width; column++) {
            for(int row=0; row<height; row++) {
                if(matrix[column][row] != null) {

                }
            }
        }
    }

    /**
     * Determine if swipe right is possible
     * @return true if swipe right is possible
     */
    private boolean isSwipeRightPossible() {
        return true;
    }

    /**
     * TODO
     */
    public void swipeDown() {
        for(int column=0; column<width; column++) {
            for(int row=0; row<height; row++) {
                if(matrix[column][row] != null) {

                }
            }
        }
    }

    /**
     * Determine if swipe down is possible
     * @return true if swipe down is possible
     */
    private boolean isSwipeDownPossible() {
        return true;
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
