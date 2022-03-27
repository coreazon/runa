package message;

import model.entity.runa.RunaClass;

/**
 * Message storage
 *
 * @author urliz
 * @version 1.0
 */
public final class Message {
    /**
     * welcome message
     */
    public static final String WELCOME_MESSAGE = "Welcome to Runa's Strive\nSelect Runa's character class\n"
            + RunaClass.getClassesListedForMessage();
    /**
     * message to enter a number
     */
    public static final String ENTER_NUMBER = "Enter number [1--%d]:";
    /**
     * message to enter multiple numbers
     */
    public static final String ENTER_MULTIPLE_NUMBER = "Enter numbers [1--%d] separated by comma:";
    /**
     * message to enter a roll
     */
    public static final String ENTER_ROLL = "Enter dice roll [1--%d]:";
    /**
     * message to enter seeds
     */
    public static final String ENTER_SEEDS = "Enter seeds [1--2147483647] separated by comma:";
    /**
     * message to enter seeds
     */
    public static final String SHUFFLE_MESSAGE = "To shuffle ability cards and monsters, enter two seeds";
    /**
     * message when entering stage
     */
    public static final String ENTER_STAGE = "Runa enters Stage %d of Level %d";
    /**
     * toString of runa
     */
    public static final String RUNA_TOSTRING = "Runa (%d/50 HP, %d/%d FP)";
    /**
     * battle information message
     */
    public static final String BATTLE_INFO = "----------------------------------------\n"
            + "%s\nvs.\n%s" + "----------------------------------------";
    /**
     * card selection message
     */
    public static final String RUNA_CARDS = "Select card to play\n%s";
    /**
     * target selection message
     */
    public static final String PICK_TARGET = "Select Runa's target.\n%s";
    /**
     * message to discard cards for heal
     */
    public static final String HEAL = "Runa (%d/50 HP) can discard ability cards for healing (or none)\n"
            + "%s";
    /**
     * upgrade message
     */
    public static final String UPGRADE = "Runa gets %s";
    /**
     * reward message
     */
    public static final String REWARD = "Choose Runa's reward\n" +
            "1) new ability cards\n" +
            "2) next player dice";
    /**
     * dice upgrade message
     */
    public static final String DICE_UPGRADE = "Runa upgrades her die to a d%d";
    /**
     * pick card message
     */
    public static final String PICK_CARDS = "Pick %d card(s) as loot\n%s";
    /**
     * format to list cards
     */
    public static final String CARDS_LISTED = "%d) %s";
    /**
     * runa won message
     */
    public static final String WON = "Runa won!";
    /**
     * runa lost message
     */
    public static final String LOST = "Runa dies";
    /**
     * runa attacks message
     */
    public static final String ATTACK_USE = "Runa uses %s";
    /**
     * monster took damage message
     */
    public static final String MOB_TOOK_DAMAGE = "%s takes %d %s damage";
    /**
     * monster attacks message
     */
    public static final String MOB_ATTACK = "%s uses %s";
    /**
     * monster died message
     */
    public static final String MOB_DIED = "%s dies";
    /**
     * runa took damage message
     */
    public static final String RUNA_TOOK_DAMAGE = "Runa takes %d %s damage";
    /**
     * runa heal message
     */
    public static final String HEALED = "Runa gains %d health";
    /**
     * monster gains focus message
     */
    public static final String MOB_FOCUS = "%s gains %d focus";
    /**
     * runa gains focus message
     */
    public static final String RUNA_FOCUS = "Runa gains %d focus";


    /**
     * Utility class constructor
     */
    private Message() {
        throw new IllegalStateException("Utility-class constructor.");
    }
}
