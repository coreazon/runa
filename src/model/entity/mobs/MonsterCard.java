package model.entity.mobs;

import model.entity.Score;

public class MonsterCard {

    private final MonsterCards card;
    private final Score level;

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
}
