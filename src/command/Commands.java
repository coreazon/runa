package command;

import errors.Errors;
import errors.SyntaxException;
import errors.TaskException;
import model.TaskHandler;

import java.util.List;

/**
 * The enum which holds all the Commands and is used to execute them
 *
 * @author urliz
 * @version 1.0
 */
public enum Commands {
    /**
     * example command
     */
    EXAMPLE(CommandParserExecute.EXAMPLE_COMMAND, CommandParserExecute.REGEX_EXAMPLE_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, TaskHandler taskHandler) {
            String resultMessage;


            try {
                resultMessage = taskHandler.example(Object);

            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }
            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * the quit command
     */
    QUIT(CommandParserExecute.QUIT_COMMAND, CommandParserExecute.REGEX_QUIT_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, TaskHandler taskHandler) {

            return new Result(Result.ResultType.SUCCESS);
        }
    };


    private final String commandName;
    private final String regexOfCommand;

    /**
     * constructor of a command
     *
     * @param commandName    the name of the command
     * @param regexOfCommand the regex which corresponds to the Command
     */
    Commands(String commandName, String regexOfCommand) {
        this.commandName = commandName;
        this.regexOfCommand = regexOfCommand;
    }

    /**
     * gets a command through the String representation
     *
     * @param commandName the command name
     * @return the found command
     * @throws SyntaxException if there is no command corresponding to the string
     */
    public static Commands getCommand(String commandName) throws SyntaxException {
        for (Commands command : Commands.values()) {
            if (command.commandName.equals(commandName)) return command;
        }
        throw new SyntaxException(Errors.COMMAND_NOT_IMPLEMENTED);
    }

    /**
     * returns the regex of a command
     *
     * @return the regex of a command
     */
    public String getRegexOfCommand() {
        return regexOfCommand;
    }

    /**
     * executes a command with its parameters
     *
     * @param parameters     the parameters
     * @param TaskHandler th database on which the command is performed
     * @return a Result which documents if the command succeeded or not and the corresponding message
     */
    public abstract Result executeCommand(List<String> parameters, TaskHandler TaskHandler);


}
