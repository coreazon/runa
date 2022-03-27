package model.entity;

/**
 * This clas represents focusPoints
 *
 * @author urliz
 * @version 1.0
 */
public class FocusPoint {

    private int FocusPoints;

    /**
     * Creates a new FocusPoint
     *
     * @param FocusPoints focus points
     */
    public FocusPoint(int FocusPoints) {
        this.FocusPoints = FocusPoints;
    }

    /**
     * returns the focusPoints
     *
     * @return the focusPoints
     */
    public int getFocusPoints() {
        return FocusPoints;
    }

    /**
     * sets the focusPoints
     *
     * @param focusPoints the focusPoints
     */
    public void setFocusPoints(int focusPoints) {
        FocusPoints = focusPoints;
    }
}
