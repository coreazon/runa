package model.entitie.runa;

import message.Message;
import model.dice.Dice;
import model.entitie.FocusPoints;
import model.entitie.HealthPoints;

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
    private ArrayList<Ability> abilities;
    private final Dice dice;
    private final FocusPoints focusPoints;
    private Ability focusCard;

    public Runa(RunaClass classOfRuna) {
        this.healthPoints = new HealthPoints(STARTING_HEALTH);
        this.classOfRuna = classOfRuna;
        this.dice = new Dice(STARTING_DICE);
        this.abilities = new ArrayList<>();
        this.focusPoints = new FocusPoints(this.dice.getSides());
        this.focusCard = null;
    }

    public Ability getFocusCard() {
        return focusCard;
    }

    public Dice getDice() {
        return dice;
    }

    public FocusPoints getFocusPoints() {
        return focusPoints;
    }

    public void setFocusCard(Ability focusCard) {
        this.focusCard = focusCard;
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
        return "";
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
        return 0;
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
        for (int i = 10; i < STARTING_HEALTH; i+=10) {
            if (i >= missingHealth) return heal;
            heal++;
        }
        return heal;
    }
}
