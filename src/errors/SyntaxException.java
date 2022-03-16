package errors;

/**
 * An exception is thrown if Syntax is wrong or parsing failed
 *
 * @author urliz
 * @version 1.0
 */
public class SyntaxException extends TaskException {

    private static final long serialVersionUID = 4533547557133386227L;

    /**
     * creates an exception with message
     *
     * @param message the message
     */
    public SyntaxException(String message) {
        super(message);
    }

}
