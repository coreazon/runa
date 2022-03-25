package model.entity.mobs;

import model.entity.AttackType;
import model.entity.HealthPoints;
import model.entity.Score;

public enum MonsterCards implements DamageCalculatorMob {

    SCRATCH("Scratch", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 5 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    CLAW("Claw", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 6 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    SMASH("Smash", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 8 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    BITE("Bite", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 10 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    FOCUS("Focus", AttackType.NONE){
        @Override
        public HealthPoints calculatedamage(Score level) {
            return new HealthPoints(0);
        }
    },
    BLOCK("Block", AttackType.DEFENSE){
        @Override
        public HealthPoints calculatedamage(Score level) {
            return new HealthPoints(7);
        }
    },
    DEFLECT("Deflect", AttackType.DEFENSE){
        @Override
        public HealthPoints calculatedamage(Score level) {
            return new HealthPoints(11 * level.getNumber() + 2);
        }
    },
    WATER("Water", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 8 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    ICE("Ice", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 10 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    FIRE("Fire", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 12 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    LIGHTNING("Lightning", AttackType.ATTACK){
        @Override
        public HealthPoints calculatedamage(Score level) {
            int damage = 14 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    };

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
