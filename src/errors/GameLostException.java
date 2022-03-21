package errors;

public class GameLostException extends TaskException{

    private static final long serialVersionUID = 4345634534133386227L;

    public GameLostException(String message) {
        super(message);
    }
}
