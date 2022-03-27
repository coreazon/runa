package model.entity;

/**
 * THis class represents a score
 *
 * @author urliz
 * @version 1.0
 */
public class Score {

    private final int number;

    /**
     * Creates a new score
     *
     * @param number score
     */
    public Score(int number) {
        this.number = number;
    }

    /**
     * returns the score
     *
     * @return score
     */
    public int getNumber() {
        return number;
    }
}
