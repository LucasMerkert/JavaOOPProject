package de.frauas.oopj.project2048;

/**
 * Direction enum for use in Gris class.
 */
enum Direction{
	EMPTY(0),
	UP(1),
	LEFT(1),
	DOWN(-1),
	RIGHT(-1);

	private final int value;
	
	Direction(int value){
		this.value = value;
	}

	/**
	 * Value getter method
	 * @return value
	 */
	public int value(){
		return value;
	}
}