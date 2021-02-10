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

	//NEED GRID PRINT FUNCTION IWIE
	/**
	 * Contructor for Grid object, the main playing field. The top-left corner of the grid is [0][0], the one to the right of it [1][0]; the one below [0][1]
	 * @param width
	 * @param height
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
		for(int column = 0; column< WIDTH; column++) {
			int pivotTile = WIDTH - 1;
			for(int row = 1; row< HEIGHT; row++) {
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
		for(int column = 0; column< WIDTH; column++) {
			int pivotTile = HEIGHT -1;
			for(int row = HEIGHT -2; row>0; row--) {
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
		for(int row = 0; row< HEIGHT; row++) {
			int pivotTile = 0;
			for(int column = 1; column< WIDTH; column++) {
				if (matrix[row][column] == null) continue;
				if (matrix[row][pivotTile].getExp() == matrix[row][column].getExp()){
					//merge! function?
					matrix[row][pivotTile].upgrade();
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
		for(int row = 0; row< HEIGHT; row++) {
			int pivotTile = WIDTH - 1;
			for(int column = WIDTH - 2; column > -1; column--) {
				//System.out.print(row + " = Row " + column +" = Column\n" );
				System.out.print("Pivot " + pivotTile + "\n");


				//current tile is empty; nothing to do
				if (matrix[column][row] == null) {}
				//pivotTile is empty; current tile is moved to pivotTile
				else if (matrix[pivotTile][row] == null) {
					//want to compare contents at said coordinate
					System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + pivotTile + "," + row + ")");
					//Verschiebung von Tile an Pivotstelle
					matrix[pivotTile][row] = matrix[column][row];
					matrix[column][row] = null;
					//Animation
					//Sound

					//grid changed to spawn new tiles
					change = true;
				}
				//pivotTile and current tile have same value; merge into eachother
				else if (matrix[pivotTile][row].getExp() == matrix[column][row].getExp()){

					System.out.println("Tile at (" + column +  "," + row + ") is merged with (" + pivotTile + "," + row + ")");
					//matrix[pivotTile][row].upgrade();
					matrix[pivotTile][row].upgrade();
					matrix[column][row] = null;
					tileCount--;
					//Animation
					//Sound

					//grid changed to spawn new tiles
					change = true;
					//pivotTile is moved to the left
					pivotTile--;

				}
				//pivotTile and current tile have diffrent value; they collide and dont merge
				else if ( column < pivotTile) {
					System.out.println("Tile at (" + column +  "," + row + ") collides (" + pivotTile + "," + row + ")");
					System.out.println("Tile at (" + column +  "," + row + ") is moved to (" + (pivotTile-1) + "," + row + ")");
					matrix[pivotTile-1][row] = matrix[column][row];
					matrix[column][row] = null;
					//Animation
					//Sound

					//grid changed to spawn new tiles
					change = true;
					pivotTile--;
				}
			}
		}
		spawnNewTile();
		return change;
	}


	/**
	 * Spawn 1 Tile with the value 2 or 4
	 * @return true if successfully spawned the tiles
	 */
	private boolean spawnNewTile() {
		if(tileCount == 16){
			return false;
		}
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
		}


        /*while(true){
            int position = new Random().nextInt(16);
            if(matrix[position/4][position%4] == null){
                matrix[position/4][position%4] = new Tile(1);
                return true;
            }
        }*/

        /*int[] pos_free_array = fill_array();
        //finds random field in matrix which is empty
        int position = new Random().nextInt((16 - tileCount));
        int new_tile_position = pos_free_array[position];
        System.out.print(position/4 + " " + position%4 + " Spawnposition of new Tile\n");
        matrix[position/4][position%4] = new Tile(1);
        //Canvas update needed

        tileCount++;
        return true;
         */
	}

	/**
	 * Finds all positions which are empty in matrix
	 * @return Array with empty matrix positions
	 */
	private int [] fill_array(){
		int count = 0;
		int empty_pos = 0;
		int[] array = new int[16-tileCount+1];
		for(int i = 0; i< WIDTH; i++){
			for(int j = 0; j< HEIGHT; j++){
				if(matrix[i][j] == null){
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
