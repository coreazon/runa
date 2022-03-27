package model.entity.mobs;

import model.entity.FocusPoint;
import model.entity.HealthPoints;
import model.entity.runa.AbilityType;

import java.util.ArrayList;
import java.util.List;

/**
 * THis class represents a Monster in the game
 *
 * @author urliz
 * @version 1.0
 */
public class Monster {

    private static final String TO_STRING_FORMAT = "%s (%d HP, %d FP): attempts %s next";
    private final HealthPoints healthPoints;
    private final ArrayList<MonsterCard> cards;
    private final Type type;
    private final FocusPoint focusPoints;
    private final String name;
    private MonsterCard focusCard;
    private MonsterCard defenseCard;

    /**
     * creates a new Monster
     *
     * @param name         name of that monster
     * @param healthPoints health points of that monster
     * @param focusPoints  focus points of that monster
     * @param cards        cards of that monster
     * @param type         type of that monster
     */
    public Monster(String name, HealthPoints healthPoints, FocusPoint focusPoints, ArrayList<MonsterCard> cards, Type type) {
        this.healthPoints = healthPoints;
        this.focusCard = null;
        this.type = type;
        this.defenseCard = null;
        this.focusPoints = focusPoints;
        this.cards = cards;
        this.name = name;
    }

    /**
     * returns the type of the monster
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * returns the focusPoints of the monster
     *
     * @return the focusPoints
     */
    public FocusPoint getFocusPoints() {
        return focusPoints;
    }

    /**
     * returns the defenseCard of the monster
     *
     * @return defenseCard
     */
    public MonsterCard getDefenseCard() {
        return defenseCard;
    }

    /**
     * sets the defenseCard of the monser
     *
     * @param defenseCard the card
     */
    public void setDefenseCard(MonsterCard defenseCard) {
        this.defenseCard = defenseCard;
    }

    /**
     * returns the cards of the monster
     *
     * @return the cards
     */
    public List<MonsterCard> getCards() {
        return cards;
    }

    /**
     * returns the first card of that monster and removes it
     *
     * @return the card at index 0
     */
    public MonsterCard poll() {
        return cards.remove(0);
    }

    /**
     * adds a card back to the list
     *
     * @param card the card
     */
    public void addBack(MonsterCard card) {
        cards.add(card);
    }

    /**
     * returns the focus card
     *
     * @return the card
     */
    public MonsterCard getFocusCard() {
        return focusCard;
    }

    /**
     * sets the focus card
     *
     * @param focusCard the card
     */
    public void setFocusCard(MonsterCard focusCard) {
        this.focusCard = focusCard;
    }

    /**
     * returns the healthPoints of the moster
     *
     * @return the healthPoints
     */
    public HealthPoints getHealthPoints() {
        return healthPoints;
    }

    /**
     * lets the monster take damage
     *
     * @param damage the raw damage
     * @param type   type of the attack
     * @return the damage taken
     */
    public int takeDamage(HealthPoints damage, AbilityType type) {
        if (defenseCard != null) {
            if (defenseCard.getCard().getDefense() == type) {
                damage.shieldDamage(defenseCard.getCard().calculateDamage(defenseCard.getLevel()));
            }
            this.defenseCard = null;
        }
        healthPoints.takeDamage(damage);
        return damage.getHealthPoints();
    }

    /**
     * lets the monster take a direct hit
     *
     * @param damage the damage
     */
    public void takeDirectHit(HealthPoints damage) {
        healthPoints.takeDamage(damage);
    }

    /**
     * reduces the focusPoints of the monster
     *
     * @param card the card
     */
    public void reduceFocusPoints(MonsterCard card) {
        if (card.getCard().getType() != AbilityType.MAGICAL) return;
        this.focusPoints.setFocusPoints(getFocusPoints().getFocusPoints() - card.getLevel().getNumber());
    }

    /**
     * retuns the string representation of themonster
     *
     * @return the representation
     */
    public String getName() {
        return name;
    }

    /**
     * returns wether the monster is dead or not
     *
     * @return true if it is dead
     */
    public boolean isDead() {
        return this.healthPoints.getHealthPoints() <= 0;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, getName()
                , getHealthPoints().getHealthPoints()
                , getFocusPoints().getFocusPoints()
                , getCards().get(0).toString());
    }
}
