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
    /**
     * parses the input according to the requirements
     *
     * @param input user input
     * @return the class corresponding to the input
     * @throws CharacterClassException if the user entered a false input
     */
    RunaClass parseClass(String input) throws CharacterClassException;

    /**
     * parses the input according to the requirements
     *
     * @param input user input
     * @throws GameQuitException if the user quits the game
     */
    void checkQuitParser(String input) throws GameQuitException;

    /**
     * parses the input according to the requirements
     *
     * @param input user input
     * @return a pair holding both seeds
     * @throws SeedNotFoundException if the inputted seed was not valid
     */
    Pair<Integer, Integer> parseSeeds(String input) throws SeedNotFoundException;

    /**
     * parses the input according to the requirements
     *
     * @param input     user input
     * @param maxNumber the highest value of a number
     * @return the number parsed
     * @throws GameQuitException if the user quits the game
     */
    int parseNumber(String input, int maxNumber) throws GameQuitException;

    /**
     * parses the input according to the requirements
     *
     * @param input           user input
     * @param maxNumber       the highest value of a number
     * @param amountOfNumbers the amount of numbers needed
     * @return array of parsed numbers
     * @throws GameQuitException if the user quits the game
     */
    int[] parseNumbers(String input, int maxNumber, int amountOfNumbers) throws GameQuitException;

    /**
     * parses the input according to the requirements
     *
     * @param input              user input
     * @param maxNumber          the highest value of a number
     * @param amountOfMaxNumbers the maximum amount of numbers
     * @return array of parsed numbers
     * @throws GameQuitException if the user quits the game
     */
    int[] parseHealNumbers(String input, int maxNumber, int amountOfMaxNumbers) throws GameQuitException;
}
