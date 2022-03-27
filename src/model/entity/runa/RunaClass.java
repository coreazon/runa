package model.entity.runa;

import java.util.Arrays;
import java.util.List;

/**
 * This enum holds runa classes
 *
 * @author urliz
 * @version 1.0
 */
public enum RunaClass {
    /**
     * Warrior
     */
    WARRIOR("Warrior", Arrays.asList(Abilities.THRUST, Abilities.PARRY)),
    /**
     * Mage
     */
    MAGE("Mage", Arrays.asList(Abilities.FOCUS, Abilities.WATER)),
    /**
     * Paladin
     */
    PALADIN("Paladin", Arrays.asList(Abilities.SLASH, Abilities.REFLECT));

    private static final String MESSAGE_FORMATTER = "%d) %s%n";
    private final List<Abilities> abilities;
    private final String representation;

    /**
     * Constructor
     *
     * @param representation representation
     * @param abilities      abilities
     */
    RunaClass(String representation, List<Abilities> abilities) {
        this.abilities = abilities;
        this.representation = representation;
    }

    /**
     * returns the classes listed for the welcome message
     *
     * @return the string result
     */
    public static String getClassesListedForMessage() {
        var output = new StringBuilder();
        var counter = 1;
        for (RunaClass classes : RunaClass.values()) {
            output.append(String.format(MESSAGE_FORMATTER, counter, classes.getRepresentation()));
            counter++;
        }
        return output.deleteCharAt(output.length() - 1).toString();
    }

    /**
     * returns the class by its index
     *
     * @param index the index
     * @return the class
     */
    public static RunaClass getClassByIndex(int index) {
        return RunaClass.values()[index - 1];
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
     * returns the abilities
     *
     * @return the abilities
     */
    public List<Abilities> getAbilities() {
        return abilities;
    }
}
