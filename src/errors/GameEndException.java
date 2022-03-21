package errors;

public abstract class GameEndException extends Exception {

    private static final long serialVersionUID = 7463456348863386527L;


    protected GameEndException(String message) {
        super(message);
    }

    protected GameEndException() {}

}
