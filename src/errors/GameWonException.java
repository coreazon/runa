package errors;

public class GameWonException extends GameEndException{

    private static final long serialVersionUID = 8678947588633386527L;

    public GameWonException(String message) {
        super(message);
    }


}
