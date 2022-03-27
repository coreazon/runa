package model.entity.runa;

/**
 * this enum holds the ability type
 *
 * @author urliz
 * @version 1.0
 */
public enum AbilityType {
    /**
     * physical
     */
    PHYSICAL("phy."),
    /**
     * magical
     */
    MAGICAL("mag."),
    /**
     * none
     */
    NONE("");

    private final String representation;

    /**
     * constructor
     *
     * @param representation representation
     */
    AbilityType(String representation) {
        this.representation = representation;
    }

    /**
     * retuns the representation
     *
     * @return the representation
     */
    public String getRepresentation() {
        return representation;
    }
}
