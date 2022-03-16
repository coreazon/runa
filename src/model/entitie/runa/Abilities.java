package model.entitie.runa;

public enum Abilities {
    SLASH("Slash"),
    SWING("Swing"),
    THRUST("Thrust"),
    PIERCE("Pierce"),
    PARRY("Parry"),
    FOCUS("Focus"),
    REFLECT("Reflect"),
    WATER("Water"),
    ICE("Ice"),
    FIRE("Fire"),
    LIGHTNING("Lightning");

    private final String representation;

    Abilities(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
