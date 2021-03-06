package de.frauas.oopj.project2048;

/**
 * Direction enum for use in Grid class.
 * @author ana, lucas, tarik, friedrich
 */
enum Direction{
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