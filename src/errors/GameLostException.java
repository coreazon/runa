package errors;

/**
 * An exception thrown if the user lost the game
 *
 * @author urliz
 * @version 1.0
 */
public class GameLostException extends GameEndException {

    private static final long serialVersionUID = 4345634534133386227L;

    /**
     * creates an exception with message
     *
     * @param message the message
     */
    public GameLostException(String message) {
        super(message);
    }
}
