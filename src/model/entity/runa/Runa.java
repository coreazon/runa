package model.entity.runa;

import message.Message;
import model.dice.Dice;
import model.entity.FocusPoints;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.mobs.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runa {

    private static final int STARTING_HEALTH = 50;
    private static final int STARTING_DICE = 4;
    private static final int BOTTLENECK = 40;
    private static final int HEAL = 10;
    private static final int MAX_HEAL_SIZE = 5;
    private final RunaClass classOfRuna;
    private final HealthPoints healthPoints;
    private final Dice dice;
    private final FocusPoints focusPoints;
    private ArrayList<Ability> abilities;
    private Ability focusCard;
    private Ability defenseCard;
    private static final String CARDS_FORMAT = "%d) %s";

    public Runa(RunaClass classOfRuna) {
        this.healthPoints = new HealthPoints(STARTING_HEALTH);
        this.classOfRuna = classOfRuna;
        this.dice = new Dice(STARTING_DICE);
        this.abilities = new ArrayList<>();
        this.focusPoints = new FocusPoints(1);
        this.focusCard = null;
        this.defenseCard = null;
    }

    public Ability getDefenseCard() {
        return defenseCard;
    }

    public void setDefenseCard(Ability defenseCard) {
        this.defenseCard = defenseCard;
    }

    public Ability getFocusCard() {
        return focusCard;
    }

    public void setFocusCard(Ability focusCard) {
        this.focusCard = focusCard;
    }

    public Dice getDice() {
        return dice;
    }

    public FocusPoints getFocusPoints() {
        return focusPoints;
    }

    public HealthPoints takeDamage(HealthPoints damage, AbilityType type, Monster target) {
        if (defenseCard != null && defenseCard.getAbility().getDefense() == type) {
            damage.shieldDamage(defenseCard.getAbility().calculateDamage(defenseCard.getLevel(), new Score(0), this.focusPoints, target.getType()));
            if (defenseCard.getAbility() == Abilities.REFLECT) {
                target.takeDirectHit(defenseCard.getAbility().calculateDamage(defenseCard.getLevel(), new Score(0), this.focusPoints, target.getType()));
            }

            this.defenseCard = null;
        }
        getHealthPoints().takeDamage(damage);
        return damage;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = (ArrayList<Ability>) abilities;
    }

    public RunaClass getClassOfRuna() {
        return classOfRuna;
    }

    public HealthPoints getHealthPoints() {
        return healthPoints;
    }

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

    public Ability getCard(int index) {
        return abilities.get(index);
    }

    public int getMaxCardsChoice() {
        return getAbilities().size();
    }

    public void heal() {
        if (getHealthPoints().getHealthPoints() <= BOTTLENECK) getHealthPoints().heal(HEAL);
        else getHealthPoints().heal(STARTING_HEALTH - getHealthPoints().getHealthPoints());
    }

    public void discardCard(int[] cardsToDiscard) {
        var listOfCardsToDiscard = new ArrayList<Ability>();
        Arrays.stream(cardsToDiscard).forEach(card -> listOfCardsToDiscard.add(getAbilities().get(card)));
        listOfCardsToDiscard.forEach(card -> getAbilities().remove(card));
    }

    public int getPossibleHealSize() {
        var missingHealth = STARTING_HEALTH - getHealthPoints().getHealthPoints();
        var heal = 1;
        for (int i = 10; i < STARTING_HEALTH; i += 10) {
            if (i >= missingHealth) return heal;
            heal++;
        }
        return heal;
    }

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

    public String newDice() {
        dice.setSides(dice.getSides() + 2);
        return String.format(Message.DICE_UPGRADE, dice.getSides());
    }

    public boolean canPlayCard(Ability card) {
        return focusPoints.getFocusPoints() >= card.getAbility().getFpCosts().getFocusPoints();
    }

    public void reduceFocusPoints(FocusPoints focusPoints) {
        this.focusPoints.setFocusPoints(getFocusPoints().getFocusPoints() - focusPoints.getFocusPoints());
    }

    public void addCard(Ability card) {
        abilities.add(card);
    }
}
