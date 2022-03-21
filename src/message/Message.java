package message;

import model.entitie.runa.RunaClass;

public final class Message {

    public static final String WELCOME_MESSAGE = "Welcome to Runa's Strive\nSelect Runa's character class"
            + RunaClass.getClassesListedForMessage();
    public static final String ENTER_NUMBER = "Enter number [1--%d]:";
    public static final String ENTER_MULTIPLE_NUMBER = "Enter numbers [1--%d] separated by comma:";
    public static final String ENTER_ROLL = "Enter dice roll [1--%d]:";
    public static final String ENTER_SEEDS = "Enter seeds [1--2147483647] separated by comma:";
    public static final String SHUFFLE_MESSAGE = "To shuffle ability cards and monsters, enter two seeds";
    public static final String ENTER_STAGE = "Runa enters Stage %d of Level %d";
    public static final String RUNA_TOSTRING = "Runa (%d/50 HP, %d/%d FP)";
    public static final String MONSTER_TOSTRING = "%s (%dHP, %d FP): attempts %s(%d) next";
    public static final String BATTLE_INFO = "----------------------------------------\n" 
                                                + "%s\nvs.\n%s" + "----------------------------------------";
    public static final String RUNA_CARDS = "Select card to play\n%s";
    public static final String PICK_TARGET = "Select Runa's target.\n%s";
    public static final String HEAL = "Runa (%d/50 HP) can discard ability cards for healing (or none)\n" 
                                        + "%s";

    /**
     * Utility class constructor
     */
    private Message() {
        throw new IllegalStateException("Utility-class constructor.");
    }
}
