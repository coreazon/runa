package model.entity.mobs;

import model.entity.FocusPoints;

import java.util.ArrayList;

public class BossMonster extends Monster {

    private final BossMobs boss;

    public BossMonster(BossMobs boss, FocusPoints focusPoints) {
        super(boss.getRepresentation(), boss.getHealthPoints(), focusPoints, (ArrayList<MonsterCard>) boss.getCards(), boss.getType());
        this.boss = boss;
    }

    public BossMobs getBoss() {
        return boss;
    }

}
