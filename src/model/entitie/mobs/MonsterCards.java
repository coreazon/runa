package model.entitie.mobs;

public enum MonsterCards {

    SCRATCH("Scratch"),
    CLAW("Claw"),
    SMASH("Smash"),
    BITE("Bite"),
    FOCUS("Focus"),
    BLOCK("Block"),
    DEFLECT("Deflect"),
    WATER("Water"),
    ICE("Ice"),
    FIRE("Fire"),
    LIGHTNING("Lightning");

    private final String representation;

    MonsterCards(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
