package errors;

/**
 * An exception thrown when the game comes to an end
 *
 * @author urliz
 * @version 1.0
 */
public abstract class GameEndException extends Exception {

    private static final long serialVersionUID = 7463456348863386527L;

    /**
     * creates an exception with message
     * @param message the message
     */
    protected GameEndException(String message) {
        super(message);
    }

    /**
     * default constructor
     */
    protected GameEndException() { }

}
