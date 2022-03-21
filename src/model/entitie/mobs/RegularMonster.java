package model.entitie.mobs;

import model.entitie.HealthPoints;

public class RegularMonster extends Monster {

    private final Mobs mob;

    public RegularMonster(Mobs mob) {
        super(mob.getHealthPoints());
        this.mob = mob;
    }
}
