package errors;

/**
 * Parent Class of the Exceptions which are thrown
 *
 * @author urliz
 * @version 1.0
 */
public abstract class TaskException extends Exception {

    private static final long serialVersionUID = 7123547588633386527L;

    /**
     * creates an exception with message
     *
     * @param message the message
     */
    protected TaskException(String message) {
        super(message);
    }

    protected TaskException() {}
}
