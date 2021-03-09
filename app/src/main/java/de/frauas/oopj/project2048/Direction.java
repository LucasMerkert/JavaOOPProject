package de.frauas.oopj.project2048;

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

	public int value(){
		return value;
	}
}