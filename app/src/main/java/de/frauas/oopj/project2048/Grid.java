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
     * 
     */
    void swipeUp() {
        for(int column=0; column<width; column++) {
            for(int row=0; row<height; row++) {
                if(matrix[column][row] != null) {

                }
            }
        }
    }




}
