package model.entity.mobs;

import model.entity.FocusPoints;

import java.util.ArrayList;

public class BossMonster extends Monster {

    private final BossMobs boss;
    private static final String TO_STRING_FORMAT = "%s (%d HP, %d FP): attempts %s next";

    public BossMonster(BossMobs boss, FocusPoints focusPoints) {
        super(boss.getRepresentation(), boss.getHealthPoints(), focusPoints, (ArrayList<MonsterCard>) boss.getCards(), boss.getType());
        this.boss = boss;
    }

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
