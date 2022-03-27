package model.entity.mobs;

import model.entity.AttackType;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.runa.AbilityType;

/**
 * THis enum hold all the abilities of the monsters
 *
 * @author urliz
 * @version 1.0
 */
public enum MonsterCards implements DamageCalculatorMob {
    /**
     * scratch ability
     */
    SCRATCH("Scratch", AttackType.ATTACK, AbilityType.NONE, true, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 5 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * claw ability
     */
    CLAW("Claw", AttackType.ATTACK, AbilityType.NONE, true, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 6 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * Smash ability
     */
    SMASH("Smash", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 8 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * bite ability
     */
    BITE("Bite", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 10 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * focus ability
     */
    FOCUS("Focus", AttackType.NONE, AbilityType.NONE, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            return new HealthPoints(0);
        }
    },
    /**
     * block ability
     */
    BLOCK("Block", AttackType.DEFENSE, AbilityType.PHYSICAL, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            return new HealthPoints(7 * level.getNumber());
        }
    },
    /**
     * deflect ability
     */
    DEFLECT("Deflect", AttackType.DEFENSE, AbilityType.MAGICAL, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            return new HealthPoints(11 * level.getNumber() + 2);
        }
    },
    /**
     * water ability
     */
    WATER("Water", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 8 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    /**
     * ice ability
     */
    ICE("Ice", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 10 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    /**
     * fire ability
     */
    FIRE("Fire", AttackType.ATTACK, AbilityType.NONE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level) {
            int damage = 12 * level.getNumber() + 2;
            return new HealthPoints(damage);
        }
    },
    /**
     * Lightning ability
     */
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

    /**
     * constructor of the monster cards
     *
     * @param representation representation
     * @param attackType     attackType
     * @param defenseType    defenseType
     * @param breakFocus     breakFocus
     * @param abilityType    abilityType
     */
    MonsterCards(String representation, AttackType attackType
            , AbilityType defenseType, boolean breakFocus, AbilityType abilityType) {
        this.representation = representation;
        this.attackType = attackType;
        this.defenseType = defenseType;
        this.breakFocus = breakFocus;
        this.abilityType = abilityType;
    }

    /**
     * returns the type of the ability
     *
     * @return the type
     */
    public AbilityType getType() {
        return abilityType;
    }

    /**
     * returns the attack type of the abiltiy
     *
     * @return the attack type
     */
    public AttackType getAttackType() {
        return attackType;
    }

    /**
     * returns the representation of the ability
     *
     * @return the representation
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * returns the defense of the card
     *
     * @return the defense
     */
    public AbilityType getDefense() {
        return this.defenseType;
    }

    /**
     * retursn wether the card can break focus or not
     *
     * @return true if it can
     */
    public boolean isBreakFocus() {
        return breakFocus;
    }
}
