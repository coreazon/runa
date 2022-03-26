package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Mobs {

    FROG("Frog", Type.WATER, 1, new HealthPoints(16), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.WATER, new Score(1))))),
    GHOST("Ghost", Type.ICE, 1, new HealthPoints(15), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.ICE, new Score(1))))),
    GORGON("Gorgon", Type.FIRE, 1, new HealthPoints(13), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.FIRE, new Score(1))))),
    SKELETON("Skeleton", Type.LIGHTNING, 1, new HealthPoints(14), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1))))),
    SPIDER("Spider", Type.NONE, 1, new HealthPoints(15),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(1)), new MonsterCard(MonsterCards.BLOCK, new Score(1))))),
    GOBLIN("Goblin", Type.NONE, 1, new HealthPoints(12),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SMASH, new Score(1)), new MonsterCard(MonsterCards.DEFLECT, new Score(1))))),
    RAT("Rat", Type.NONE, 1, new HealthPoints(14), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BLOCK, new Score(1)), new MonsterCard(MonsterCards.CLAW, new Score(1))))),
    MUSHROOMLIN("Mushroomlin", Type.NONE, 1, new HealthPoints(20),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.DEFLECT, new Score(1)), new MonsterCard(MonsterCards.SCRATCH, new Score(1))))),
    SNAKE("Snake", Type.ICE, 2, new HealthPoints(31),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(2)), new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.ICE, new Score(2))))),
    DARK_ELF("Dark Elf", Type.NONE, 2, new HealthPoints(34), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.WATER, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1))))),
    SHADOW_BLADE("Shadow Blade", Type.LIGHTNING, 2, new HealthPoints(27),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SCRATCH, new Score(2)), new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.LIGHTNING, new Score(2))))),
    HORNET("Hornet", Type.FIRE, 2, new HealthPoints(32), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SCRATCH, new Score(2)), new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.FIRE, new Score(1)), new MonsterCard(MonsterCards.FIRE, new Score(2))))),
    TARANTULA("Tarantula", Type.NONE, 2, new HealthPoints(33), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(2)), new MonsterCard(MonsterCards.BLOCK, new Score(2)), new MonsterCard(MonsterCards.SCRATCH, new Score(2))))),
    BEAR("Bear", Type.NONE, 2, new HealthPoints(40), new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.CLAW, new Score(2)), new MonsterCard(MonsterCards.DEFLECT, new Score(2)), new MonsterCard(MonsterCards.BLOCK, new Score(2))))),
    MUSHROOMLON("Mushroomlon", Type.NONE, 2, new HealthPoints(50),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.DEFLECT, new Score(2)), new MonsterCard(MonsterCards.SCRATCH, new Score(2)), new MonsterCard(MonsterCards.BLOCK, new Score(2))))),
    WILD_BOAR("Wild Boar", Type.NONE, 2, new HealthPoints(27),new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.SCRATCH, new Score(2)), new MonsterCard(MonsterCards.DEFLECT, new Score(2)), new MonsterCard(MonsterCards.SCRATCH, new Score(2)))));

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;
    private final ArrayList<MonsterCard> cards;
    private final Type type;

    Mobs(String representation, Type type, int gameLevel, HealthPoints healthPoints, ArrayList<MonsterCard> cards) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
        this.cards = cards;
        this.type = type;
    }

    public static ArrayList<Mobs> getMobsFromGameLevel(int gameLevel) {
        var mobs = new ArrayList<Mobs>();
        for (Mobs mob : Mobs.values()) {
            if (mob.getGameLevel() == gameLevel) mobs.add(mob);
        }
        return mobs;
    }

    public Type getType() {
        return type;
    }

    public ArrayList<MonsterCard> getCards() {
        return cards;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public HealthPoints getHealthPoints() {
        return healthPoints;
    }
}
