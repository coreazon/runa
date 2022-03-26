package model.entity.mobs;

import model.entity.FocusPoints;

import java.util.ArrayList;

public class RegularMonster extends Monster {

    private final Mobs mob;

    public RegularMonster(Mobs mob, FocusPoints focusPoints) {
        super(mob.getRepresentation(), mob.getHealthPoints(), focusPoints, (ArrayList<MonsterCard>) mob.getCards(), mob.getType());
        this.mob = mob;
    }

    public Mobs getMob() {
        return mob;
    }
}
