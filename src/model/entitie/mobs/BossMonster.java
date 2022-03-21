package model.entitie.mobs;

public class BossMonster extends Monster {

    private final BossMobs boss;

    public BossMonster(BossMobs boss) {
        super(boss.getHealthPoints());
        this.boss = boss;
    }
}
