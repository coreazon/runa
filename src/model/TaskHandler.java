package model;

import command.CommandParserExecute;
import core.Input;
import core.Output;
import core.Pair;
import errors.CharacterClassException;
import errors.SeedNotFoundException;
import message.Message;
import model.entitie.AttackType;
import model.entitie.mobs.BossMobs;
import model.entitie.mobs.BossMonster;
import model.entitie.mobs.Mobs;
import model.entitie.mobs.Monster;
import model.entitie.runa.Abilities;
import model.entitie.runa.Runa;
import model.entitie.runa.RunaClass;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
    private Queue<Monster> monsters;
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

        //TODO: change that into a monster list or RegularMonster list
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
        while(inLevel) {

            output.output(String.format(Message.ENTER_STAGE, gameLevel.getGameRoom(), gameLevel.getGameLevel()));
            var monstersInRoom = getMobsForRoom();

            var inRoom = true;
            while (inRoom) {
                output.output(getBattleInformation());


                //first runa
                inLevel = runaTurn(monstersInRoom);
                //check if won

                //fp of runa

                //every mob

                //check if lost

                //fp of mobs
            }



            nextRoom();
        }

        nextLevel();
        return true;
    }

    private List<Monster> getMobsForRoom() {
        LinkedList<Monster> list = new LinkedList<>();
        if (gameLevel.getGameRoom() == 4) list.add(new BossMonster(BossMobs.getBoss(gameLevel.getGameLevel())));
        else if (gameLevel.getGameRoom() == 1) list.add(this.monsters.poll());
        else {
            var firstMob = this.monsters.poll();
            var secondMob = this.monsters.poll();
            list.add(firstMob);
            list.add(secondMob);
        }
        return list;
    }

    private boolean runaTurn(List<Monster> monstersInRoom) {
        //first need attack from user
        output.output(String.format(Message.RUNA_CARDS, runa.getCardsInfo()));
        int cardIndex;
        do {
            output.output(String.format(Message.ENTER_NUMBER, runa.getAbilities().size()));
            var userInput = input.read();
            if (parser.checkQuitParser(userInput)) return false;
            cardIndex = parser.parseNumber(userInput, runa.getMaxCardsChoice());
        }while(cardIndex == 0);
        var card = runa.getCard(cardIndex);
        //optional need monster to attack
        if (monstersInRoom.size() == 1 && card.getAttackType() == AttackType.ATTACK) {
            runaAttack(monstersInRoom.get(0));
        }
        else {
            output.output(String.format(Message.PICK_TARGET, getMonstersInRoomToString(monstersInRoom)));
            int mobIndex;
            do {
                output.output(String.format(Message.ENTER_NUMBER, monstersInRoom.size()));
                var userInput = input.read();
                if (parser.checkQuitParser(userInput)) return false;
                mobIndex = parser.parseNumber(userInput, monstersInRoom.size());
            } while(mobIndex == 0);
            runaAttack(monstersInRoom.get(mobIndex));
        }
        return true;
    }

    private String getMonstersInRoomToString(List<Monster> monstersInRoom) {
        var outputBuilder = new StringBuilder();
        monstersInRoom.forEach(mob -> outputBuilder.append(mob.toString()).append("\n"));
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    private void runaAttack(Monster target) {
        
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
        if (runa.getHealthPoints().getHealthPoints() <= 0) throw new ;
    }

    private void won() {

    }
    /**
     * update the gamelevel to the next level
     * @throws
     */
    private void nextLevel() {
        //check if won game
        if (gameLevel.getGameLevel() == 2) throw new ;
        gameLevel.setGameLevel(gameLevel.getGameLevel() + 1);
    }

    /**
     * update the gameroom to the next room or back to room 1 if it was the boss room
     */
    private void nextRoom() {
        if (gameLevel.getGameRoom() == 4) gameLevel.setGameRoom(1);
        else gameLevel.setGameRoom(gameLevel.getGameRoom() + 1);
    }

    private String getBattleInformation() {
        var outputBuilder = new StringBuilder();
        monsters.forEach(monster -> outputBuilder.append(monster.toString()).append(System.lineSeparator()));
        outputBuilder.deleteCharAt(outputBuilder.length() - 1);
        return String.format(Message.BATTLE_INFO, runa.toString(), outputBuilder.toString());
    }

}
