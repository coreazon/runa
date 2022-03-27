package errors;

/**
 * Error-messages storage
 *
 * @author urliz
 * @version 1.0
 */
public final class Errors {


    /**
     * Error-Message which gets thrown when there are command line arguments
     */
    public static final String ARGS_NOT_ALLOWED = "command line arguments are not allowed";

    private Errors() throws IllegalAccessException {
        throw new IllegalStateException("Utility-class Constructor");
    }
}
