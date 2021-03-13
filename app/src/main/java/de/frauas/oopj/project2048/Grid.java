package de.frauas.oopj.project2048;

import android.content.Context;

import java.util.Random;

import static de.frauas.oopj.project2048.Direction.*;

/**
 * The Grid class is where all the logical things happen on the grid.
 * @author Tarik, Friedrich, Ana, Lucas
 */
public class Grid {
	private Tile[][] matrix;
	private int WIDTH;
	private int HEIGHT;
	private int tileCount;
	private int currentScore;
	private boolean change = false;
	private int[] pos_free_array;
	private static int pivotTile;
	private boolean looseFlag = false;
	public Context context;

	//SOUND test
	private SoundPlayer sound;

	/**
	 * Contructor for Grid object, the main playing field. The top-left corner of the grid is [0][0],
	 * the one to the right of it [1][0]; the one below [0][1]
	 * @param width width of game grid
	 * @param height height of game grid
	 * @param context is context
	 */
	public Grid(int width, int height, Context context) {
		this.context = context;
		this.tileCount = 0;
		this.WIDTH = width;
		this.HEIGHT = height;
		matrix = new Tile[WIDTH][HEIGHT];
		pos_free_array = new int[16];
		for(int i=0; i<16 ;i++){
			pos_free_array[i] = i;
		}
		initSpawn();

		//sound test
		sound = new SoundPlayer(context);
	}

