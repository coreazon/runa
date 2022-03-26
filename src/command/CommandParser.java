package command;

import core.Pair;
import errors.CharacterClassException;
import errors.GameQuitException;
import errors.SeedNotFoundException;
import model.entity.runa.RunaClass;

/**
 * This interface describes a parser for commands.
 * The parser parses a string into a command name and a List of parameters.
 *
 * @author Lucas ALber
 * @author urliz
 * @version 1.0
 */
public interface CommandParser {

    RunaClass parseClass(String input) throws CharacterClassException;

    void checkQuitParser(String input) throws GameQuitException;

    Pair<Integer, Integer> parseSeeds(String input) throws SeedNotFoundException;

    int parseNumber(String input, int maxNumber) throws GameQuitException;

    int[] parseNumbers(String input, int maxNumber, int amountOfNumbers) throws GameQuitException;

    int[] parseHealNumbers(String input, int maxNumber, int amountOfMaxNumbers) throws GameQuitException;
}
