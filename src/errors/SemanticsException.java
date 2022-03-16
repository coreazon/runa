package errors;

/**
 * An exception thrown if the input validates against the semantic rules
 *
 * @author urliz
 * @version 1.0
 */
public class SemanticsException extends TaskException {

    private static final long serialVersionUID = 8414862552476146227L;

    /**
     * creates an exception with message
     *
     * @param message the message
     */
    public SemanticsException(String message) {
        super(message);
    }
}
