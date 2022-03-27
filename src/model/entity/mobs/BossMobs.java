package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

import java.util.ArrayList;
import java.util.Arrays;

public enum BossMobs {

    SPIDER_KING("Spider King", 1, new HealthPoints(50), Type.LIGHTNING, new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(1)),
            new MonsterCard(MonsterCards.BLOCK, new Score(1)), new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1))))),
    MEGA_SAURUS("Mega Saurus", 2, new HealthPoints(100), Type.NONE, new ArrayList<>(Arrays.asList(new MonsterCard(MonsterCards.BITE, new Score(2)), new MonsterCard(MonsterCards.BLOCK, new Score(2)),
            new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.FIRE, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1)))));

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;
    private final Type type;
    private final ArrayList<MonsterCard> cards;

    BossMobs(String representation, int gameLevel, HealthPoints healthPoints, Type type, ArrayList<MonsterCard> cards) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
        this.type = type;
        this.cards = cards;
    }

    public static BossMobs getBoss(int level) {
        return Arrays.stream(BossMobs.values()).filter(boss -> boss.getGameLevel() == level).findFirst().orElseThrow();
    }

    public ArrayList<MonsterCard> getCards() {
        return cards;
    }

    public Type getType() {
        return type;
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
