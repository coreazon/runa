package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This enum holds all the boss monster of the game
 *
 * @author urliz
 * @version 1.0
 */
public enum BossMobs {
    /**
     * spider king boss
     */
    SPIDER_KING("Spider King", 1, new HealthPoints(50), Type.LIGHTNING, new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(1)),
            new MonsterCard(MonsterCards.BLOCK, new Score(1)), new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1))))),
    /**
     * mega-saurus boss
     */
    MEGA_SAURUS("Mega Saurus", 2, new HealthPoints(100), Type.NONE, new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(2)), new MonsterCard(MonsterCards.BLOCK, new Score(2)),
            new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.FIRE, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1)))));

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;
    private final Type type;
    private final ArrayList<MonsterCard> cards;

    /**
     * constructor of a boss
     *
     * @param representation representation of the boss
     * @param gameLevel      gameLevel of the boss
     * @param healthPoints   healthPoints of the boss
     * @param type           type of the Boss
     * @param cards          cards that the boss has
     */
    BossMobs(String representation, int gameLevel, HealthPoints healthPoints, Type type, ArrayList<MonsterCard> cards) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
        this.type = type;
        this.cards = cards;
    }

    /**
     * returns the boss for the given game level
     *
     * @param level the level of the game
     * @return the boss for that level
     */
    public static BossMobs getBoss(int level) {
        return Arrays.stream(BossMobs.values()).filter(boss -> boss.getGameLevel() == level).findFirst().orElseThrow();
    }

    /**
     * return the cards that boss has
     *
     * @return all his cards
     */
    public ArrayList<MonsterCard> getCards() {
        return cards;
    }

    /**
     * returns the type of the boss
     *
     * @return type of the boss
     */
    public Type getType() {
        return type;
    }

    /**
     * returns the string representation of the boss
     *
     * @return string representation of the boss
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * returns the gamelevel for the boss
     *
     * @return the gamelevle for the boss
     */
    public int getGameLevel() {
        return gameLevel;
    }

    /**
     * returns the healthPoints of that boss
     *
     * @return the healthPoints
     */
    public HealthPoints getHealthPoints() {
        return healthPoints;
    }

}
