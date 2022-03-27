package model;

import command.CommandParserExecute;
import core.Input;
import core.Output;
import core.Pair;
import errors.CharacterClassException;
import errors.GameLostException;
import errors.GameQuitException;
import errors.GameWonException;
import errors.SeedNotFoundException;
import message.Message;
import model.entity.AttackType;
import model.entity.FocusPoint;
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
import java.util.concurrent.atomic.AtomicInteger;
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
    private final CommandParserExecute parser;
    private final GameLevel gameLevel;
    private ArrayList<Ability> cardDeck;
    private Runa runa;
    private Queue<Monster> monsters;

    /**
     * Creates a new TaskHandler
     *
     * @param input  the input
     * @param output the output
     * @param parser the parser
     */
    public TaskHandler(Input input, Output output, CommandParserExecute parser) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        this.gameLevel = new GameLevel(1, 1);
        this.monsters = new ArrayDeque<>();
        this.cardDeck = new ArrayList<>();
    }

    /**
     * starts the game and executes the required steps
     *
     * @throws GameQuitException if the user quits the game
     */
    public void start() throws GameQuitException {
        this.runa = new Runa(initialize());
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
            } catch (CharacterClassException e) {
                output.output(String.format(Message.ENTER_NUMBER, RunaClass.values().length));
            }
        } while (runaClass == null);
        return runaClass;
    }

    /**
     * shuffle the cards
     *
     * @throws GameQuitException if the user quits the game
     */
    public void shuffleCards() throws GameQuitException {

        output.output(Message.SHUFFLE_MESSAGE);
        output.output(Message.ENTER_SEEDS);
        Pair<Integer, Integer> seeds = null;
        do {
            var userInput = input.read();
            parser.checkQuitParser(userInput);
            try {
                seeds = parser.parseSeeds(userInput);
            } catch (SeedNotFoundException e) {
                output.output(Message.ENTER_SEEDS);
            }
        } while (seeds == null);

        var mobsList = Mobs.getMobsFromGameLevel(gameLevel.getGameLevel());
        var abilities = Abilities.getAllAbilitiesForRuna(runa.getClassOfRuna());

        Collections.shuffle(abilities, new Random(seeds.getFirstElement()));
        Collections.shuffle(mobsList, new Random(seeds.getSecondElement()));

        var listOfAbilities = new ArrayList<Ability>();
        var listOfMonsters = new ArrayList<Monster>();

        abilities.forEach(ability -> listOfAbilities.add(new Ability(ability, new Score(gameLevel.getGameLevel()))));
        mobsList.forEach(monster -> listOfMonsters.add(new RegularMonster(monster, new FocusPoint(0))));

        this.monsters = new LinkedList<>(listOfMonsters);
        cardDeck = (new ArrayList<>(listOfAbilities));

    }

    /**
     * handles the fighting section of the game.
     *
     * @throws GameQuitException if runa quits the game
     * @throws GameLostException if runa lost the game
     * @throws GameWonException  if runa won the game
     */
    public void fight() throws GameQuitException, GameLostException, GameWonException {
        var inLevel = true;

        giveRunaCards();

        while (inLevel) {

            output.output(String.format(Message.ENTER_STAGE, gameLevel.getGameRoom(), gameLevel.getGameLevel()));
            var monstersInRoom = getMobsForRoom();

            //as long as in the same room
            while (true) {
                output.output(getBattleInformation(monstersInRoom));


                //first runa
                turnOfRuna(monstersInRoom);
                getRidOfDeadMobs(monstersInRoom);
                //check if won
                if (!monsterIsAlive(monstersInRoom)) break;
                //fp of mobs
                focusPointsTurnMonster(monstersInRoom);
                //every mob
                turnOfMonsters(monstersInRoom);
                getRidOfDeadMobs(monstersInRoom);
                //check if lost
                hasLost();
                //fp of runa
                focusPointsTurnRuna();

            }

            resetBuffs();
            //get reward as long it wasn't the boss
            if (gameLevel.getGameRoom() != 4) reward();
            inLevel = nextRoom();
            //don't heal if the level change -> need to upgrade first
            if (inLevel) heal();
        }
        //check if won the game
        if (gameLevel.getGameLevel() == 2) throw new GameWonException(Message.WON);
        //check rewards
        upgrade();
        //heal player
        heal();

        nextLevel();
    }

    private void resetBuffs() {
        runa.setFocusCard(null);
        runa.setDefenseCard(null);
    }

    private void getRidOfDeadMobs(List<Monster> monstersInRoom) {
        monstersInRoom.forEach(mob -> {
            if (mob.isDead()) monstersInRoom.remove(mob);
        });
    }

    private void giveRunaCards() {
        var spells = runa.getClassOfRuna().getAbilities();
        var abilitiesList = new ArrayList<Ability>();
        spells.forEach(ability -> abilitiesList.add(new Ability(ability, new Score(gameLevel.getGameLevel()))));
        runa.setAbilities(abilitiesList);
    }

    private String getCardsForReward(int size) {
        var outputBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (cardDeck.isEmpty()) break;
            outputBuilder.append(String.format(Message.CARDS_LISTED, i + 1, cardDeck.get(i).toString()));
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
            cardDeck.remove(0);
        } else {
            output.output(String.format(Message.PICK_CARDS, 2, getCardsForReward(4)));
            int[] card;
            do {
                output.output(String.format(Message.ENTER_MULTIPLE_NUMBER, Math.min(cardDeck.size(), 4)));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                card = parser.parseNumbers(userInput, Math.min(cardDeck.size(), 4), 2);
            } while (card == null);
            var list = new ArrayList<Ability>();
            Arrays.stream(card).forEach(ability -> {
                runa.addCard(cardDeck.get(ability - 1));
                list.add(cardDeck.get(ability - 1));
            });
            Arrays.sort(card);
            cardDeck.remove(card[0]);
            cardDeck.remove(card[1] - 1);
            cardDeck.remove(0);
            cardDeck.remove(0);
            list.forEach(ability -> output.output(String.format(Message.UPGRADE, ability.toString())));
            list.forEach(cardDeck::remove);
        }
    }

    private void reward() throws GameQuitException {

        //check if runa can get better dice
        if (runa.getDice().getSides() == 12) {
            chooseCard();
        } else {
            output.output(Message.REWARD);
            int reward;
            do {
                output.output(String.format(Message.ENTER_NUMBER, 2));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                reward = parser.parseNumber(userInput, 2);
            } while (reward == 0);
            if (reward == 1) {
                chooseCard();
            } else {
                output.output(runa.newDice());
            }
        }
    }

    private void upgrade() {
        output.output(runa.upgrade());
    }

    private void turnOfMonsters(List<Monster> monstersInRoom) {
        monstersInRoom.forEach(monster -> {
            var card = monster.poll();
            output.output(String.format(Message.MOB_ATTACK, monster.getName(), card));
            if (card.getCard().isBreakFocus() && runa.getFocusCard() != null) runa.setFocusCard(null);
            if (card.getCard().getAttackType() == AttackType.ATTACK) {
                var damage = runa.takeDamage(card.getCard().calculateDamage(card.getLevel()), card.getCard().getType(), monster);
                if (damage.getHealthPoints() > 0)
                    output.output(String.format(Message.RUNA_TOOK_DAMAGE, damage.getHealthPoints(), card.getCard().getType().getRepresentation()));
            } else if (card.getCard().getAttackType() == AttackType.DEFENSE) {
                monster.setDefenseCard(card);
            } else {
                monster.setFocusCard(card);
            }
            monster.reduceFocusPoints(card);
            if (monster.getHealthPoints().getHealthPoints() <= 0)
                output.output(String.format(Message.MOB_DIED, monster.getName()));
            monster.addBack(card);
        });
    }

    private void focusPointsTurnRuna() {
        if (runa.getFocusCard() == null) return;
        var focusPoints = runa.getFocusCard().getLevel().getNumber();
        output.output(String.format(Message.RUNA_FOCUS, focusPoints));
        runa.getFocusPoints().setFocusPoints(Math.min(focusPoints + runa.getFocusPoints().getFocusPoints(), runa.getDice().getSides()));
        runa.setFocusCard(null);
    }

    private void focusPointsTurnMonster(List<Monster> monstersInRoom) {
        monstersInRoom.stream().filter(monster -> monster.getFocusCard() != null)
                .collect(Collectors.toList()).forEach(monster -> {
            monster.getFocusPoints().setFocusPoints(monster.getFocusCard().getLevel().getNumber());
            output.output(String.format(Message.MOB_FOCUS, monster.getName(), monster.getFocusCard().getLevel().getNumber()));
            monster.setFocusCard(null);
        });
    }

    private List<Monster> getMobsForRoom() {
        LinkedList<Monster> list = new LinkedList<>();
        if (gameLevel.getGameRoom() == 4)
            list.add(new BossMonster(BossMobs.getBoss(gameLevel.getGameLevel()), new FocusPoint(0)));
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

        } while (cardIndex == 0);
        card = runa.getCard(cardIndex - 1);

        //optional need monster to attack
        if (monstersInRoom.size() != 1 && card.getAbility().getAttackType() == AttackType.ATTACK) {
            output.output(String.format(Message.PICK_TARGET, getMonstersInRoomToString(monstersInRoom)));
            int mobIndex;
            do {
                output.output(String.format(Message.ENTER_NUMBER, monstersInRoom.size()));
                var userInput = input.read();
                parser.checkQuitParser(userInput);
                mobIndex = parser.parseNumber(userInput, monstersInRoom.size());
            } while (mobIndex == 0);
            runaAttack(monstersInRoom.get(mobIndex - 1), card);
        } else {
            runaAttack(monstersInRoom.get(0), card);
        }
        reduceFocusPoints(card);
    }

    private void reduceFocusPoints(Ability card) {
        runa.reduceFocusPoints(card.getAbility().getFpCosts());
    }

    private String getMonstersInRoomToString(List<Monster> monstersInRoom) {
        var outputBuilder = new StringBuilder();
        var i = new AtomicInteger();
        monstersInRoom.forEach(mob -> outputBuilder.append(String.format(Message.CARDS_LISTED, i.incrementAndGet(), mob.getName())).append("\n"));
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    private void runaAttack(Monster target, Ability card) throws GameQuitException {
        output.output(String.format(Message.ATTACK_USE, card));
        if (card.getAbility().isBreakFocus() && target.getFocusCard() != null) target.setFocusCard(null);
        if (card.getAbility().getAttackType() == AttackType.ATTACK) {
            var dmg = target.takeDamage(card.getAbility().calculateDamage(card.getLevel()
                    , card.getAbility().isNeedRoll() ? new Score(enterRoll()) : new Score(0)
                    , runa.getFocusPoints(), target.getType())
                    , card.getAbility().getAbilityType());
            if (dmg > 0)
                output.output(String.format(Message.MOB_TOOK_DAMAGE, target.getName(), dmg, card.getAbility().getAbilityType().getRepresentation()));
        } else if (card.getAbility().getAttackType() == AttackType.DEFENSE) {
            runa.setDefenseCard(card);
        } else {
            runa.setFocusCard(card);
        }
        if (target.getHealthPoints().getHealthPoints() <= 0)
            output.output(String.format(Message.MOB_DIED, target.getName()));
    }

    private void hasLost() throws GameLostException {
        if (runa.getHealthPoints().getHealthPoints() <= 0) throw new GameLostException(Message.LOST);
    }

    private boolean monsterIsAlive(List<Monster> monstersInRoom) {
        return monstersInRoom.stream().anyMatch(monster -> monster.getHealthPoints().getHealthPoints() > 0);
    }

    private int enterRoll() throws GameQuitException {
        output.output(String.format(Message.ENTER_ROLL, runa.getDice().getSides()));
        int roll;
        do {
            var inputUser = input.read();
            roll = parser.parseNumber(inputUser, runa.getDice().getSides());
        } while (roll == 0);
        return roll;
    }

    /**
     * update the gamelevel to the next level
     *
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
        } else {
            gameLevel.setGameRoom(gameLevel.getGameRoom() + 1);
            return true;
        }
    }

    private String getBattleInformation(List<Monster> monstersInRoom) {
        var outputBuilder = new StringBuilder();
        monstersInRoom.forEach(monster -> outputBuilder.append(monster.toString()).append(System.lineSeparator()));
        return String.format(Message.BATTLE_INFO, runa.toString(), outputBuilder);
    }

    private void heal() throws GameQuitException {
        if (runa.getHealthPoints().getHealthPoints() == 50 || runa.getAbilities().size() == 1) return;
        if (runa.getAbilities().size() == 2) {
            int[] heal;
            output.output(String.format(Message.HEAL, runa.getHealthPoints().getHealthPoints(), runa.getCardsInfo()));
            do {
                output.output(String.format(Message.ENTER_NUMBER, runa.getAbilities().size()));
                var inputUser = input.read();
                parser.checkQuitParser(inputUser);
                heal = parser.parseHealNumbers(inputUser, runa.getAbilities().size(), 1);

            } while (heal == null);
            if (heal.length == 0) return;
            runa.discardCard(heal);
            var healthBefore = runa.getHealthPoints().getHealthPoints();
            runa.heal();
            output.output(String.format(Message.HEALED, runa.getHealthPoints().getHealthPoints() - healthBefore));
        } else {
            int[] heal;
            output.output(String.format(Message.HEAL, runa.getHealthPoints().getHealthPoints(), runa.getCardsInfo()));
            do {
                output.output(String.format(Message.ENTER_MULTIPLE_NUMBER, runa.getAbilities().size()));
                var inputUser = input.read();
                parser.checkQuitParser(inputUser);
                heal = parser.parseHealNumbers(inputUser, runa.getMaxCardsChoice(), runa.getPossibleHealSize());

            } while (heal == null);
            if (heal.length == 0) return;
            var healthBefore = runa.getHealthPoints().getHealthPoints();
            Arrays.stream(heal).forEach(card -> runa.heal());
            runa.discardCard(heal);
            output.output(String.format(Message.HEALED, runa.getHealthPoints().getHealthPoints() - healthBefore));
        }
    }

}
