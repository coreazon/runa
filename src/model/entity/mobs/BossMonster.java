package model.entity.mobs;

import model.entity.FocusPoints;

public class BossMonster extends Monster {

    private final BossMobs boss;

    public BossMonster(BossMobs boss, FocusPoints focusPoints) {
        super(boss.getRepresentation(), boss.getHealthPoints(), focusPoints, boss.getCards(), boss.getType());
        this.boss = boss;
    }

    public BossMobs getBoss() {
        return boss;
    }

}
