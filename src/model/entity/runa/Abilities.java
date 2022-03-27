package model.entity.runa;

import model.entity.AttackType;
import model.entity.FocusPoint;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.mobs.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * enum that holds all the abilities of runa
 *
 * @author urliz
 * @version 1.0
 */
public enum Abilities implements DamageCalculatorRuna {
    /**
     * slash ability
     */
    SLASH("Slash", AbilityType.PHYSICAL, new FocusPoint(0), AttackType.ATTACK, true, AbilityType.NONE, true) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            return new HealthPoints(4 * level.getNumber() + dice.getNumber());
        }
    },
    /**
     * swing ability
     */
    SWING("Swing", AbilityType.PHYSICAL, new FocusPoint(0), AttackType.ATTACK, true, AbilityType.NONE, true) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            return new HealthPoints(5 * level.getNumber() + dice.getNumber());
        }
    },
    /**
     * thrust ability
     */
    THRUST("Thrust", AbilityType.PHYSICAL, new FocusPoint(0), AttackType.ATTACK, false, AbilityType.NONE, true) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            int damage = 6 * level.getNumber() + dice.getNumber();
            if (dice.getNumber() >= 6) damage += 4 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * pierce ability
     */
    PIERCE("Pierce", AbilityType.PHYSICAL, new FocusPoint(0), AttackType.ATTACK, false, AbilityType.NONE, true) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            int damage = 7 * level.getNumber() + dice.getNumber();
            if (dice.getNumber() >= 6) damage += 5 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * parry ability
     */
    PARRY("Parry", AbilityType.PHYSICAL, new FocusPoint(0), AttackType.DEFENSE, false, AbilityType.PHYSICAL, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            return new HealthPoints(7 * level.getNumber());
        }
    },
    /**
     * focus ability
     */
    FOCUS("Focus", AbilityType.MAGICAL, new FocusPoint(0), AttackType.NONE, false, AbilityType.NONE, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            return new HealthPoints(0);
        }
    },
    /**
     * reflect ability
     */
    REFLECT("Reflect", AbilityType.MAGICAL, new FocusPoint(0), AttackType.DEFENSE, false, AbilityType.MAGICAL, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            return new HealthPoints(10 * level.getNumber());
        }
    },
    /**
     * water ability
     */
    WATER("Water", AbilityType.MAGICAL, new FocusPoint(1), AttackType.ATTACK, false, AbilityType.NONE, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 4) * focusPoints.getFocusPoints();
            if (targetType == Type.LIGHTNING) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * ice ability
     */
    ICE("Ice", AbilityType.MAGICAL, new FocusPoint(1), AttackType.ATTACK, false, AbilityType.NONE, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 4) * focusPoints.getFocusPoints() + 2;
            if (targetType == Type.WATER) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * fire ability
     */
    FIRE("Fire", AbilityType.MAGICAL, new FocusPoint(1), AttackType.ATTACK, false, AbilityType.NONE, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 4) * focusPoints.getFocusPoints();
            if (targetType == Type.ICE) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    /**
     * lightning ability
     */
    LIGHTNING("Lightning", AbilityType.MAGICAL, new FocusPoint(1), AttackType.ATTACK, false, AbilityType.NONE, false) {
        @Override
        public HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 5) * focusPoints.getFocusPoints() + 2;
            if (targetType == Type.FIRE) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    };

    private final String representation;
    private final AbilityType abilityType;
    private final FocusPoint fpCosts;
    private final AttackType attackType;
    private final boolean breakFocus;
    private final AbilityType defenseType;
    private final boolean needRoll;

    /**
     * constructor
     *
     * @param representation the representation
     * @param abilityType    the ability type
     * @param fpCosts        the fp costs
     * @param attackType     the attackType
     * @param breakFocus     teh breakFocus
     * @param defenseType    the defenseCard
     * @param needRoll       the needRoll
     */
    Abilities(String representation, AbilityType abilityType, FocusPoint fpCosts, AttackType attackType
            , boolean breakFocus, AbilityType defenseType, boolean needRoll) {
        this.representation = representation;
        this.abilityType = abilityType;
        this.fpCosts = fpCosts;
        this.attackType = attackType;
        this.breakFocus = breakFocus;
        this.defenseType = defenseType;
        this.needRoll = needRoll;
    }

    /**
     * returns all the abilities for runa in a list
     *
     * @param runaClass the class of runa
     * @return the abilities in a list
     */
    public static List<Abilities> getAllAbilitiesForRuna(RunaClass runaClass) {

        return Arrays.stream(Abilities.values())
                .filter(ability -> !runaClass.getAbilities().contains(ability)).collect(Collectors.toList());
    }

    /**
     * returns wether the ability needs a dice roll or not
     *
     * @return true if it needs
     */
    public boolean isNeedRoll() {
        return needRoll;
    }

    /**
     * returns wether the ability breaks focus or not
     *
     * @return true if it can
     */
    public boolean isBreakFocus() {
        return breakFocus;
    }

    /**
     * returns the defense
     *
     * @return the defense
     */
    public AbilityType getDefense() {
        return defenseType;
    }

    /**
     * returns the fp costs
     *
     * @return the fp costs
     */
    public FocusPoint getFpCosts() {
        return fpCosts;
    }

    /**
     * returns the ability type
     *
     * @return the abiltiy tpye
     */
    public AbilityType getAbilityType() {
        return abilityType;
    }

    /**
     * returns the representation
     *
     * @return the representation
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * returns the attack type
     *
     * @return teh attackType
     */
    public AttackType getAttackType() {
        return attackType;
    }

}
