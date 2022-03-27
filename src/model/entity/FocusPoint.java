package model.entity;

/**
 * This clas represents focusPoints
 *
 * @author urliz
 * @version 1.0
 */
public class FocusPoint {

    private int focusPoints;

    /**
     * Creates a new FocusPoint
     *
     * @param focusPoints focus points
     */
    public FocusPoint(int focusPoints) {
        this.focusPoints = focusPoints;
    }

    /**
     * returns the focusPoints
     *
     * @return the focusPoints
     */
    public int getFocusPoints() {
        return focusPoints;
    }

    /**
     * sets the focusPoints
     *
     * @param focusPoints the focusPoints
     */
    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }
}
