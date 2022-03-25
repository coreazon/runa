package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

import java.util.Arrays;
import java.util.List;

public enum BossMobs {

    SPIDER_KING("Spider King", 1, new HealthPoints(50), Type.LIGHTNING, List.of(new MonsterCard(MonsterCards.BITE, new Score(1)),
            new MonsterCard(MonsterCards.BLOCK, new Score(1)), new MonsterCard(MonsterCards.FOCUS, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1)))),
    MEGA_SAURUS("Mega Saurus", 2, new HealthPoints(100), Type.NONE, List.of(new MonsterCard(MonsterCards.BITE, new Score(2)),
            new MonsterCard(MonsterCards.FOCUS, new Score(2)), new MonsterCard(MonsterCards.FIRE, new Score(1)), new MonsterCard(MonsterCards.LIGHTNING, new Score(1))));

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;
    private final Type type;
    private final List<MonsterCard> cards;

    BossMobs(String representation, int gameLevel, HealthPoints healthPoints, Type type, List<MonsterCard> cards) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
        this.type = type;
        this.cards = cards;
    }

    public List<MonsterCard> getCards() {
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

    public static BossMobs getBoss(int level) {
        return Arrays.stream(BossMobs.values()).filter(boss -> boss.getGameLevel() == level).findFirst().orElseThrow();
    }

}
