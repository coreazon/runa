import command.CommandParserExecute;
import core.Input;
import core.Output;
import errors.GameLostException;
import errors.GameQuitException;
import errors.GameWonException;
import message.Message;
import model.TaskHandler;

/**
 * The class describes a session for a command execution
 *
 * @author urliz
 * @version 1.0
 */
public class Session {

    private final TaskHandler taskHandler;
    private final Output output;
    private final Output errorOutput;
    private final Input input;
    private final CommandParserExecute commandParserExecute;
    private boolean isCodeRunning;

    /**
     * Instantiates a new session
     *
     * @param output               the output consumer
     * @param errorOutput          the input supplier
     * @param input                the error output consumer
     * @param commandParserExecute the command parser
     */
    public Session(Output output, Output errorOutput, Input input, CommandParserExecute commandParserExecute) {
        this.output = output;
        this.errorOutput = errorOutput;
        this.input = input;
        this.taskHandler = new TaskHandler(input, output, commandParserExecute);
        this.commandParserExecute = commandParserExecute;
    }


    /**
     * Method which starts the task
     */
    public void interactive() {
        isCodeRunning = true;
        try {
            taskHandler.start();
        } catch (GameQuitException e) {
            isCodeRunning = false;
        }
        while (isCodeRunning) {
            try {
                taskHandler.shuffleCards();
                taskHandler.fight();
            } catch (GameQuitException e) {
                isCodeRunning = false;
            } catch (GameLostException e) {
                output.output(Message.LOST);
                isCodeRunning = false;
            } catch (GameWonException e) {
                output.output(e.getMessage());
                isCodeRunning = false;
            }
        }
    }


}