	/**
	 * Gets called by Grid constructor
	 * Spawns 2 Tiles at the beginning of a game
	 */
	private void initSpawn() {
		spawnNewTile();
		spawnNewTile();
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
        for(int column = 0; column< WIDTH; column++) {
            pivotTile = 0;
            System.out.print("Neue Zeile\n");
            for(int row = 1; row < HEIGHT; row++) {
				printPosition(row, column, pivotTile, true);
                //current tile is not empty; something to do
                if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is moved to pivotTile
					if (matrix[column][pivotTile] == null) {
						//want to compare contents at said coordinate
						pivotTile = slideTile(column, row, pivotTile, true, EMPTY);

						//Animation
						//Sound
					}
					//pivotTile and current tile have same value; merge into eachother
					else if (matrix[column][pivotTile].getExp() == matrix[column][row].getExp()){

						pivotTile = mergeTile(column, row, pivotTile, UP);

						//Animation
						//Sound
					}
					//pivotTile and current tile have different value; they collide and dont merge
					else if ( row > pivotTile+1) {
						pivotTile = slideTile(column, row, pivotTile, true, UP);

						//Animation
						//Sound
					}
					else pivotTile++;
				}
            }
        }
		checkSpawnNewTile();
		sound.playSound();
        return change;
	}


	/**
	 * swipeDown: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return true if tiles changed position, to spawn new tile
	 */
	public boolean swipeDown() {
		change = false;
        for(int column = 0; column< WIDTH; column++) {
            pivotTile = HEIGHT - 1;
            System.out.print("Neue Zeile\n");
            for(int row = HEIGHT - 2; row > -1; row--) {
				printPosition(row, column, pivotTile, false);
                //current tile is empty; nothing to do
                if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is moved to pivotTile
					if (matrix[column][pivotTile] == null) {
						//want to compare contents at said coordinate
						pivotTile = slideTile(column, row, pivotTile, true, EMPTY);

						//Animation
						//Sound
					}
					//MERGE into eachother, pivotTile and current tile have same value;
					else if (matrix[column][pivotTile].getExp() == matrix[column][row].getExp()){

						pivotTile = mergeTile(column, row, pivotTile, DOWN);

						//Animation
						//Sound
					}
					//pivotTile and current tile have different value; they collide and dont merge
					else if ( row < pivotTile-1) {
						pivotTile = slideTile(column, row, pivotTile, true, DOWN);

						//Animation
						//Sound
					}
					else pivotTile--;
				}
            }
        }
		checkSpawnNewTile();
		sound.playSound();
        return change;
	}

	/**
	 * swipeLeft: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return true if tiles changed position, to spawn new tile
	 */
	public boolean swipeLeft() {
		change = false;
        for(int row = 0; row< HEIGHT; row++) {
            pivotTile = 0;
            System.out.print("Neue Spalte\n");
            for(int column = 1; column < WIDTH; column++) {
				printPosition(row, column, pivotTile, true);
                //current tile is empty; nothing to do
                if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is moved to pivotTile
					if (matrix[pivotTile][row] == null) {
						//want to compare contents at said coordinate
						pivotTile = slideTile(column, row, pivotTile, false, EMPTY);

						//Animation
						//Sound
					}
					//pivotTile and current tile have same value; merge into eachother
					else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){
						pivotTile = mergeTile(column, row, pivotTile, LEFT);

						//Animation
						//Sound
					}
					//pivotTile and current tile have different value; they collide and dont merge
					else if ( column > pivotTile+1) {
						pivotTile = slideTile(column, row, pivotTile, false, LEFT);

						//Animation
						//Sound
					}
					else pivotTile++;
				}
            }
        }
		checkSpawnNewTile();
		sound.playSound();
        return change;
	}

	/**
	 * swipeRight: Merges tiles if possible and moves Tiles to new position in swipe direction
	 * @return true if tiles changed position, to spawn new tile
	 */
	public boolean swipeRight() {
		change = false;
		for(int row = 0; row< HEIGHT; row++) {
			pivotTile = WIDTH - 1;
            System.out.print("Neue Spalte\n");
			for(int column = WIDTH - 2; column > -1; column--) {
				printPosition(row, column, pivotTile, true);
				//current tile is empty; nothing to do
				if (matrix[column][row] != null) {
					//pivotTile is empty; current tile is moved to pivotTile
					if (matrix[pivotTile][row] == null) {
						//want to compare contents at said coordinate
						pivotTile = slideTile(column, row, pivotTile, false, EMPTY);

						//Animation
						//Sound
					}
					//pivotTile and current tile have same value; merge into each other
					else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){
						pivotTile = mergeTile(column, row, pivotTile, RIGHT);

						//Animation
						//Sound
					}
					//pivotTile and current tile have different value; they collide and dont merge
					else if ( column < pivotTile-1) {
						pivotTile = slideTile(column, row, pivotTile, false, RIGHT);

						//Animation
						//Sound
					}
					else pivotTile--;
				}
			}
		}

		checkSpawnNewTile();
		sound.playSound();
		return change;
	}

	/**
	 * checks if change == true theb calls method spawnNewTile and checks return value if another swipe is possible
	 * prints out control statements if swipe is possible or gameover
	 */
	private void checkSpawnNewTile() {
		if(change) {
			if (spawnNewTile()) {
				System.out.print("SWIPE MÖGLICH o)\n");
			} else {
				looseFlag = true;
				System.out.print("------------------------------------------DU BIST EIN KNOOB o)------------------------------------------------------------\n");
				System.out.println("TileCount:" + tileCount);
				System.out.print("Current Score: " + currentScore + "\n");
			}
		}
	}

	/**
	 * is a method to print out the CurrentTile and PivotTile for debugging
	 *
	 * @param row is the row to be print
	 * @param column is the column to be print
	 * @param pivotTile is the pivotTile to be print
	 * @param byRow	true if column same(up and down swipe) and false if row same(right and left swipe)
	 */
	private void printPosition(int row, int column, int pivotTile, boolean byRow) {
		if(byRow){
			System.out.print("CurrentTile = (" + column + "/" + row + ") (column/row)\n" );
			System.out.print("PivotTile = (" + column + "/" + pivotTile + ") (column/row)\n");
		}else{
			System.out.print("CurrentTile = (" + column + "/" + row + ") (column/row)\n" );
			System.out.print("PivotTile = (" + pivotTile + "/" + row + ") (column/row)\n");
		}
	}


	// WARUM WURDE private void slideTile(int column, int row, int i, boolean b) vorgeschlagen geht pivotTile auch?

	/**
	 *Method to slide the Tiles from the position matrix[column][row] -> matrix[column][pivotTile] or matrix[column][row] -> matrix[pivotTile][row]
	 *
	 * @param column is the column of the slided Tile
	 * @param row is the row of the slided Tile
	 * @param pivotTile is the tile which the currentTile is being slid to
	 * @param sameColumn true for swipeUp and swipeDown
	 * @param typ is the slide direction (UP,DOWN,RIGHT,LEFT,(EMPTY))
	 */

	private int slideTile(int column, int row, int pivotTile, boolean sameColumn, Direction typ) {
		if(sameColumn){
			System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + column + "," + (pivotTile + typ.value()) + ")");
			matrix[column][pivotTile + typ.value()] = matrix[column][row];
			//gameView.slideColumnAnimation(column,row,column,pivotTile+ typ.value(),matrix[column][pivotTile + typ.value()].getValue());//move Tile from: x1,y1 -> x2,y2
		}else{
			System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + (pivotTile + typ.value()) + "," + row + ")");
			matrix[pivotTile + typ.value()][row] = matrix[column][row];
		}
		deleteTile(column, row, false);
		change = true;
		return pivotTile + typ.value();
	}

	/**
	 * merges matrix[column][row] -> matrix[column][pivotTile], triggers animation
	 * @param column is the column of the merging tile
	 * @param row is the row of the merging tile
	 * @param pivotTile is the position of the new merged tile
	 * @param //sameColumn true if the column is the same(down or up swipe); false if the column is different(right of left swipe)
	 * @param typ is the swipe direction
	 * @return
	 */
												//boolean sameColumn
	private int mergeTile(int column, int row, int pivotTile, Direction typ) {
		// EMPTY EXEPTION !!!!
		if(typ == UP || typ == DOWN){
			System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + column + "," + pivotTile + ")\n");
			matrix[column][pivotTile].upgrade();
			currentScore +=matrix[column][pivotTile].getValue();
			System.out.print("Current Score: " + currentScore +"\n");
		}else{
			System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + pivotTile + "," + row + ")\n");
			matrix[pivotTile][row].upgrade();
			System.out.print("Current Score: " + currentScore +"\n");
			currentScore +=matrix[pivotTile][row].getValue();
		}
		deleteTile(column, row, true);
		change = true;
		return pivotTile + typ.value();
	}

	/**
	 *
	 * @param column is the column of Tile which should be deleted
	 * @param row is the row of Tile which should be deleted
	 * @param lowerTileCount true if tile gets merged tile count gets decremented; false if tile gets slided tile count stays the same
	 */
	private void deleteTile(int column, int row, boolean lowerTileCount) {
		matrix[column][row] = null;
		if(lowerTileCount) tileCount--;
	}

	/**
	 * Spawn 1 Tile with the value 2 or 4 and if tileCount == 16 checks if swipePossible
	 * @return true if successfully spawned the tiles; false when no more sliding possible
	 */
	// spawning 4 wenn bestimmter score erreicht wurde mit chance
	private boolean spawnNewTile() {
		int r_randomSpace, i= 0;

		Random dice = new Random();
		//value of range [0, 16 - tileCount++)
		r_randomSpace = dice.nextInt(16 - tileCount++);

		for(int column = 0; column < WIDTH; column++) {
			for(int row = 0; row < HEIGHT; row++){
				if(matrix[column][row] == null) {
					if(i == r_randomSpace) {
						matrix[column][row] = new Tile(1, context);
						System.out.println("New tile spawned at ("+ column + "," + row + ")");
					}
					i++;
				}
			}
		}
		if(tileCount == 16){
			return swipePossible();
		}
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
				//System.out.print("AUSGABE NR 1 CurrentTile = (" + row + "/" + column + ") and otherTile = (" + (row+1) + "/" + column + ")\n");
				if(matrix[column][row].getExp() == matrix[column][row+1].getExp()) return true;
			}
		}
		for(int row = 0; row < HEIGHT; row++) {
			for(int column = 0; column < WIDTH-1; column++){
				//System.out.print("AUSGABE NR 2 CurrentTile = (" + row + "/" + column + ") and otherTile = (" + row + "/" + (column+1) + ")\n");
				if(matrix[column][row].getExp() == matrix[column+1][row].getExp()) return true;
			}
		}
		return false;
	}

	/**
	 *	method to get current score
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
	 *
	 * @param x column of a Value in the grid matrix
	 * @param y row of a Value in the grid matrix
	 * @return returns value of the tile in matrix [x][y] = [column][row]
	 */
	public int getValue(int x, int y){
		if(matrix[x][y] == null) {
			return 0;
		} else {
			return matrix[x][y].getValue();
		}
	}

	/**
	 *
	 * @param x column of a tile in the grid matrix
	 * @param y row of a tile in the grid matrix
	 * @return tile of matrix[x][y]
	 */
	public Tile getTileAtPos(int x, int y){
		return matrix[x][y];
	}

}
