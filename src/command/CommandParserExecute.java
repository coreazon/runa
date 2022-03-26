package command;

import core.Pair;
import errors.CharacterClassException;
import errors.GameQuitException;
import errors.SeedNotFoundException;
import model.entity.runa.RunaClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a Command Parser, which has the purpose to check the Validation of the Syntax and handles
 * the conversion of the inputted String
 *
 * @author urliz
 * @version 1.0
 */
public class CommandParserExecute implements CommandParser {


    public static final String SEED_SEPARATOR = ",";


    //REGEX here

    public static final String CHOOSE_CLASS_REGEX = "[1-3]";
    public static final String SEED = "[0-9]+";
    public static final String REGEX_SEEDS = "SEED,SEED";
    public static final String QUIT_REGEX = "quit";
    public static final String REGEX_NUMBERS = "(" + SEED + "|[" + SEED + ",]+" + SEED + ")";

    //****************

    @Override
    public RunaClass parseClass(String input) throws CharacterClassException {
        if (!input.matches(CHOOSE_CLASS_REGEX)) throw new CharacterClassException();
        return RunaClass.getClassByIndex(Integer.parseInt(input));
    }

    @Override
    public void checkQuitParser(String input) throws GameQuitException {
        if (input.matches(QUIT_REGEX)) throw new GameQuitException();
    }

    public Pair<Integer, Integer> parseSeeds(String input) throws SeedNotFoundException {
        if (!input.matches(REGEX_NUMBERS)) throw new SeedNotFoundException();
        if (!input.contains(SEED_SEPARATOR)) throw new SeedNotFoundException();
        var seeds = input.split(SEED_SEPARATOR);
        return new Pair<>(Integer.parseInt(seeds[0]), Integer.parseInt(seeds[1]));
    }

    @Override
    public int parseNumber(String input, int maxNumber) throws GameQuitException {
        checkQuitParser(input);
        if (!input.matches(SEED)) return 0;
        var number = Integer.parseInt(input);
        if (number <= 0 || number > maxNumber) return 0;
        return number;
    }

    @Override
    public int[] parseNumbers(String input, int maxNumber, int amountOfNumbers) throws GameQuitException {
        checkQuitParser(input);
        if (!input.matches(REGEX_NUMBERS)) return new int[0];
        int[] numbers = Arrays.stream(input.split(SEED_SEPARATOR)).mapToInt(Integer::parseInt).toArray();
        if (numbers.length != amountOfNumbers || duplicates(numbers) || notInRange(numbers, maxNumber)) return null;
        return numbers;
    }

    @Override
    public int[] parseHealNumbers(String input, int maxNumber, int amountOfMaxNumbers) throws GameQuitException {
        checkQuitParser(input);
        if (input.isEmpty()) return new int[0];
        if (!input.matches(REGEX_NUMBERS)) return null;
        int[] numbers = Arrays.stream(input.split(SEED_SEPARATOR)).mapToInt(Integer::parseInt).toArray();
        if (numbers.length > amountOfMaxNumbers || duplicates(numbers) || notInRange(numbers, maxNumber)) return null;
        return numbers;
    }

    private boolean notInRange(final int[] numbers, int maxNumber) {
        return Arrays.stream(numbers).anyMatch(number -> number < 1 || number > maxNumber);
    }

    private boolean duplicates(final int[] numbers) {
        Set<Integer> lump = new HashSet<>();
        for (int i : numbers) {
            if (lump.contains(i)) return true;
            lump.add(i);
        }
        return false;
    }
}
