package de.frauas.oopj.project2048;

import java.util.Random;

/**
 * @author Tarik, Friedrich, Ana, Lucas
 *
 */
public class Grid {
	Tile[][] matrix;
	int WIDTH;
	int HEIGHT;
	int tileCount;
	boolean change = false;
	int[] pos_free_array;
	public static int pivotTile;

	//NEED GRID PRINT FUNCTION IWIE
	/**
	 * Contructor for Grid object, the main playing field. The top-left corner of the grid is [0][0], the one to the right of it [1][0]; the one below [0][1]
	 * @param width width of game grid
	 * @param height height of game grid
	 */
	public Grid(int width, int height) {
		this.tileCount = 0;
		this.WIDTH = width;
		this.HEIGHT = height;
		matrix = new Tile[WIDTH][HEIGHT];
		pos_free_array = new int[16];
		for(int i=0; i<16 ;i++){
			pos_free_array[i] = i;
		}
		initSpawn();
	}

	/**
	 * Spawn 2 Tiles at the beginning of a game
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

	enum pivotTyp{
		EMPTY(0),
		UP(1),
		LEFT(1),
		DOWN(-1),
		RIGHT(-1);

		private int value;
		pivotTyp(int value){
			this.value = value;
		}
	}


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
                System.out.print(row + " = Row " + column +" = Column\n" );
                System.out.print("Pivot " + pivotTile + "\n");

                //current tile is empty; nothing to do
                if (matrix[column][row] == null) {}
                //pivotTile is empty; current tile is moved to pivotTile
                else if (matrix[column][pivotTile] == null) {
                    //want to compare contents at said coordinate
					pivotTile = slideTile(column, row, pivotTile, true, pivotTyp.EMPTY);

                    //Animation
                    //Sound
                }
                //pivotTile and current tile have same value; merge into eachother
                else if (matrix[column][pivotTile].getExp() == matrix[column][row].getExp()){

					pivotTile = mergeTile(column, row, pivotTile, true, pivotTyp.UP);

                    //Animation
                    //Sound
                }
                //pivotTile and current tile have different value; they collide and dont merge
                else if ( row > pivotTile+1) {
					pivotTile = slideTile(column, row, pivotTile, true, pivotTyp.UP);

                	//Animation
                    //Sound
                }
                else pivotTile++;
            }
        }
        if(change)spawnNewTile();
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
                System.out.print(row + " = Row " + column +" = Column\n" );
                System.out.print("Pivot " + pivotTile + "\n");

                //current tile is empty; nothing to do
                if (matrix[column][row] == null) {}
                //pivotTile is empty; current tile is moved to pivotTile
                else if (matrix[column][pivotTile] == null) {
                    //want to compare contents at said coordinate
					pivotTile = slideTile(column, row, pivotTile, true, pivotTyp.EMPTY);

                    //Animation
                    //Sound
                }
                //MERGE into eachother, pivotTile and current tile have same value;
                else if (matrix[column][pivotTile].getExp() == matrix[column][row].getExp()){

					pivotTile = mergeTile(column, row, pivotTile, true, pivotTyp.DOWN);

                    //Animation
                    //Sound
                }
                //pivotTile and current tile have different value; they collide and dont merge
                else if ( row < pivotTile-1) {
					pivotTile = slideTile(column, row, pivotTile, true, pivotTyp.DOWN);

                    //Animation
                    //Sound
                }
                else pivotTile--;
            }
        }
        if(change)spawnNewTile();
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
            System.out.print("Neue Zeile\n");
            for(int column = 1; column < WIDTH; column++) {
                System.out.print(row + " = Row " + column +" = Column\n" );
                System.out.print("Pivot " + pivotTile + "\n");

                //current tile is empty; nothing to do
                if (matrix[column][row] == null) {}
                //pivotTile is empty; current tile is moved to pivotTile
                else if (matrix[pivotTile][row] == null) {
                    //want to compare contents at said coordinate
					pivotTile = slideTile(column, row, pivotTile, false, pivotTyp.EMPTY);

                    //Animation
                    //Sound
                }
                //pivotTile and current tile have same value; merge into eachother
                else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){
					pivotTile = mergeTile(column, row, pivotTile, false, pivotTyp.LEFT);

                    //Animation
                    //Sound
                }
                //pivotTile and current tile have different value; they collide and dont merge
                else if ( column > pivotTile+1) {
					pivotTile = slideTile(column, row, pivotTile, false, pivotTyp.LEFT);

                    //Animation
                    //Sound
                }
                else pivotTile++;
            }
        }
        if(change)spawnNewTile();
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
            System.out.print("Neue Zeile\n");
			for(int column = WIDTH - 2; column > -1; column--) {
				System.out.print(row + " = Row " + column +" = Column\n" );
				System.out.print("Pivot " + pivotTile + "\n");
				//current tile is empty; nothing to do
				if (matrix[column][row] == null) {}
				//pivotTile is empty; current tile is moved to pivotTile
				else if (matrix[pivotTile][row] == null) {
					//want to compare contents at said coordinate
					pivotTile = slideTile(column, row, pivotTile, false, pivotTyp.EMPTY);

					//Animation
					//Sound
				}
				//pivotTile and current tile have same value; merge into eachother
				else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){
					pivotTile = mergeTile(column, row, pivotTile, false, pivotTyp.RIGHT);

					//Animation
					//Sound
					pivotTile--;
				}
				//pivotTile and current tile have different value; they collide and dont merge
				else if ( column < pivotTile-1) {
					pivotTile = slideTile(column, row, pivotTile, false, pivotTyp.RIGHT);

					//Animation
					//Sound
					pivotTile--;
				}
				else pivotTile--;
			}
		}
		if(change)spawnNewTile();
		return change;
	}


	// WARUM WURDE private void slideTile(int column, int row, int i, boolean b) vorgeschlagen geht pivotTile auch?
	/**
	 * Function to slide the pivotTile into the correct position
	 * @param sameColumn true for swipeUp and swipeDown
	 * @param typ if value of pivot tile equals empty typ = pivotTyp.EMPTY else if swiped down or right typ = pivotTyp.DOWNORRIGHT or up and left typ = pivotTyp.UPORLEFT
	 * */
	private int slideTile(int column, int row, int pivotTile, boolean sameColumn, pivotTyp typ) {
		if(sameColumn){
			System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + column + "," + (pivotTile + typ.value) + ")");
			matrix[column][pivotTile + typ.value] = matrix[column][row];
		}else{
			System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + (pivotTile + typ.value) + "," + row + ")");
			matrix[pivotTile + typ.value][row] = matrix[column][row];
		}
		deleteTile(column, row);
		//pivotTile += typ.value;
		change = true;
		return pivotTile + typ.value;
	}

	private int mergeTile(int column, int row, int pivotTile, boolean sameColumn, pivotTyp typ) {
		if(sameColumn){
			System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + column + "," + pivotTile + ")");
			matrix[column][pivotTile].upgrade();
		}else{
			System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + pivotTile + "," + row + ")");
			matrix[pivotTile][row].upgrade();
		}
		deleteTile(column, row);
		//pivotTile += typ.value;
		change = true;
		return pivotTile + typ.value;
	}



	private void deleteTile(int column, int row) {
		matrix[column][row] = null;
		tileCount--;
	}


	/**
	 * Spawn 1 Tile with the value 2 or 4
	 * @return true if successfully spawned the tiles
	 */
	private boolean spawnNewTile() {
		if(tileCount == 16){
			return false;
		}else{
			int r_randomSpace, i= 0;
			Random dice = new Random();
			r_randomSpace = dice.nextInt(16 - tileCount++);
			  //value of range [0,tileCount)
			for(int column = 0; column < WIDTH; column++) {
				for(int row = 0; row < HEIGHT; row++){
					if(matrix[column][row] == null) {
						if(i == r_randomSpace) {
							matrix[column][row] = new Tile(1);
							System.out.println("TileCount:" + tileCount);
							System.out.println("New tile spawned at ("+ column + "," + row + ")");
						}
						i++;
					}
				}
			}
			tileCount++;
			return true;
		}
		/*
		else{
			int r_column, r_row, r_exp;
			do{
				Random dice = new Random();
				r_column = dice.nextInt(4);   //value of range [0,4)
				r_row    = dice.nextInt(4); 		  //value of range [0,4)
				r_exp    = dice.nextInt() % 2;			//value of 0 or 1

				Math.random();
			}while(matrix[r_column][r_row] != null);
			System.out.println("New tile spawned at ("+ r_column + "," + r_row + ")");
			matrix[r_column][r_row] = new Tile(1);
			tileCount++;
			return true;
		}*/
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
