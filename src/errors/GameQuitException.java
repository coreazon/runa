package errors;

/**
 * An exception thrown if the user quits the game
 *
 * @author urliz
 * @version 1.0
 */
public class GameQuitException extends GameEndException {

    private static final long serialVersionUID = 7123547545634563827L;

    /**
     * creates an exception without message
     */
    public GameQuitException() {
        super();
    }
}
