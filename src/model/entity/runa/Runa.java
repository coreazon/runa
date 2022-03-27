package model.entity.runa;

import message.Message;
import model.dice.Dice;
import model.entity.FocusPoint;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.mobs.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * THis calss represents runa
 *
 * @author urliz
 * @version 1.0
 */
public class Runa {

    private static final int STARTING_HEALTH = 50;
    private static final int STARTING_DICE = 4;
    private static final int BOTTLENECK = 40;
    private static final int HEAL = 10;
    private static final int MAX_HEAL_SIZE = 5;
    private static final String CARDS_FORMAT = "%d) %s";
    private final RunaClass classOfRuna;
    private final HealthPoints healthPoints;
    private final Dice dice;
    private final FocusPoint focusPoints;
    private ArrayList<Ability> abilities;
    private Ability focusCard;
    private Ability defenseCard;

    /**
     * Instantiates a new Runa
     *
     * @param classOfRuna the class of runa
     */
    public Runa(RunaClass classOfRuna) {
        this.healthPoints = new HealthPoints(STARTING_HEALTH);
        this.classOfRuna = classOfRuna;
        this.dice = new Dice(STARTING_DICE);
        this.abilities = new ArrayList<>();
        this.focusPoints = new FocusPoint(1);
        this.focusCard = null;
        this.defenseCard = null;
    }

    /**
     * sets the defenseCard
     *
     * @param defenseCard teh new defenseCard
     */
    public void setDefenseCard(Ability defenseCard) {
        this.defenseCard = defenseCard;
    }

    /**
     * returns the focus card
     *
     * @return focus card
     */
    public Ability getFocusCard() {
        return focusCard;
    }

    /**
     * sets the focus card
     *
     * @param focusCard focus card
     */
    public void setFocusCard(Ability focusCard) {
        this.focusCard = focusCard;
    }

    /**
     * returns the dic
     *
     * @return the dice
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * returns the fp points
     *
     * @return the fp points
     */
    public FocusPoint getFocusPoints() {
        return focusPoints;
    }

    /**
     * takes damage
     *
     * @param damage the raw damage
     * @param type   teh type of damag
     * @param target the target
     * @return the damage taken
     */
    public HealthPoints takeDamage(HealthPoints damage, AbilityType type, Monster target) {
        if (defenseCard != null && defenseCard.getAbility().getDefense() == type) {
            damage.shieldDamage(defenseCard.getAbility().calculateDamage(defenseCard.getLevel()
                    , new Score(0), this.focusPoints, target.getType()));
            if (defenseCard.getAbility() == Abilities.REFLECT) {
                target.takeDirectHit(defenseCard.getAbility().calculateDamage(defenseCard.getLevel()
                        , new Score(0), this.focusPoints, target.getType()));
            }

            this.defenseCard = null;
        }
        getHealthPoints().takeDamage(damage);
        return damage;
    }

    /**
     * returns all abilities
     *
     * @return all abilities
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * sets the abilities
     *
     * @param abilities abilities
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = (ArrayList<Ability>) abilities;
    }

    /**
     * returns the class of runa
     *
     * @return the class
     */
    public RunaClass getClassOfRuna() {
        return classOfRuna;
    }

    /**
     * returns the healthPoints
     *
     * @return healthPoints
     */
    public HealthPoints getHealthPoints() {
        return healthPoints;
    }

    /**
     * returns the information of all cards
     *
     * @return the information
     */
    public String getCardsInfo() {
        var outputBuilder = new StringBuilder();
        for (int i = 0; i < abilities.size(); i++) {
            outputBuilder.append(String.format(CARDS_FORMAT, i + 1, getAbilities().get(i).toString()));
            outputBuilder.append("\n");
        }
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    @Override
    public String toString() {
        return String.format(Message.RUNA_TOSTRING,
                healthPoints.getHealthPoints(),
                focusPoints.getFocusPoints(),
                dice.getSides());
    }

    /**
     * returns the card at a given index
     *
     * @param index the index
     * @return the card
     */
    public Ability getCard(int index) {
        return abilities.get(index);
    }

    /**
     * returns the maximum card choices
     *
     * @return the amount
     */
    public int getMaxCardsChoice() {
        return getAbilities().size();
    }

    /**
     * heals runa
     */
    public void heal() {
        if (getHealthPoints().getHealthPoints() <= BOTTLENECK) getHealthPoints().heal(HEAL);
        else getHealthPoints().heal(STARTING_HEALTH - getHealthPoints().getHealthPoints());
    }

    /**
     * discards the given card
     *
     * @param cardsToDiscard all the cards
     */
    public void discardCard(int[] cardsToDiscard) {
        var listOfCardsToDiscard = new ArrayList<Ability>();
        Arrays.stream(cardsToDiscard).forEach(card -> listOfCardsToDiscard.add(getAbilities().get(card - 1)));
        listOfCardsToDiscard.forEach(card -> getAbilities().remove(card));
    }

    /**
     * returns the max possible heal size
     *
     * @return the aount
     */
    public int getPossibleHealSize() {
        var missingHealth = STARTING_HEALTH - getHealthPoints().getHealthPoints();
        var heal = 1;
        for (int i = 10; i < STARTING_HEALTH; i += 10) {
            if (i >= missingHealth) return heal;
            heal++;
        }
        return heal;
    }

    /**
     * upgrades runa
     *
     * @return the string result
     */
    public String upgrade() {
        var cards = getClassOfRuna().getAbilities();
        var outputBuilder = new StringBuilder();
        cards.forEach(card -> {
            var newCard = new Ability(card, new Score(2));
            this.abilities.add(newCard);
            outputBuilder.append(String.format(Message.UPGRADE, newCard)).append("\n");
        });
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    /**
     * gives runa a new dice
     *
     * @return the string result
     */
    public String newDice() {
        dice.setSides(dice.getSides() + 2);
        return String.format(Message.DICE_UPGRADE, dice.getSides());
    }

    /**
     * reduces runa fp points
     *
     * @param focusPoints the fp points
     */
    public void reduceFocusPoints(FocusPoint focusPoints) {
        this.focusPoints.setFocusPoints(getFocusPoints().getFocusPoints() - focusPoints.getFocusPoints());
        if (this.focusPoints.getFocusPoints() == 0) this.focusPoints.setFocusPoints(1);
    }

    /**
     * adds a new card
     *
     * @param card teh card
     */
    public void addCard(Ability card) {
        abilities.add(card);
    }
}
