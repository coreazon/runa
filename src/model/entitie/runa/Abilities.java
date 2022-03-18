package model.entitie.runa;

public enum Abilities {

    SLASH("Slash", AbilityType.PHYSICAL, new FocusPoints(0)),
    SWING("Swing", AbilityType.PHYSICAL, new FocusPoints(0)),
    THRUST("Thrust", AbilityType.PHYSICAL, new FocusPoints(0)),
    PIERCE("Pierce", AbilityType.PHYSICAL, new FocusPoints(0)),
    PARRY("Parry", AbilityType.PHYSICAL, new FocusPoints(0)),
    FOCUS("Focus", AbilityType.MAGICAL, new FocusPoints(0)),
    REFLECT("Reflect", AbilityType.MAGICAL, new FocusPoints(0)),
    WATER("Water", AbilityType.MAGICAL, new FocusPoints(1)),
    ICE("Ice", AbilityType.MAGICAL, new FocusPoints(1)),
    FIRE("Fire", AbilityType.MAGICAL, new FocusPoints(1)),
    LIGHTNING("Lightning", AbilityType.MAGICAL, new FocusPoints(1));

    private final String representation;
    private final AbilityType abilityType;
    private final FocusPoints fpCosts;

    Abilities(String representation, AbilityType abilityType, FocusPoints fpCosts) {
        this.representation = representation;
        this.abilityType = abilityType;
        this.fpCosts = fpCosts;
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

    public static List<Abilities> getAllAbilities() {
        return Arrays.asList(Abilities.values());
    }
}
