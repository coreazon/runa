package model.entity.mobs;

import model.entity.FocusPoints;

import java.util.ArrayList;

public class RegularMonster extends Monster {

    private final Mobs mob;
    private static final String TO_STRING_FORMAT = "%s (%d HP, %d FP): attempts %s next";


    public RegularMonster(Mobs mob, FocusPoints focusPoints) {
        super(mob.getRepresentation(), mob.getHealthPoints(), focusPoints, (ArrayList<MonsterCard>) mob.getCards(), mob.getType());
        this.mob = mob;
    }

    public Mobs getMob() {
        return mob;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, getName()
                , getHealthPoints().getHealthPoints()
                , getFocusPoints().getFocusPoints()
                , getCards().get(0).toString());
    }
}
