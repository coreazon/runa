package command;

import core.Pair;
import errors.CharacterClassException;
import errors.Errors;
import errors.SeedNotFoundException;
import errors.SyntaxException;
import model.entitie.runa.RunaClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a Command Parser, which has the purpose to check the Validation of the Syntax and handles
 * the conversion of the inputted String
 *
 * @author urliz
 * @version 1.0
 */
public class CommandParserExecute implements CommandParser {


    //COMMANDS here
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final char SPACE_CHAR = ' ';
    public static final String SEED_SEPARATOR = ",";


    //REGEX here

    public static final String CHOOSE_CLASS_REGEX = "[1-3]";
    public static final String SEED = "[0-9]+";
    public static final String REGEX_SEEDS = "SEED,SEED";
    public static final String QUIT_REGEX = "quit";
    public static final String REGEX_NUMBER = "";

    /**
     * check the input with the regex and throws Exception if it does not match
     * or returns the input as a Pair
     *
     * @param inputUser the whole input
     * @return the input as a Pair
     * @throws SyntaxException if the regex does not match
     */
    @Override
    public Pair<String, List<String>> parseCommand(String inputUser) throws SyntaxException {
        checkBasicRegex(inputUser);
        String commandValue = inputUser.split(SPACE)[0];

        return new Pair<>(checkCommand(commandValue, inputUser), createParameters(inputUser, commandValue));

    }

    private List<String> createParameters(String inputUser, String commandValue) {
        String modifiedInput = inputUser.substring(commandValue.length());
        if (modifiedInput.equals(EMPTY_STRING)) {
            return new LinkedList<>();
        }

        String[] outputAsStringArray = modifiedInput.substring(1).split(SPACE);
        return new LinkedList<>(Arrays.asList(outputAsStringArray));
    }

    private void checkBasicRegex(String inputUser) throws SyntaxException {
        if (inputUser.isEmpty()) throw new SyntaxException(Errors.COMMAND_PARAM_WRONG);
        if (inputUser.charAt(0) == SPACE_CHAR) {
            throw new SyntaxException(Errors.COMMAND_PARAM_WRONG);
        }
    }

    private String checkCommand(String command, String userInput) throws SyntaxException {


        if (userInput.matches(Commands.getCommand(command).getRegexOfCommand())) {
            return command;
        }
        throw new SyntaxException(Errors.COMMAND_PARAM_WRONG);
    }

    @Override
    public RunaClass parseClass(String input) throws CharacterClassException {
        if (!input.matches(CHOOSE_CLASS_REGEX)) throw new CharacterClassException();
        return RunaClass.getClassByIndex(Integer.parseInt(input));
    }

    @Override
    public boolean checkQuitParser(String input) {
        return input.matches(QUIT_REGEX);
    }

    public Pair<Integer, Integer> parseSeeds(String input) throws SeedNotFoundException {
        if (!input.matches(REGEX_SEEDS)) throw new SeedNotFoundException();
        var seeds = input.split(SEED_SEPARATOR);
        return new Pair<>(Integer.parseInt(seeds[0]), Integer.parseInt(seeds[1]));
    }

    @Override
    public int parseNumber(String input, int maxNumber) {
        if (checkQuitParser(input)) throw new ;
        if (!input.matches(SEED)) return 0;
        var number = Integer.parseInt(input);
        if (number <= 0 || number > maxNumber) return 0;
        return number;
    }
}
