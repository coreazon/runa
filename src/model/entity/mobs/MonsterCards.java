package model.entity.mobs;

import model.entity.AttackType;

public enum MonsterCards {

    SCRATCH("Scratch", AttackType.ATTACK),
    CLAW("Claw", AttackType.ATTACK),
    SMASH("Smash", AttackType.ATTACK),
    BITE("Bite", AttackType.ATTACK),
    FOCUS("Focus", AttackType.NONE),
    BLOCK("Block", AttackType.DEFENSE),
    DEFLECT("Deflect", AttackType.DEFENSE),
    WATER("Water", AttackType.ATTACK),
    ICE("Ice", AttackType.ATTACK),
    FIRE("Fire", AttackType.ATTACK),
    LIGHTNING("Lightning", AttackType.ATTACK);

    private final String representation;
    private final AttackType attackType;

    MonsterCards(String representation, AttackType attackType) {
        this.representation = representation;
        this.attackType = attackType;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public String getRepresentation() {
        return representation;
    }
}
