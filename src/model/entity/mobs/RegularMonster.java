package model.entity.mobs;

public class RegularMonster extends Monster {

    private final Mobs mob;

    public RegularMonster(Mobs mob) {
        super(mob.getHealthPoints());
        this.mob = mob;
    }
}
