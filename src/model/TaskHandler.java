package model;

import command.CommandParserExecute;
import core.Input;
import core.Output;
import core.Pair;
import errors.CharacterClassException;
import errors.SeedNotFoundException;
import message.Message;
import model.entitie.mobs.Mobs;
import model.entitie.runa.Abilities;
import model.entitie.runa.Runa;
import model.entitie.runa.RunaClass;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * This class models a task handler for the given task.
 * It is used to execute and handle every step of the task.
 *
 * @author urliz
 * @version 1.0
 */
public class TaskHandler {

    private final Input input;
    private final Output output;
    private final Runa runa;
    private final CommandParserExecute parser;
    private Queue<Mobs> monster;
    private GameLevel gameLevel;

    public TaskHandler(Input input, Output output, CommandParserExecute parser) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        this.gameLevel = new GameLevel(1, 1);
        this.runa = new Runa(initialize());
        this.monster = new ArrayDeque<>();
    }


    private RunaClass initialize() {
        output.output(Message.WELCOME_MESSAGE);
        RunaClass runaClass = null;
        output.output(Message.ENTER_NUMBER);
        do {
            try {
                runaClass = parser.parseClass(input.read());
            } catch (CharacterClassException e){
                output.output(Message.ENTER_NUMBER);
            }
        }while(runaClass ==  null);
        return runaClass;
    }

    public boolean shuffleCards() {

        output.output(Message.SHUFFLE_MESSAGE);
        output.output(Message.ENTER_SEEDS);
        Pair<Integer, Integer> seeds = null;
        do {
            var userInput = input.read();
            if (parser.checkQuitParser(userInput)) return false;
            try {
                seeds = parser.parseSeeds(userInput);
            } catch (SeedNotFoundException e){
                output.output(Message.ENTER_SEEDS);
            }
        }while(seeds == null);
        
        var mobsList = Mobs.getMobsFromGameLevel(gameLevel.getGameLevel());
        var abilities = Abilities.getAllAbilities();

        Collections.shuffle(abilities, new Random(seeds.getFirstElement()));
        Collections.shuffle(mobsList, new Random(seeds.getSecondElement()));

        this.monster = new LinkedList<>(mobsList);
        runa.setAbilities(new ArrayList<>(abilities));

        return true;
    }

    public boolean fight() {
        //first runa

        //fp of mobs

        //every mob

        //fp of runa

        return true;
    }

    private void newCards() {

    }

    private void newDice() {

    }

    private void upgrade() {

    }

    private void healing() {

    }

    private void lost() {

    }

    private void won() {

    }

    private void nextLevel() {

    }

    private void nextRoom() {

    }

}
