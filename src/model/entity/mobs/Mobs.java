package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * this enum holds all the monsters in the game
 *
 * @author urliz
 * @version 1.0
 */
public enum Mobs {
    /**
     * frog monster
     */
    FROG("Frog", Type.WATER, 1, new HealthPoints(16)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1))
            , new MonsterCard(MonsterCards.WATER, new Score(1))))),
    /**
     * ghost monster
     */
    GHOST("Ghost", Type.ICE, 1, new HealthPoints(15)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1))
            , new MonsterCard(MonsterCards.ICE, new Score(1))))),
    /**
     * gorgon monster
     */
    GORGON("Gorgon", Type.FIRE, 1, new HealthPoints(13)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1))
            , new MonsterCard(MonsterCards.FIRE, new Score(1))))),
    /**
     * Skeleton monster
     */
    SKELETON("Skeleton", Type.LIGHTNING, 1, new HealthPoints(14)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1))
            , new MonsterCard(MonsterCards.LIGHTNING, new Score(1))))),
    /**
     * spider monster
     */
    SPIDER("Spider", Type.NONE, 1, new HealthPoints(15)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(1))
            , new MonsterCard(MonsterCards.BLOCK, new Score(1))))),
    /**
     * goblin monster
     */
    GOBLIN("Goblin", Type.NONE, 1, new HealthPoints(12)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SMASH, new Score(1))
            , new MonsterCard(MonsterCards.DEFLECT, new Score(1))))),
    /**
     * rat monster
     */
    RAT("Rat", Type.NONE, 1, new HealthPoints(14)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BLOCK, new Score(1))
            , new MonsterCard(MonsterCards.CLAW, new Score(1))))),
    /**
     * mushroomlon monster
     */
    MUSHROOMLIN("Mushroomlin", Type.NONE, 1, new HealthPoints(20)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.DEFLECT, new Score(1))
            , new MonsterCard(MonsterCards.SCRATCH, new Score(1))))),
    /**
     * snake monster
     */
    SNAKE("Snake", Type.ICE, 2, new HealthPoints(31)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(2))
            , new MonsterCard(MonsterCards.FOCUS, new Score(2))
            , new MonsterCard(MonsterCards.ICE, new Score(2))))),
    /**
     * dark-elf mosnter
     */
    DARK_ELF("Dark Elf", Type.NONE, 2, new HealthPoints(34)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(2))
            , new MonsterCard(MonsterCards.WATER, new Score(1))
            , new MonsterCard(MonsterCards.LIGHTNING, new Score(1))))),
    /**
     * Shadow blade monster
     */
    SHADOW_BLADE("Shadow Blade", Type.LIGHTNING, 2, new HealthPoints(27)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SCRATCH, new Score(2))
            , new MonsterCard(MonsterCards.FOCUS, new Score(2))
            , new MonsterCard(MonsterCards.LIGHTNING, new Score(2))))),
    /**
     * Hornet monster
     */
    HORNET("Hornet", Type.FIRE, 2, new HealthPoints(32)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SCRATCH, new Score(2))
            , new MonsterCard(MonsterCards.FOCUS, new Score(2))
            , new MonsterCard(MonsterCards.FIRE, new Score(1))
            , new MonsterCard(MonsterCards.FIRE, new Score(2))))),
    /**
     * tarantula monster
     */
    TARANTULA("Tarantula", Type.NONE, 2, new HealthPoints(33)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(2))
            , new MonsterCard(MonsterCards.BLOCK, new Score(2))
            , new MonsterCard(MonsterCards.SCRATCH, new Score(2))))),
    /**
     * bear monster
     */
    BEAR("Bear", Type.NONE, 2, new HealthPoints(40)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.CLAW, new Score(2))
            , new MonsterCard(MonsterCards.DEFLECT, new Score(2))
            , new MonsterCard(MonsterCards.BLOCK, new Score(2))))),
    /**
     * mushroomlon monster
     */
    MUSHROOMLON("Mushroomlon", Type.NONE, 2, new HealthPoints(50)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.DEFLECT, new Score(2))
            , new MonsterCard(MonsterCards.SCRATCH, new Score(2))
            , new MonsterCard(MonsterCards.BLOCK, new Score(2))))),
    /**
     * wild boar mosnter
     */
    WILD_BOAR("Wild Boar", Type.NONE, 2, new HealthPoints(27)
            , new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SCRATCH, new Score(2))
            , new MonsterCard(MonsterCards.DEFLECT, new Score(2))
            , new MonsterCard(MonsterCards.SCRATCH, new Score(2)))));

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;
    private final ArrayList<MonsterCard> cards;
    private final Type type;

    /**
     * constructor of a monster
     *
     * @param representation representation of the monster
     * @param type           type of that monster
     * @param gameLevel      gameLevel for that monster
     * @param healthPoints   healthPoints of that monster
     * @param cards          cards of that monster
     */
    Mobs(String representation, Type type, int gameLevel, HealthPoints healthPoints, ArrayList<MonsterCard> cards) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
        this.cards = cards;
        this.type = type;
    }

    /**
     * returns all the monster for a gameLevel
     *
     * @param gameLevel the level
     * @return all the monster for that level
     */
    public static ArrayList<Mobs> getMobsFromGameLevel(int gameLevel) {
        var mobs = new ArrayList<Mobs>();
        for (Mobs mob : Mobs.values()) {
            if (mob.getGameLevel() == gameLevel) mobs.add(mob);
        }
        return mobs;
    }

    /**
     * returns the type of that monster
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * retuns the cards of the monster
     *
     * @return the cards
     */
    public ArrayList<MonsterCard> getCards() {
        return cards;
    }

    /**
     * returns the representation of a monster
     *
     * @return the representation
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * returns the gameLevel of a monster
     *
     * @return the gameLevel
     */
    public int getGameLevel() {
        return gameLevel;
    }

    /**
     * returns the healthpoint of a monster
     *
     * @return the healthpoint of a monster
     */
    public HealthPoints getHealthPoints() {
        return healthPoints;
    }
}
