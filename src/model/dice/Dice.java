package model.dice;

/**
 * This class represents a dice.
 *
 * @author urliz
 * @version 1.0
 */
public class Dice {

    private int sides;

    /**
     * creates a new dice
     *
     * @param side sides of the dice
     */
    public Dice(int side) {
        this.sides = side;
    }

    /**
     * return the amount of sides of the dice
     *
     * @return the amount as integer
     */
    public int getSides() {
        return sides;
    }

    /**
     * sets the sides of the dice to a new value
     *
     * @param sides new value of sides of the dice
     */
    public void setSides(int sides) {
        this.sides = sides;
    }
}
