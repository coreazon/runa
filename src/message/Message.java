package message;

import model.entitie.runa.RunaClass;

public final class Message {

    public static final String WELCOME_MESSAGE = "Welcome to Runa's Strive\nSelect Runa's character class"
            + RunaClass.getClassesListedForMessage();
    public static final String ENTER_NUMBER = "Enter number [1--3]:";
    public static final String ENTER_MULTIPLE_NUMBER = "Enter numbers [1--<n>] separated by comma:";
    public static final String ENTER_ROLL = "Enter dice roll [1--<d>]:";
    public static final String ENTER_SEEDS = "Enter seeds [1--2147483647] separated by comma:";
    public static final String SHUFFLE_MESSAGE = "To shuffle ability cards and monsters, enter two seeds";

}
