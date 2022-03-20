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
    private Queue<Mobs> monsters;
    private GameLevel gameLevel;

    public TaskHandler(Input input, Output output, CommandParserExecute parser) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        this.gameLevel = new GameLevel(1, 1);
        this.runa = new Runa(initialize());
        this.monsters = new ArrayDeque<>();
    }


    private RunaClass initialize() {
        output.output(Message.WELCOME_MESSAGE);
        RunaClass runaClass = null;
        output.output(String.format(Message.ENTER_NUMBER, RunaClass.values().length));
        do {
            try {
                runaClass = parser.parseClass(input.read());
            } catch (CharacterClassException e){
                output.output(String.format(Message.ENTER_NUMBER, RunaClass.values().length));
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
        var abilities = Abilities.getAllAbilitiesForRuna(runa.getClassOfRuna());

        Collections.shuffle(abilities, new Random(seeds.getFirstElement()));
        Collections.shuffle(mobsList, new Random(seeds.getSecondElement()));

        this.monsters = new LinkedList<>(mobsList);
        runa.setAbilities(new ArrayList<>(abilities));

        return true;
    }

    public boolean fight() {
        var inLevel = true;
        var newStage = true;
        while(inLevel) {
            if (newStage){
                output.output(String.format(Message.ENTER_STAGE, gameLevel.getRoom(), gameLevel.getLevel()));
                newStage = false;
            }
            output.output(getBattleInfortmation());

            //first runa
            inLevel = runaTurn();
                //check if won

            //fp of runa

            //every mob

                //check if lost

            //fp of mobs

        }
        return true;
    }

    private boolean runaTurn() {
        //first need attack from user
        output.output(String.format(Message.RUNA_CARDS, runa.getCardsInfo()));
        output.output(String.format(Message.ENTER_NUMBER, runa.getAbilities().size()));
        int card = null;
        do {
            var userInput = input.read();
            if (parser.checkQuitParser(userInput)) return false;
            try {
                card = parser.parseNumber(userInput);
            } catch ( e){
                output.output(String.format(Message.ENTER_NUMBER, runa.getAbilities().size()));
            }
        }while(card == null);

        //optional need monster to attack
        
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
        if (runa.getHealthpoints().getHealthpoints() <= 0) throw new ;
    }

    private void won() {

    }
    /**
     * update the gamelevel to the next level
     * @throws 
     */
    private void nextLevel() {
        //check if won game
        if (gameLevel.getLevel() == 2) throw new ;
        gameLevel.setGameLevel(gameLevel.getLevel() + 1);
    }

    /**
     * update the gameroom to the next room or back to room 1 if it was the boss room
     */
    private void nextRoom() {
        if (gameLevel.getGameRoom() == 4) gameLevel.setGameRoom(1);
        else gameLevel.setGameRoom(gameLevel.getGameRoom() + 1);
    }

    private String getBattleInfortmation() [
        var outputBuilder = new StringBuilder();
        monsters.forEach(monster -> outputBuilder.append(monster.toString()).append(System.lineSeparator()));
        outputBuilder.deleteCharAt(outputBuilder.length() - 1);
        return String.format(Message.BATTLE_INFO, runa.toString(), outputBuilder.toString());
    ]

}
