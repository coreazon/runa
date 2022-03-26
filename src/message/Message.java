package message;

import model.entity.runa.RunaClass;

import java.util.Locale;

public final class Message {

    public static final String WELCOME_MESSAGE = "Welcome to Runa's Strive\nSelect Runa's character class\n"
            + RunaClass.getClassesListedForMessage();
    public static final String ENTER_NUMBER = "Enter number [1--%d]:";
    public static final String ENTER_MULTIPLE_NUMBER = "Enter numbers [1--%d] separated by comma:";
    public static final String ENTER_ROLL = "Enter dice roll [1--%d]:";
    public static final String ENTER_SEEDS = "Enter seeds [1--2147483647] separated by comma:";
    public static final String SHUFFLE_MESSAGE = "To shuffle ability cards and monsters, enter two seeds";
    public static final String ENTER_STAGE = "Runa enters Stage %d of Level %d";
    public static final String RUNA_TOSTRING = "Runa (%d/50 HP, %d/%d FP)";
    public static final String BATTLE_INFO = "----------------------------------------\n"
                                                + "%s\nvs.\n%s" + "----------------------------------------";
    public static final String RUNA_CARDS = "Select card to play\n%s";
    public static final String PICK_TARGET = "Select Runa's target.\n%s";
    public static final String HEAL = "Runa (%d/50 HP) can discard ability cards for healing (or none)\n" 
                                        + "%s";
    public static final String UPGRADE = "Runa gets %s";
    public static final String REWARD = "Choose Runa's reward\n" +
            "1) new ability cards\n" +
            "2) next player dice";
    public static final String DICE_UPGRADE = "Runa upgrades her die to a d%d.";
    public static final String PICK_CARDS = "Pick %d card(s) as loot\n%s";
    public static final String CARDS_LISTED = "%d) %s";
    public static final String WON = "Runa won!";
    public static final String LOST = "Runa dies";
    public static final String ATTACK_USE = "Runa uses %s";
    public static final String MOB_TOOK_DAMAGE = "%s takes %d %s damage";
    public static final String MOB_ATTACK = "%s uses %s";
    public static final String MOB_DIED = "%s dies";
    public static final String RUNA_TOOK_DAMAGE = "Runa takes %d %s damage";
    public static final String HEALED = "Runa gains %d health";
    public static final String MOB_FOCUS = "%s gains %d focus";
    public static final String RUNA_FOCUS = "Runa gains %d focus";


    /**
     * Utility class constructor
     */
    private Message() {
        throw new IllegalStateException("Utility-class constructor.");
    }
}
