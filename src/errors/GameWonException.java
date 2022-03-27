package errors;

/**
 * An exception thrown if the user won the game
 *
 * @author urliz
 * @version 1.0
 */
public class GameWonException extends GameEndException {

    private static final long serialVersionUID = 8678947588633386527L;

    /**
     * creates an exception with message
     *
     * @param message the message
     */
    public GameWonException(String message) {
        super(message);
    }


}
