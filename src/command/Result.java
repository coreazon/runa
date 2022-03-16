package command;

/**
 * This class describes a result of a command execute
 *
 * @author Lucas Alber
 * @author urliz
 * @version 1.0
 */
public class Result {

    private final String message;
    private final ResultType resultType;

    /**
     * creates a new Result without message
     *
     * @param resultType the type of the result
     */
    public Result(final ResultType resultType) {
        this(resultType, null);
    }

    /**
     * creates a new Result with a message
     *
     * @param resultType the type of the result
     * @param message    the message
     */
    public Result(final ResultType resultType, final String message) {
        this.resultType = resultType;
        this.message = message;
    }

    /**
     * returns the message
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * returns the result type
     *
     * @return the result type
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * The type of result of a execution
     */
    public enum ResultType {
        /**
         * the execution was a failure
         */
        FAILURE,
        /**
         * the execution was successfully
         */
        SUCCESS
    }
}
