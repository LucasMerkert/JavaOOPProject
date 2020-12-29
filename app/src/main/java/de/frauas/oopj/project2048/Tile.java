package de.frauas.oopj.project2048;

/**
 * @author Tarik
 *
 */
public class Tile {
    private int value;

    /**
     * Constructor for tile object,
     * @param value
     * @throws TileSpawnException if value is not 2 or 4
     */
    public Tile(int value) throws TileSpawnException{
        if (value == 2 || value == 4) {
            this.value=value;
        } else throw new TileSpawnException();
    }
    /**
     * Levels up (doubles in value) the corresponding tile
     */
    public void levelUP() {
        value = 2*value;
        if (value>=2048) {
            //TODO: Winning condition 1
        }
    }

    @Override
    /**
     * Outputs tile as String; Overrides 'toString()' from java.lang.Object
     */
    public String toString(){
        return "("+ Integer.toString(value) + ")";
    }

}
