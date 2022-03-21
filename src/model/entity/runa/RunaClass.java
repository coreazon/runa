package model.entity.runa;

import java.util.Arrays;
import java.util.List;

public enum RunaClass {

    WARRIOR("Warrior", Arrays.asList(Abilities.THRUST, Abilities.PARRY)),
    MAGE("Mage", Arrays.asList(Abilities.FOCUS, Abilities.WATER)),
    PALADIN("Paladin", Arrays.asList(Abilities.SLASH, Abilities.REFLECT));

    private final List<Abilities> abilities;
    private final String representation;
    private static final String MESSAGE_FORMATTER = "%d) %s%n";

    RunaClass(String representation, List<Abilities> abilities) {
        this.abilities = abilities;
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public static String getClassesListedForMessage() {
        var output = new StringBuilder();
        var counter = 1;
        for (RunaClass classes : RunaClass.values()) {
            output.append(String.format(MESSAGE_FORMATTER, counter, classes.getRepresentation()));
            counter++;
        }
        return output.deleteCharAt(output.length() - 1).toString();
    }

    public static RunaClass getClassByIndex(int index) {
        return RunaClass.values()[index - 1];
    }
}
