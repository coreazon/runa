package model.entitie.mobs;

public enum BossMobs {

    SPIDER_KING("Spider King"),
    MEGA_SAURUS("Mega Saurus");

    private final String representation;

    BossMobs(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
