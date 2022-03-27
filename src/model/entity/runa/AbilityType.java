package model.entity.runa;

public enum AbilityType {

    PHYSICAL("phy."),
    MAGICAL("mag."),
    NONE("");

    private final String representation;

    AbilityType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
