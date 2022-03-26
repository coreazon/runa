package model.entity.runa;

import model.dice.Dice;
import model.entity.AttackType;
import model.entity.FocusPoints;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.mobs.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Abilities implements DamageCalculatorRuna {

    SLASH("Slash", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK, true, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            return new HealthPoints(4 * level.getNumber() + dice.getSides());
        }
    },
    SWING("Swing", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK, true, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            return new HealthPoints(5 * level.getNumber() + dice.getSides());
        }
    },
    THRUST("Thrust", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            int damage = 6 * level.getNumber() + dice.getSides();
            if (dice.getSides() >= 6) damage += 4 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    PIERCE("Pierce", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            int damage = 7 * level.getNumber() + dice.getSides();
            if (dice.getSides() >= 6) damage += 5 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    PARRY("Parry", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.DEFENSE, false, AbilityType.PHYSICAL) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            return new HealthPoints(7 * level.getNumber());
        }
    },
    FOCUS("Focus", AbilityType.MAGICAL, new FocusPoints(0), AttackType.NONE, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            return new HealthPoints(0);
        }
    },
    REFLECT("Reflect", AbilityType.MAGICAL, new FocusPoints(0), AttackType.DEFENSE, false, AbilityType.MAGICAL) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            return new HealthPoints(10 * level.getNumber());
        }
    },
    WATER("Water", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 4) * focusPoints.getFocusPoints();
            if (targetType == Type.LIGHTNING) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    ICE("Ice", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 4) * focusPoints.getFocusPoints() + 2;
            if (targetType == Type.WATER) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    FIRE("Fire", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 4) * focusPoints.getFocusPoints();
            if (targetType == Type.ICE) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    },
    LIGHTNING("Lightning", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK, false, AbilityType.NONE) {
        @Override
        public HealthPoints calculateDamage(Score level, Dice dice, FocusPoints focusPoints, Type targetType) {
            int damage = (2 * level.getNumber() + 5) * focusPoints.getFocusPoints() + 2;
            if (targetType == Type.FIRE) damage += 2 * level.getNumber();
            return new HealthPoints(damage);
        }
    };

    private final String representation;
    private final AbilityType abilityType;
    private final FocusPoints fpCosts;
    private final AttackType attackType;
    private final boolean breakFocus;
    private final AbilityType defenseType;

    Abilities(String representation, AbilityType abilityType, FocusPoints fpCosts, AttackType attackType, boolean breakFocus, AbilityType defenseType) {
        this.representation = representation;
        this.abilityType = abilityType;
        this.fpCosts = fpCosts;
        this.attackType = attackType;
        this.breakFocus = breakFocus;
        this.defenseType = defenseType;
    }

    public static List<Abilities> getAllAbilitiesForRuna(RunaClass runaClass) {

        return Arrays.stream(Abilities.values()).filter(ability -> !runaClass.getAbilities().contains(ability)).collect(Collectors.toList());
    }

    public boolean isBreakFocus() {
        return breakFocus;
    }

    public AbilityType getDefense() {
        return defenseType;
    }

    public FocusPoints getFpCosts() {
        return fpCosts;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public String getRepresentation() {
        return representation;
    }

    public AttackType getAttackType() {
        return attackType;
    }


}
