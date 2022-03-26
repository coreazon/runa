package errors;

/**
 * Error-messages storage
 *
 * @author urliz
 * @version 1.0
 */
public final class Errors {

    /**
     * Error-Message which gets thrown when the command arguments are wrong
     */
    public static final String COMMAND_PARAM_WRONG = "the parameters are wrong";
    /**
     * Error-Message which gets thrown when the command should have been implemented but is not
     */
    public static final String COMMAND_NOT_IMPLEMENTED = "command not implemented";
    /**
     * Error-Message which gets thrown when there are command line arguments
     */
    public static final String ARGS_NOT_ALLOWED = "command line arguments are not allowed";

    private Errors() throws IllegalAccessException {
        throw new IllegalStateException("Utility-class Constructor");
    }
}
