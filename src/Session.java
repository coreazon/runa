import command.CommandParserExecute;
import command.Commands;
import command.Result;
import core.Input;
import core.Output;
import core.Pair;
import errors.Errors;
import errors.GameLostException;
import errors.GameQuitException;
import errors.GameWonException;
import errors.SyntaxException;
import message.Message;
import model.TaskHandler;

import java.util.List;

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
     * @param output      the output consumer
     * @param errorOutput the input supplier
     * @param input       the error output consumer
     * @param commandParserExecute the command parser
     */
    public Session(Output output, Output errorOutput, Input input, CommandParserExecute commandParserExecute) {
        this.output = output;
        this.errorOutput = errorOutput;
        this.input = input;
        this.taskHandler = new TaskHandler(input, output, commandParserExecute);
        this.commandParserExecute = commandParserExecute;
    }


    /*
        initialize - choose class
        loop:
            shuffle
            loop:
                fight
                reward and heal
                next room
            next level
     */

    /**
     * Method which starts the task
     */
    public void interactive() {
        isCodeRunning = true;
        try {
            taskHandler.start();
        }catch (GameQuitException e) {
            isCodeRunning = false;
        }
        while (isCodeRunning) {
            try {
                taskHandler.shuffleCards();
                taskHandler.fight();
            } catch (GameQuitException e) {
                break;
            } catch (GameLostException e) {
                output.output(Message.LOST);
                break;
            } catch (GameWonException e) {
                output.output(e.getMessage());
                break;
            }
        }
    }









}
