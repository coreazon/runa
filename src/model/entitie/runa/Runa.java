package model.entitie.runa;

import model.dice.Dice;
import model.entitie.FocusPoints;
import model.entitie.HealthPoints;

import java.util.ArrayList;

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
        this.abilities = (ArrayList<Abilities>) this.classOfRuna.getAbilities();
        this.focusPoints = new FocusPoints(this.dice.getSides());
    }

    public void setAbilities(ArrayList<Abilities> abilities) {
        this.abilities = abilities;
    }
}
