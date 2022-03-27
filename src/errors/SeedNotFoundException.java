package errors;

/**
 * An exception thrown if the input validates again the requirements
 *
 * @author urliz
 * @version 1.0
 */
public class SeedNotFoundException extends TaskException {

    private static final long serialVersionUID = 4634547534133386227L;

    /**
     * creates an exception without message
     */
    public SeedNotFoundException() {
        super();
    }
}
