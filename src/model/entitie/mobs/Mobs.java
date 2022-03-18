package model.entitie.mobs;

import model.entitie.HealthPoints;

import java.util.ArrayList;
import java.util.List;

public enum Mobs {

    FROG("Frog", 1, new HealthPoints(16)),
    GHOST("Ghost", 1, new HealthPoints(15)),
    GORGON("Gorgon", 1, new HealthPoints(13)),
    SKELETON("Skeleton", 1, new HealthPoints(14)),
    SPIDER("Spide", 1, new HealthPoints(15)),
    GOBLIN("Goblin", 1, new HealthPoints(12)),
    RAT("Rat", 1, new HealthPoints(14)),
    MUSHROOMLIN("Mushroomlin", 1, new HealthPoints(20)),
    SNAKE("Snake", 2, new HealthPoints(31)),
    DARK_ELF("Dark Elf", 2, new HealthPoints(34)),
    SHADOW_BLADE("Shadow Blade", 2, new HealthPoints(27)),
    HORNET("Hornet", 2, new HealthPoints(32)),
    TARANTULA("Tarantula", 2, new HealthPoints(33)),
    BEAR("Bear", 2, new HealthPoints(40)),
    MUSHROOMLON("Mushroomlon", 2, new HealthPoints(50)),
    WILD_BOAR("Wild Boar", 2, new HealthPoints(27));

    private final String representation;
    private final int gameLevel;
    private final HealthPoints healthPoints;


    Mobs(String representation, int gameLevel, HealthPoints healthPoints) {
        this.representation = representation;
        this.gameLevel = gameLevel;
        this.healthPoints = healthPoints;
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

    public static List<Mobs> getMobsFromGameLevel(int gameLevel) {
        var mobs = new ArrayList<Mobs>();
        for (Mobs mob : Mobs.values()) {
            if (mob.getGameLevel() == gameLevel) mobs.add(mob);
        }
        return mobs;
    }
}
