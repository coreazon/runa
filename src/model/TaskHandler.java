package model;

import command.CommandParserExecute;
import core.Input;
import core.Output;
import core.Pair;
import errors.CharacterClassException;
import errors.Errors;
import errors.GameLostException;
import errors.GameQuitException;
import errors.GameWonException;
import errors.SeedNotFoundException;
import message.Message;
import model.entity.AttackType;
import model.entity.FocusPoints;
import model.entity.Score;
import model.entity.mobs.BossMobs;
import model.entity.mobs.BossMonster;
import model.entity.mobs.Mobs;
import model.entity.mobs.Monster;
import model.entity.mobs.RegularMonster;
import model.entity.runa.Abilities;
import model.entity.runa.Ability;
import model.entity.runa.Runa;
import model.entity.runa.RunaClass;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

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
    private final GameLevel gameLevel;
    private final ArrayList<Ability> cardDeck;

    public TaskHandler(Input input, Output output, CommandParserExecute parser) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        this.gameLevel = new GameLevel(1, 1);
        this.runa = new Runa(initialize());
        this.monsters = new ArrayDeque<>();
        this.cardDeck = new ArrayList<>();
    }


    private RunaClass initialize() throws GameQuitException {
        output.output(Message.WELCOME_MESSAGE);
        RunaClass runaClass = null;
        output.output(String.format(Message.ENTER_NUMBER, RunaClass.values().length));
        do {
            try {
                var inputUser = input.read();
                parser.checkQuitParser(inputUser);
                runaClass = parser.parseClass(inputUser);
            } catch (CharacterClassException e){
                output.output(String.format(Message.ENTER_NUMBER, RunaClass.values().length));
            }
        }while(runaClass ==  null);
        return runaClass;
    }

    public boolean shuffleCards() throws GameQuitException {

        output.output(Message.SHUFFLE_MESSAGE);
        output.output(Message.ENTER_SEEDS);
        Pair<Integer, Integer> seeds = null;
        do {
            var userInput = input.read();
            parser.checkQuitParser(userInput);
            try {
                seeds = parser.parseSeeds(userInput);
            } catch (SeedNotFoundException e){
                output.output(Message.ENTER_SEEDS);
            }
        }while(seeds == null);

        //TODO: change both lists according to their types
        var mobsList = Mobs.getMobsFromGameLevel(gameLevel.getGameLevel());
        var abilities = Abilities.getAllAbilitiesForRuna(runa.getClassOfRuna());

        Collections.shuffle(abilities, new Random(seeds.getFirstElement()));
        Collections.shuffle(mobsList, new Random(seeds.getSecondElement()));

        var listOfAbilities = new ArrayList<Ability>();
        var listOfMonsters = new LinkedList<Monster>();

        abilities.forEach(ability -> listOfAbilities.add(new Ability(ability, new Score(gameLevel.getGameLevel()))));
        mobsList.forEach(monster -> listOfMonsters.add(new RegularMonster(monster, new FocusPoints(gameLevel.getGameLevel()))));

        this.monsters = new LinkedList<>(listOfMonsters);
        runa.setAbilities(new ArrayList<>(listOfAbilities));

        return true;
    }

    /**
     * handles the fighting section of the game.
     *
     * @return true as long as runa did not won the game
     * @throws GameQuitException if runa quits the game
     * @throws GameLostException if runa lost the game
     */
    public boolean fight() throws GameQuitException, GameLostException, GameWonException {
        var inLevel = true;
        while(inLevel) {

            output.output(String.format(Message.ENTER_STAGE, gameLevel.getGameRoom(), gameLevel.getGameLevel()));
            var monstersInRoom = getMobsForRoom();

            //as long as in the same room
            while (true) {
                output.output(getBattleInformation());


                //first runa
                turnOfRuna(monstersInRoom);
                //check if won
                if (monsterIsAlive(monstersInRoom)) break;
                //fp of runa
                focusPointsTurnRuna();
                //every mob
                turnOfMonsters(monstersInRoom);
                //check if lost
                hasLost();
                //fp of mobs
                focusPointsTurnMonster(monstersInRoom);

            }

            heal();
            if (gameLevel.getGameRoom() != 4) reward();
            inLevel = nextRoom();
        }
        //check if won the game
        if (gameLevel.getGameLevel() == 2) return false;
        //check rewards
        upgrade();
        //heal player

        nextLevel();
        return true;
    }

    private String getCardsForReward(int size) {
        var outputBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (cardDeck.isEmpty()) break;
            outputBuilder.append(String.format(Message.CARDS_LISTED, i+1, cardDeck.get(i).toString()));
            outputBuilder.append("\n");
        }
        return outputBuilder.length() > 0 ? outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString() : outputBuilder.toString();
    }

    private void chooseCard() throws GameQuitException {
        if (gameLevel.getGameRoom() == 1) {
            output.output(String.format(Message.PICK_CARDS, 1, getCardsForReward(2)));
            int card;
            do {
                output.output(String.format(Message.ENTER_NUMBER, 2));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                card = parser.parseNumber(userInput, 2);
            } while (card == 0);
            runa.addCard(cardDeck.get(card - 1));
            output.output(String.format(Message.UPGRADE, cardDeck.get(card - 1).toString()));
            cardDeck.remove(card - 1);
        }
        else {
            output.output(String.format(Message.PICK_CARDS, 2, getCardsForReward(4)));
            int[] card;
            do {
                output.output(String.format(Message.ENTER_NUMBER, Math.min(cardDeck.size(), 4)));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                card = parser.parseNumbers(userInput, Math.min(cardDeck.size(), 4));
            } while (card == null);
            var list = new ArrayList<Ability>();
            Arrays.stream(card).forEach(ability -> {
                runa.addCard(cardDeck.get(ability - 1));
                list.add(cardDeck.get(ability - 1));
            });
            list.forEach(ability ->  output.output(String.format(Message.UPGRADE, ability.toString())));
            list.forEach(cardDeck::remove);
        }
    }

    private void reward() throws GameQuitException {

        output.output(Message.REWARD);
        //check if runa can get better dice
        if (runa.getDice().getSides() == 12) {
            chooseCard();
        }
        else {
            int reward;
            do {
                output.output(String.format(Message.ENTER_NUMBER, 2));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                reward = parser.parseNumber(userInput, 2);
            } while (reward == 0);
            if (reward == 1){
                output.output(runa.newDice());
            }
            else {
                chooseCard();
            }
        }
    }

    private void upgrade() {
        output.output(runa.upgrade());
    }

    private void turnOfMonsters(List<Monster> monstersInRoom) {
        monstersInRoom.forEach(monster -> {
            var card = monster.getCards().remove(0);
            if (card.getCard().isBreakFocus() && runa.getFocusCard() != null) runa.setFocusCard(null);
            if (card.getCard().getAttackType() == AttackType.ATTACK) {
                runa.takeDamage(card.getCard().calculateDamage(card.getLevel()), card.getCard().getType(), monster);
            }
            else if (card.getCard().getAttackType() == AttackType.DEFENSE) {
                monster.setDefenseCard(card);
            }else {
                monster.setFocusCard(card);
            }
            monster.reduceFocusPoints(card);
        });
    }

    private void focusPointsTurnRuna() {
        if (runa.getFocusCard() == null) return;
        var focusPoints = runa.getFocusCard().getLevel().getNumber();
        runa.getFocusPoints().setFocusPoints(Math.min(focusPoints + runa.getFocusPoints().getFocusPoints(), runa.getDice().getSides()));
        runa.setFocusCard(null);
    }

    private void focusPointsTurnMonster(List<Monster> monstersInRoom) {
        monstersInRoom.stream().filter(monster -> monster.getFocusCard() != null)
                .collect(Collectors.toList()).forEach(monster -> {
                    monster.getFocusPoints().setFocusPoints(monster.getFocusCard().getLevel().getNumber());
                    monster.setFocusCard(null);
        });
    }

    private List<Monster> getMobsForRoom() {
        LinkedList<Monster> list = new LinkedList<>();
        if (gameLevel.getGameRoom() == 4) list.add(new BossMonster(BossMobs.getBoss(gameLevel.getGameLevel()), new FocusPoints(gameLevel.getGameLevel())));
        else if (gameLevel.getGameRoom() == 1) list.add(this.monsters.poll());
        else {
            var firstMob = this.monsters.poll();
            var secondMob = this.monsters.poll();
            list.add(firstMob);
            list.add(secondMob);
        }
        return list;
    }

    private void turnOfRuna(List<Monster> monstersInRoom) throws GameQuitException {
        //first need attack from user
        output.output(String.format(Message.RUNA_CARDS, runa.getCardsInfo()));
        int cardIndex;
        Ability card;
        do {
            output.output(String.format(Message.ENTER_NUMBER, runa.getAbilities().size()));
            var userInput = input.read();
            parser.checkQuitParser(userInput);
            cardIndex = parser.parseNumber(userInput, runa.getMaxCardsChoice());
            card = runa.getCard(cardIndex);
            if (!runa.canPlayCard(card)) cardIndex = 0;
        }while(cardIndex == 0);
        //optional need monster to attack
        if (monstersInRoom.size() != 1 && card.getAbility().getAttackType() == AttackType.ATTACK) {
            output.output(String.format(Message.PICK_TARGET, getMonstersInRoomToString(monstersInRoom)));
            int mobIndex;
            do {
                output.output(String.format(Message.ENTER_NUMBER, monstersInRoom.size()));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                mobIndex = parser.parseNumber(userInput, monstersInRoom.size());
            } while(mobIndex == 0);
            runaAttack(monstersInRoom.get(mobIndex), card);
        }
        else {
            runaAttack(monstersInRoom.get(0), card);
        }
        reduceFocusPoints(card);
    }

    private void reduceFocusPoints(Ability card) {
        runa.reduceFocusPoints(card.getAbility().getFpCosts());
    }

    private String getMonstersInRoomToString(List<Monster> monstersInRoom) {
        var outputBuilder = new StringBuilder();
        monstersInRoom.forEach(mob -> outputBuilder.append(mob.toString()).append("\n"));
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    private void runaAttack(Monster target, Ability card) {
        if (card.getAbility().isBreakFocus() && target.getFocusCard() != null) target.setFocusCard(null);
        if (card.getAbility().getAttackType() == AttackType.ATTACK) {
            target.takeDamage(card.getAbility().calculateDamage(card.getLevel(), runa.getDice(), runa.getFocusPoints(), target.getType()), card.getAbility().getAbilityType());
        }
        else if (card.getAbility().getAttackType() == AttackType.DEFENSE) {
            runa.setDefenseCard(card);
        }else {
            runa.setFocusCard(card);
        }
    }

    private void hasLost() throws GameLostException {
        if (runa.getHealthPoints().getHealthPoints() <= 0 ) throw new GameLostException(Errors.GAME_OVER);
    }

    private boolean monsterIsAlive(List<Monster> monstersInRoom) {
        return monstersInRoom.stream().anyMatch(monster -> monster.getHealthPoints().getHealthPoints() > 0);
    }


    /**
     * update the gamelevel to the next level
     * @throws
     */
    private void nextLevel() throws GameWonException {
        //check if won game
        if (gameLevel.getGameLevel() == 2) throw new GameWonException(Message.WON);
        gameLevel.setGameLevel(gameLevel.getGameLevel() + 1);
    }

    /**
     * update the gameroom to the next room or back to room 1 if it was the boss room
     */
    private boolean nextRoom() {
        if (gameLevel.getGameRoom() == 4) {
            gameLevel.setGameRoom(1);
            return false;
        }
        else {
            gameLevel.setGameRoom(gameLevel.getGameRoom() + 1);
            return true;
        }
    }

    private String getBattleInformation() {
        var outputBuilder = new StringBuilder();
        monsters.forEach(monster -> outputBuilder.append(monster.toString()).append(System.lineSeparator()));
        outputBuilder.deleteCharAt(outputBuilder.length() - 1);
        return String.format(Message.BATTLE_INFO, runa.toString(), outputBuilder.toString());
    }

    private void heal() throws GameQuitException {
        if (runa.getHealthPoints().getHealthPoints() == 50 || runa.getAbilities().size() == 1) return;
        if (runa.getAbilities().size() == 2) {
            int heal;
                output.output(String.format(Message.HEAL, runa.getHealthPoints().getHealthPoints(), runa.getMaxCardsChoice()));
            do {
                output.output(String.format(Message.ENTER_NUMBER, runa.getAbilities().size()));
                var inputUser = input.read();
                parser.checkQuitParser(inputUser);
                heal = parser.parseNumber(inputUser, runa.getAbilities().size());

            }while (heal == 0);
            runa.discardCard(new int[]{heal});
            runa.heal();
        }
        else {
            int[] heal;
                output.output(String.format(Message.HEAL, runa.getHealthPoints().getHealthPoints(), runa.getMaxCardsChoice()));
            do {
                output.output(String.format(Message.ENTER_MULTIPLE_NUMBER, runa.getAbilities().size()));
                var inputUser = input.read();
                parser.checkQuitParser(inputUser);
                heal = parser.parseNumbers(inputUser, runa.getPossibleHealSize());

            }while (heal == null);
            runa.discardCard(heal);
        }
    }

}
