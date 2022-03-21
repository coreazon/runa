package model.entitie.mobs;

import model.entitie.HealthPoints;

import java.util.Arrays;

public enum BossMobs {

    SPIDER_KING("Spider King", 1, new HealthPoints(50), Type.LIGHTNING),
    MEGA_SAURUS("Mega Saurus", 2, new HealthPoints(100), Type.NONE);

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;
    private final Type type;

    BossMobs(String representation, int gameLevel, HealthPoints healthPoints, Type type) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
        this.type = type;
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
