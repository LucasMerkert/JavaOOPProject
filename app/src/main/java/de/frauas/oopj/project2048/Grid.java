package de.frauas.oopj.project2048;

import android.content.Context;

import java.util.Random;

import static android.icu.lang.UProperty.MATH;
import static de.frauas.oopj.project2048.Direction.*;

/**
 * The Grid maintains the logical functioning of the game
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class Grid {

	private static final int MIN_SPAWN_FOUR_SCORE = 1000;

	private final Tile[][] matrix;
	private final int WIDTH;
	private final int HEIGHT;
	private int tileCount;
	private int currentScore;
	private boolean change = false;
	private static int pivotTile;
	private boolean looseFlag = false;
	public Context context;

	/**
	 * Constructor for Grid object, the main playing field. The top-left corner of the grid is [0][0] ([column][row]),
	 * bottom-right: [3][3],
	 * @param context of your current android activity
	 */
	public Grid( Context context) {
		Application2048 app = (Application2048) context.getApplicationContext();
		this.WIDTH = app.getWidth();
		this.HEIGHT = app.getHeight();
		if(WIDTH < 4){
			throw new IllegalArgumentException("Width too small");
		}
		if(HEIGHT < 4){
			throw new IllegalArgumentException("Height too small");
		}
		if(context == null){
			throw new IllegalArgumentException("Context ");
		}

		this.context = context;
		this.tileCount = 0;
		matrix = new Tile[WIDTH][HEIGHT];
		initSpawn();
	}

	/**
	 * Gets called by Grid constructor
	 * Spawns 2 Tiles at the beginning of a game
	 */
	private void initSpawn() {
		spawnNewTile();
		spawnNewTile();
	}

	/**
	 * swipeUp: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return whether the grid has changed with the swipe
	 */
	public boolean swipeUp() {
		deleteTilePaths();
		change = false;
        for(int column = 0; column< WIDTH; column++) {
            pivotTile = 0;
            for(int row = 1; row < HEIGHT; row++) {
                //current tile is empty; nothing to do
                if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is slid to pivotTile
					if (matrix[column][pivotTile] == null) {
						pivotTile = slideTile(column, row, pivotTile, UP);
					}
					//pivotTile and current tile have same value; merge into each other
					else if (matrix[column][pivotTile].getExp() == matrix[column][row].getExp()){
						pivotTile = mergeTile(column, row, pivotTile, UP);
					}
					//pivotTile and current tile have different values; they collide, stay next to each other and DON'T merge
					else if ( row > pivotTile+1) {
						pivotTile = slideTile(column, row, pivotTile, UP);
					}
					else pivotTile++;
				}
            }
        }
		checkSpawnNewTile();
		return change;
	}

	/**
	 * swipeDown: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return whether the grid has changed with the swipe
	 */
	public boolean swipeDown() {
		deleteTilePaths();
		change = false;
        for(int column = 0; column< WIDTH; column++) {
            pivotTile = HEIGHT - 1;
            for(int row = HEIGHT - 2; row > -1; row--) {
                //current tile is empty; nothing to do
                if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is slid to pivotTile
					if (matrix[column][pivotTile] == null) {
						pivotTile = slideTile(column, row, pivotTile, DOWN);
					}
					//MERGE into each other, pivotTile and current tile have same value;
					else if (matrix[column][pivotTile].getExp() == matrix[column][row].getExp()){
						pivotTile = mergeTile(column, row, pivotTile, DOWN);
					}
					//pivotTile and current tile have different value; they collide, stay next to each other and DON'T merge
					else if ( row < pivotTile-1) {
						pivotTile = slideTile(column, row, pivotTile, DOWN);
					}
					else pivotTile--;
				}
            }
        }
		checkSpawnNewTile();
		return change;
	}

	/**
	 * swipeLeft: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return whether the grid has changed with the swipe
	 */
	public boolean swipeLeft() {
		deleteTilePaths();
		change = false;
        for(int row = 0; row< HEIGHT; row++) {
            pivotTile = 0;
            for(int column = 1; column < WIDTH; column++) {
                //current tile is empty; nothing to do
                if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is slid to pivotTile
					if (matrix[pivotTile][row] == null) {
						pivotTile = slideTile(column, row, pivotTile, LEFT);
					}
					//pivotTile and current tile have same value; merge into each other
					else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){
						pivotTile = mergeTile(column, row, pivotTile, LEFT);
					}
					//pivotTile and current tile have different value; they collide, stay next to each other and DON'T merge
					else if ( column > pivotTile+1) {
						pivotTile = slideTile(column, row, pivotTile, LEFT);
					}
					else pivotTile++;
				}
            }
        }
		checkSpawnNewTile();
		return change;
	}

	/**
	 * swipeRight: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return whether the grid has changed with the swipe
	 */
	public boolean swipeRight() {
		deleteTilePaths();
		change = false;
		for(int row = 0; row< HEIGHT; row++) {
			pivotTile = WIDTH - 1;
			for(int column = WIDTH - 2; column > -1; column--) {
				//current tile is empty; nothing to do
				if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is slid to pivotTile
					if (matrix[pivotTile][row] == null) {
						pivotTile = slideTile(column, row, pivotTile, RIGHT);
					}
					//pivotTile and current tile have same value; merge into each other
					else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){
						pivotTile = mergeTile(column, row, pivotTile, RIGHT);
					}
					//pivotTile and current tile have different value; they collide, stay next to each other and DON'T merge
					else if ( column < pivotTile-1) {
						pivotTile = slideTile(column, row, pivotTile, RIGHT);
					}
					else pivotTile--;
				}
			}
		}
		checkSpawnNewTile();
		return change;
	}

	/**
	 * method to spawn new tiles
	 * checks if change == true then calls method spawnNewTile and checks return value if another swipe is possible
	 * prints out control statements if swipe is possible or gameover
	 */
	private void checkSpawnNewTile() {
		// change checks if something changed (successful swipe/merge)
		if(change) {
			// call spawnNewTile which checks whether swipe is possible and then spawns new tile
			if (spawnNewTile()) {
				System.out.print("weitere swipes möglich o)\n");
			} else {
				// if swipe impossible, gameover
				looseFlag = true;
				System.out.print("------------------------------------------DU BIST EIN KNOOB o)------------------------------------------\n");
				System.out.println("TileCount:" + tileCount);
				System.out.print("Current Score: " + currentScore + "\n");
			}
		}
	}

	/**
	 * is a method to print out the CurrentTile and PivotTile for debugging
	 *
	 * @param row is the row to be printed
	 * @param column is the column to be printed
	 * @param pivotTile is the pivotTile to be printed
	 * @param typ is the direction of the swipe
	 */
	private void printPosition(int row, int column, int pivotTile, Direction typ) {
		if(row < 0 || row > HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		if(column < 0 || column > WIDTH){
			throw new IllegalArgumentException("column not [0," + WIDTH + "]");
		}
		// check all conditions in case of rectangular (not square) Grid
		if(pivotTile < 0 || pivotTile > WIDTH || pivotTile > HEIGHT){
			throw new IllegalArgumentException("pivotTile not [0," + WIDTH + "]");
		}
		if(typ == DOWN || typ == UP){
			System.out.print("CurrentTile = (" + column + "/" + row + ") (column/row)\n" );
			System.out.print("PivotTile = (" + column + "/" + pivotTile + ") (column/row)\n");
		}else{
			System.out.print("CurrentTile = (" + column + "/" + row + ") (column/row)\n" );
			System.out.print("PivotTile = (" + pivotTile + "/" + row + ") (column/row)\n");
		}
	}

	/**
	 *Method to slide the Tiles from the position matrix[column][row] -> matrix[column][pivotTile] or matrix[column][row] -> matrix[pivotTile][row]
	 *
	 * @param column is the column of the slided Tile
	 * @param row is the row of the slided Tile
	 * @param pivotTile is the tile which the currentTile is being slid to
	 * @param typ is the slide direction (UP,DOWN,RIGHT,LEFT,(EMPTY))
	 */
	private int slideTile(int column, int row, int pivotTile, Direction typ) {
		if(row < 0 || row > HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		if(column < 0 || column > WIDTH){
			throw new IllegalArgumentException("column not [0," + WIDTH + "]");
		}
		// check all conditions in case of rectangular (not square) Grid
		if(pivotTile < 0 || pivotTile > WIDTH || pivotTile > HEIGHT){
			throw new IllegalArgumentException("pivotTile not [0," + WIDTH + "]");
		}

		//determine swipe direction and move the Tile respectively
		if(typ == DOWN || typ == UP){
			if(matrix[column][pivotTile] == null){
				System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + column + "," + (pivotTile) + ")");
				matrix[column][pivotTile] = matrix[column][row];
			}else{
				System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + column + "," + (pivotTile + typ.value()) + ")");

				matrix[column][pivotTile + typ.value()] = matrix[column][row];
				pivotTile += typ.value();
			}
			matrix[column][pivotTile].setPath(column, row, false);
		}else{
			if(matrix[pivotTile][row] == null){
				System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + column + "," + (pivotTile) + ")");
				matrix[pivotTile][row] = matrix[column][row];
			}else{
				System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + (pivotTile + typ.value()) + "," + row + ")");
				matrix[pivotTile + typ.value()][row] = matrix[column][row];
				pivotTile += typ.value();
			}
			matrix[pivotTile][row].setPath(column, row, false);
		}
		deleteTile(column, row, false);
		change = true;
		return pivotTile;
	}


	/**
	 * merges two Tile and upgrade exponent
	 * @param column is the column of the merging tile
	 * @param row is the row of the merging tile
	 * @param pivotTile is the position of the new merged tile
	 * @param typ is the swipe direction
	 * @return returns the value of the new pivotTile
	 */
	private int mergeTile(int column, int row, int pivotTile, Direction typ) {
		if(row < 0 || row > HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		if(column < 0 || column > WIDTH){
			throw new IllegalArgumentException("column not [0," + WIDTH + "]");
		}
		// check all conditions in case of rectangular (not square) Grid
		if(pivotTile < 0 || pivotTile > WIDTH || pivotTile > HEIGHT){
			throw new IllegalArgumentException("row not [0," + WIDTH + "]");
		}

		//determine swipe direction and merge tiles
		if(typ == DOWN || typ == UP){
			matrix[column][pivotTile].setPath(column, row, true);
			System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + column + "," + pivotTile + ")\n");
			matrix[column][pivotTile].upgrade();
			currentScore +=matrix[column][pivotTile].getValue();
		}else{
			// swipe direction is left or right
			matrix[pivotTile][row].setPath(column, row, true);
			System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + pivotTile + "," + row + ")\n");
			matrix[pivotTile][row].upgrade();
			currentScore +=matrix[pivotTile][row].getValue();
		}
		System.out.print("Current Score: " + currentScore +"\n");
		deleteTile(column, row, true);
		change = true;
		return pivotTile + typ.value();
	}

	/**
	 * deletes matrix[column][row] tile and lowers TileCount if needed
	 *
	 * @param column is the column of Tile which should be deleted
	 * @param row is the row of Tile which should be deleted
	 * @param lowerTileCount true if tile gets merged tile count gets decremented; false if tile gets slided tile count stays the same
	 */
	private void deleteTile(int column, int row, boolean lowerTileCount) {
		if(row < 0 || row > HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		if(column < 0 || column > WIDTH){
			throw new IllegalArgumentException("column not [0," + WIDTH + "]");
		}
		matrix[column][row] = null;
		if(lowerTileCount) tileCount--;
	}

	/**
	 * Spawn 1 Tile with the value 2 or 4 and if tileCount == 16 checks if swipePossible
	 *
	 * Explanation of Algorithm: Tilecount marks empty spaces (value = null), from which a random number gets selected.
	 * The for loop then counts to that tile and inserts a new tile.
	 * @return true if successfully spawned the tiles; false when no more sliding possible
	 */
	private boolean spawnNewTile() {
		int r_randomSpace, i= 0, exp = 1;
		// generate dice in order to get random number
		Random dice = new Random();
		//select random value from range [0, 16 - tileCount++)
		r_randomSpace = dice.nextInt(16 - tileCount++);
		for (int column = 0; column < WIDTH; column++) {
			for (int row = 0; row < HEIGHT; row++) {
				if (matrix[column][row] == null) {
					if (i == r_randomSpace) {

						//Spawns also Tiles with value 4 if Score is high enough
						if(currentScore > MIN_SPAWN_FOUR_SCORE){
							Random dice4 = new Random();
							int r_randomSpaceFour = dice4.nextInt(100);
							if(r_randomSpaceFour <= 20) {
								exp = 2;
							}
						}
						matrix[column][row] = new Tile(exp, context);
						System.out.println("New tile spawned at (" + column + "," + row + ")");
					}
					i++;
				}
			}
		}
		if(tileCount == 16){
			return swipePossible();
		}
		//tileCount++;
		System.out.println("TileCount:" + tileCount);
		return true;
	}

	/**
	 *method to checks if a merge is somewhere possible
	 * @return true if merge is possible; false if no merges found
	 */
	private boolean swipePossible() {
		for(int column = 0; column < WIDTH; column++) {
			for(int row = 0; row < HEIGHT-1; row++){
				if(matrix[column][row].getExp() == matrix[column][row+1].getExp()) return true;
			}
		}
		for(int row = 0; row < HEIGHT; row++) {
			for(int column = 0; column < WIDTH-1; column++){
				if(matrix[column][row].getExp() == matrix[column+1][row].getExp()) return true;
			}
		}
		return false;
	}

	/**
	 * getter for current score
	 * @return int value of the currentScore
	 */
	public int getScore(){
		return currentScore;
	}

	/**
	 *	method to return state of looseFlag is up
	 * @return return state of looseFlag
	 */
	public boolean isGameOver() {
		return looseFlag;
	}

	/**
	 *Getter for the value of a tile at [x][y]
	 * @param x column of a Value in the grid matrix
	 * @param y row of a Value in the grid matrix
	 * @return returns value of the tile in matrix [x][y] = [column][row]
	 */
	public int getValue(int x, int y){
		if(y < 0 || y > HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		if(x < 0 || x > WIDTH){
			throw new IllegalArgumentException("row not [0," + WIDTH + "]");
		}
		if(matrix[x][y] == null) {
			return 0;
		} else {
			return matrix[x][y].getValue();
		}
	}

	/**
	 *Getter for the Tile at position [x][y]
	 * @param x column of a tile in the grid matrix
	 * @param y row of a tile in the grid matrix
	 * @return tile of matrix[x][y]
	 */
	public Tile getTileAtPos(int x, int y){
		if(y < 0 || y >= HEIGHT){
			throw new IllegalArgumentException("row not [0," + HEIGHT + "]");
		}
		if(x < 0 || x >= WIDTH){
			throw new IllegalArgumentException("row not [0," + WIDTH + "]");
		}
		return matrix[x][y];
	}

	/**
	 * method to clear TilePath from every Tile in Grid
	 * Gets called in the beginning of a swipe method
	 */
	public void deleteTilePaths(){
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				if (matrix[i][j] != null) {
					matrix[i][j].deletePath();
					matrix[i][j].setNewSpawn(false);
				}
			}
		}
	}



	/**
	 * getter for HEIGHT
	 * @return int value of HEIGHT
	 */
	public int getHEIGHT() { return HEIGHT;}

	/**
	 * getter for WIDTH
	 * @return int value of WIDTH
	 */
	public int getWIDTH() { return WIDTH;}

}

