package model.entity.mobs;

import model.entity.FocusPoint;

/**
 * THis class represents a regular monster
 *
 * @author urliz
 * @version 1.0
 */
public class RegularMonster extends Monster {

    private static final String TO_STRING_FORMAT = "%s (%d HP, %d FP): attempts %s next";
    private final Mobs mob;

    /**
     * creates a new regular monster
     *
     * @param mob         the monster
     * @param focusPoints the focus points
     */
    public RegularMonster(Mobs mob, FocusPoint focusPoints) {
        super(mob.getRepresentation(), mob.getHealthPoints(), focusPoints, mob.getCards(), mob.getType());
        this.mob = mob;
    }


    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, getName()
                , getHealthPoints().getHealthPoints()
                , getFocusPoints().getFocusPoints()
                , getCards().get(0).toString());
    }
}
