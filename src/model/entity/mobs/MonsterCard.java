package model.entity.mobs;

import model.entity.Score;

public class MonsterCard {

    private final MonsterCards card;
    private final Score level;
    private static final String TO_STRING_FORMAT = "%s(%d)";

    public MonsterCard(MonsterCards card, Score level) {
        this.card = card;
        this.level = level;
    }

    public Score getLevel() {
        return level;
    }

    public MonsterCards getCard() {
        return card;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, card.getRepresentation(), level.getNumber());
    }
}
