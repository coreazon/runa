package model.entity.mobs;

import model.entity.AttackType;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.runa.AbilityType;

public enum MonsterCards implements DamageCalculatorMob {

    SCRATCH("Scratch", AttackType.ATTACK, AbilityType.NONE, true, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 5 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    CLAW("Claw", AttackType.ATTACK, AbilityType.NONE, true, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 6 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    SMASH("Smash", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 8 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    BITE("Bite", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 10 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    FOCUS("Focus", AttackType.NONE, AbilityType.NONE, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            return new HealthPoints(0);
        }
    },
    BLOCK("Block", AttackType.DEFENSE, AbilityType.PHYSICAL, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            return new HealthPoints(7 * level.getNumber());
        }
    },
    DEFLECT("Deflect", AttackType.DEFENSE, AbilityType.MAGICAL, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            return new HealthPoints(11 * level.getNumber() + 2);
        }
    },
    WATER("Water", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 8 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    ICE("Ice", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 10 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    FIRE("Fire", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 12 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    LIGHTNING("Lightning", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 14 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    };

    private final String representation;
    private final AttackType attackType;
    private final AbilityType defenseType;
    private final boolean breakFocus;
    private final AbilityType abilityType;

    MonsterCards(String representation, AttackType attackType, AbilityType defenseType, boolean breakFocus, AbilityType abilityType) {
        this.representation = representation;
        this.attackType = attackType;
        this.defenseType = defenseType;
        this.breakFocus = breakFocus;
        this.abilityType = abilityType;
    }

    public AbilityType getType() {
        return abilityType;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public String getRepresentation() {
        return representation;
    }

    public AbilityType getDefense() {
        return this.defenseType;
    }

    public boolean isBreakFocus() {
        return breakFocus;
    }
}
