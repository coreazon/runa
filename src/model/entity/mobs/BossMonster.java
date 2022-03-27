package model.entity.mobs;

import model.entity.FocusPoint;

/**
 * This class represents a Boss
 *
 * @author urliz
 * @version 1.0
 */
public class BossMonster extends Monster {

    private static final String TO_STRING_FORMAT = "%s (%d HP, %d FP): attempts %s next";
    private final BossMobs boss;

    /**
     * creates a new Boss
     *
     * @param boss        the boss
     * @param focusPoints the focus points of that boss
     */
    public BossMonster(BossMobs boss, FocusPoint focusPoints) {
        super(boss.getRepresentation(), boss.getHealthPoints(), focusPoints, boss.getCards(), boss.getType());
        this.boss = boss;
    }

    /**
     * returns the boss enum
     *
     * @return the enum
     */
    public BossMobs getBoss() {
        return boss;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, getName()
                , getHealthPoints().getHealthPoints()
                , getFocusPoints().getFocusPoints()
                , getCards().get(0).toString());
    }
}
