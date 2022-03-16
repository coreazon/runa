package command;

import core.Pair;
import errors.CharacterClassException;
import errors.SyntaxException;
import model.entitie.runa.RunaClass;

import java.util.List;

/**
 * This interface describes a parser for commands.
 * The parser parses a string into a command name and a List of parameters.
 *
 * @author Lucas ALber
 * @author urliz
 * @version 1.0
 */
public interface CommandParser {
    /**
     * Parses the given string into a {@link Pair} of command name and a List of parameters.
     *
     * @param input the input string
     * @return a {@link Pair} of command name and a List of parameters as string representation.
     * Returns the parsed String
     * @throws SyntaxException if the String could not be parsed
     */
    Pair<String, List<String>> parseCommand(String input) throws SyntaxException;

    RunaClass parseClass(String input) throws CharacterClassException;

    boolean checkQuitParser(String input);
}
