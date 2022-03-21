package model.entitie.runa;

import message.Message;
import model.dice.Dice;
import model.entitie.FocusPoints;
import model.entitie.HealthPoints;

import java.util.ArrayList;
import java.util.List;

public class Runa {

    private static final int STARTING_HEALTH = 50;
    private static final int STARTING_DICE = 4;
    private final RunaClass classOfRuna;
    private final HealthPoints healthPoints;
    private ArrayList<Abilities> abilities;
    private final Dice dice;
    private final FocusPoints focusPoints;

    public Runa(RunaClass classOfRuna) {
        this.healthPoints = new HealthPoints(STARTING_HEALTH);
        this.classOfRuna = classOfRuna;
        this.dice = new Dice(STARTING_DICE);
        this.abilities = new ArrayList<>();
        this.focusPoints = new FocusPoints(this.dice.getSides());
    }

    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = (ArrayList<Abilities>) abilities;
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

    public Abilities getCard(int index) {
        return abilities.get(index);
    }

    public int getMaxCardsChoice() {
        return 0;
    }
}
