package model.entity.mobs;

import model.entity.FocusPoints;

public class RegularMonster extends Monster {

    private final Mobs mob;

    public RegularMonster(Mobs mob, FocusPoints focusPoints) {
        super(mob.getHealthPoints(), focusPoints, mob.getCards());
        this.mob = mob;
    }

    public Mobs getMob() {
        return mob;
    }
}
