package model.entity.mobs;

import model.entity.Score;

/**
 * THis class represents a Monster Card
 *
 * @author urliz
 * @version 1.0
 */
public class MonsterCard {

    private static final String TO_STRING_FORMAT = "%s(%d)";
    private final MonsterCards card;
    private final Score level;

    /**
     * Creates a new MonsterCard
     *
     * @param card  the card
     * @param level the level
     */
    public MonsterCard(MonsterCards card, Score level) {
        this.card = card;
        this.level = level;
    }

    /**
     * returns the level of the card
     *
     * @return the level
     */
    public Score getLevel() {
        return level;
    }

    /**
     * return the card
     *
     * @return the card
     */
    public MonsterCards getCard() {
        return card;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, card.getRepresentation(), level.getNumber());
    }
}
