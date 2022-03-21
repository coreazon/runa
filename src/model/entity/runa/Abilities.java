package model.entity.runa;

import model.entity.AttackType;
import model.entity.FocusPoints;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Abilities {

    SLASH("Slash", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK),
    SWING("Swing", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK),
    THRUST("Thrust", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK),
    PIERCE("Pierce", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.ATTACK),
    PARRY("Parry", AbilityType.PHYSICAL, new FocusPoints(0), AttackType.DEFENSE),
    FOCUS("Focus", AbilityType.MAGICAL, new FocusPoints(0), AttackType.NONE),
    REFLECT("Reflect", AbilityType.MAGICAL, new FocusPoints(0), AttackType.DEFENSE),
    WATER("Water", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK),
    ICE("Ice", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK),
    FIRE("Fire", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK),
    LIGHTNING("Lightning", AbilityType.MAGICAL, new FocusPoints(1), AttackType.ATTACK);

    private final String representation;
    private final AbilityType abilityType;
    private final FocusPoints fpCosts;
    private final AttackType attackType;

    Abilities(String representation, AbilityType abilityType, FocusPoints fpCosts, AttackType attackType) {
        this.representation = representation;
        this.abilityType = abilityType;
        this.fpCosts = fpCosts;
        this.attackType = attackType;
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

    public static List<Abilities> getAllAbilitiesForRuna(RunaClass runaClass) {

        return Arrays.stream(Abilities.values()).filter(ability -> !runaClass.getAbilities().contains(ability)).collect(Collectors.toList());
    }
}
